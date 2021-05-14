package ru.school.database.backend.filteringUtils;

public class SearchCriteria {
    private String key;
    private ObjectProvider valueProvider;

    public SearchCriteria(String key, ObjectProvider valueProvider) {
        this.key = key;
        this.valueProvider = valueProvider;
    }
    public String getKey(){
        return key;
    }
    public Object getValue() throws InvalidObjectException {
        return valueProvider.getObject();
    }
}
