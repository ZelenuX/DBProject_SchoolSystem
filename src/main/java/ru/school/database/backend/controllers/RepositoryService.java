package ru.school.database.backend.controllers;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;
import org.vaadin.crudui.form.FieldProvider;
import ru.school.database.backend.filteringUtils.EntitySpecification;
import ru.school.database.backend.filteringUtils.ObjectProvider;
import ru.school.database.backend.filteringUtils.SearchCriteria;
import ru.school.database.ui.utils.MyComboBoxProvider;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public abstract class RepositoryService<Entity, ID> {//todo 1st: change editing existing entity to creating new one in add and update methods
    private final  JpaRepository<Entity, ID> repository;
    private final  Class<Entity> entityClass;
    private final List<Column> cols = new ArrayList<>();
    private final Map<String, Column> namesToCols = new HashMap<>();
    private final Map<String, FieldProvider> fieldProviders = new HashMap<>();
    private final Map<String, JpaRepository> fieldRepositories = new HashMap<>();
    private IdProvider<Entity> idProvider = null;
    private List<IdProvider<Entity>> foreignKeyProviders = new ArrayList<>();
    private EntitySpecification<Entity> specification = new EntitySpecification<>();
    private boolean specificationBuilt = false;

    public RepositoryService(JpaRepository<Entity, ID> repository, Class<Entity> entityClass, String... colNames){
        this.repository = repository;
        this.entityClass = entityClass;
        for (String colName : colNames) {
            Column newCol = new Column(colName);
            this.cols.add(newCol);
            this.namesToCols.put(newCol.getName(), newCol);
        }
    }
    public Class<Entity> getEntityClass(){
        return entityClass;
    }
    public List<Column> getCols(){
        return cols;
    }
    public FieldProvider getFieldProvider(String colName){
        FieldProvider res = fieldProviders.get(colName);
        if (res == null){
            throw new RuntimeException("No field provider for field \"" + colName + "\"");
        }
        return res;
    }
    public void buildSpecification(Map<String, ObjectProvider> filters){
        if (specificationBuilt){
            return;
        }
        filters.forEach((fieldName, valueProvider) -> {
            specification.addCriteria(new SearchCriteria(fieldName, valueProvider));
        });
        specificationBuilt = true;
    }
    protected void setComboboxFieldProvider(String colName, JpaRepository provider){
        fieldProviders.put(colName, new MyComboBoxProvider(provider, namesToCols.get(colName).isFinal()));
        fieldRepositories.put(colName, provider);
    }
    protected void setIdProvider(String srcColDotMethod, String idCol){
        if (idProvider == null) {
            String[] colAndMethod = srcColDotMethod.split("\\.");
            idProvider = new IdProvider<>(entityClass, colAndMethod[0], colAndMethod[1], idCol);
        }
    }
    protected void addForeignKeyProvider(String srcColDotMethod, String idCol){
        String[] colAndMethod = srcColDotMethod.split("\\.");
        foreignKeyProviders.add(new IdProvider<>(entityClass, colAndMethod[0], colAndMethod[1], idCol));
    }

    public List<Entity> findAll(){
        return repository.findAll();
    }
    public List<Entity> findAllFiltered(){
        List<Entity> res = ((JpaSpecificationExecutor)repository).findAll(specification);
        return res;
    }
    public void delete(Entity entity){
        repository.delete(entity);
    }
    @Transactional
    public Entity add(Entity entity){
        if (idProvider != null){
            String srcColName = idProvider.getSrcColName();
            ID id = (ID)idProvider.getSrcId(entity);
            if (repository.findById(id).isPresent()){
                throw new RuntimeException("Could not add: entity already exists.");
            }
            JpaRepository srcFieldRepository = fieldRepositories.get(srcColName);
            if (!srcFieldRepository.findById(id).isPresent()){
                throw new RuntimeException("Could not add: entity from another table does not exist.");
            }
            idProvider.setIdAndClearSrcCol(entity);
        }
        foreignKeyProviders.forEach(provider -> provider.setIdAndClearSrcCol(entity));
        try {
            return repository.save(entity);
        }
        catch (Exception e){
            Throwable t = e;
            while (t.getCause() != null){
                t = t.getCause();
            }
            throw new RuntimeException(t.getMessage());
        }
    }
    @Transactional
    public Entity update(Entity entity){             //todo: delete old entity
        if (idProvider != null){
            String srcColName = idProvider.getSrcColName();
            ID newId = (ID)idProvider.getSrcId(entity);
            ID oldId = (ID)idProvider.getDstId(entity);
            if (!repository.findById(oldId).isPresent()){
                throw new RuntimeException("Could not update: entity does not exist.");
            }
            JpaRepository srcFieldRepository = fieldRepositories.get(srcColName);
            if (!srcFieldRepository.findById(newId).isPresent()){
                throw new RuntimeException("Could not update: entity from another table does not exist.");
            }
            idProvider.setIdAndClearSrcCol(entity);
            try {
                if (!oldId.equals(newId)) {
                    repository.deleteById(oldId);
                }
            }
            catch (Exception ignored) {}
        }
        foreignKeyProviders.forEach(provider -> provider.setIdAndClearSrcCol(entity));
        try {
            return repository.save(entity);
        }
        catch (Exception e){
            Throwable t = e;
            while (t.getCause() != null){
                t = t.getCause();
            }
            throw new RuntimeException(t.getMessage());
        }
    }

    /** column type specifiers: auto, combobox */
    public static class Column {
        private final String name;
        private boolean isAutoGenerated = false;
        private boolean isCombobox = false;
        private boolean isFinal = false;
        private boolean isTime = false;

        public Column(String colName){
            String[] colNameParts = colName.split("-");
            if (colNameParts.length < 1){
                name = "unnamedColumn";
            }
            else {
                name = colNameParts[colNameParts.length - 1];
                for (int i = 0; i < colNameParts.length - 1; i++) {
                    switch (colNameParts[i]){
                        case "auto":
                            isAutoGenerated = true;
                            break;
                        case "combobox":
                            isCombobox = true;
                            break;
                        case "finalCombobox":
                            isFinal = true;
                            isCombobox = true;
                            break;
                        case "time":
                            isTime = true;
                            break;
                        default:
                            throw new RuntimeException("Unknown column type specifier: " + colNameParts[i]);
                    }
                }
            }
        }
        public String getName() {
            return name;
        }
        public boolean isAutoGenerated() {
            return isAutoGenerated;
        }
        public boolean isCombobox() {
            return isCombobox;
        }
        public boolean isFinal() {
            return isFinal;
        }
        public boolean isTime(){
            return isTime;
        }
    }
    private static class IdProvider<Entity>{
        private Method getSrcColMethod;
        private Method getSrcIdFromColMethod;
        private Method setSrcColMethod = null;
        private Method setDstIdMethod = null;
        private Method getDstIdMethod;
        private String srcColName;

        public IdProvider(Class<Entity> entityClass, String srcCol, String srcColMethod, String idCol){
            try {
                srcColName = srcCol;

                String srcMethodName = "get" + Character.toUpperCase(srcCol.charAt(0)) + srcCol.substring(1);
                getSrcColMethod = entityClass.getMethod(srcMethodName);
                Class srcColType = getSrcColMethod.getReturnType();
                getSrcIdFromColMethod = srcColType.getMethod(srcColMethod);

                String setSrcColMethodName = "set" + Character.toUpperCase(srcCol.charAt(0)) + srcCol.substring(1);
                String setDstIdMethodName = "set" + Character.toUpperCase(idCol.charAt(0)) + idCol.substring(1);
                for (Method cur : entityClass.getMethods()){
                    if (setSrcColMethod != null && setDstIdMethod != null){
                        break;
                    }
                    if (cur.getName().equals(setSrcColMethodName)){
                        setSrcColMethod = cur;
                        continue;
                    }
                    if (cur.getName().equals(setDstIdMethodName)){
                        setDstIdMethod = cur;
                    }
                }
                if (setSrcColMethod == null || setDstIdMethod == null){
                    throw new NoSuchMethodException();
                }

                String getDstIdMethodName = "get" + Character.toUpperCase(idCol.charAt(0)) + idCol.substring(1);
                getDstIdMethod = entityClass.getMethod(getDstIdMethodName);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        public String getSrcColName(){
            return srcColName;
        }
        public Object getSrcId(Object entity){
            try {
                Object col = getSrcColMethod.invoke(entity);
                return getSrcIdFromColMethod.invoke(col);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        public Object getDstId(Object entity){
            try {
                return getDstIdMethod.invoke(entity);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        public void setSrcColNull(Object entity){
            try {
                setSrcColMethod.invoke(entity, (Object)null);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        public void setDstId(Object entity, Object newId){
            try {
                setDstIdMethod.invoke(entity, newId);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        public void setIdAndClearSrcCol(Object entity){
            this.setDstId(entity, this.getSrcId(entity));
            this.setSrcColNull(entity);
        }
    }
}
