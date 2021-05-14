package ru.school.database.ui.utils;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.BeanPropertySet;
import com.vaadin.flow.data.binder.PropertyDefinition;
import com.vaadin.flow.data.binder.PropertySet;
import org.vaadin.crudui.crud.CrudListener;
import org.vaadin.crudui.crud.LazyFindAllCrudOperationListener;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;
import org.vaadin.crudui.layout.CrudLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyGridCrud<T> extends GridCrud<T> {
    private static final int PAGE_SIZE = 2;
    private HorizontalLayout pageButtons;
    private int prevNumberOfPages = 0;
    private int currentPage = 1;
    private HorizontalLayout crudFormButtons;

    private void recreatePageButtons(int numberOfRows){
        int numberOfPages = numberOfRows / PAGE_SIZE;
        if (numberOfPages * PAGE_SIZE != numberOfRows){
            numberOfPages++;
        }
        if (numberOfPages != prevNumberOfPages){
            pageButtons.removeAll();
            List<Button> buttons = new ArrayList<>();
            int leftButton = currentPage - 1;
            int rightButton = currentPage + 1;
            for (int i = leftButton; i < 1; i++) {
                Button newButton = new Button();
                newButton.setDisableOnClick(true);
                newButton.click();
                newButton.getStyle().set("background", "#ffffff");
                buttons.add(newButton);
            }
            for (int i = Math.max(leftButton, 1); i <= Math.min(rightButton, numberOfPages); i++) {
                Button newButton = new Button(String.valueOf(i));
                buttons.add(newButton);
                if (i == currentPage){
                    newButton.setDisableOnClick(true);
                    newButton.click();
                }
                else {
                    final int finalI = i;
                    newButton.addClickListener(event -> {
                        currentPage = finalI;
                        this.refreshGrid();
                    });
                }
            }
            for (int i = numberOfPages; i < rightButton; i++) {
                Button newButton = new Button();
                newButton.setDisableOnClick(true);
                newButton.click();
                newButton.getStyle().set("background", "#ffffff");
                buttons.add(newButton);
            }
            pageButtons.add(buttons.toArray(new Button[0]));
        }
    }

    public MyGridCrud(Class<T> domainType) {
        super(domainType);
    }
    public MyGridCrud(Class<T> domainType, CrudLayout crudLayout) {
        super(domainType, crudLayout);
    }
    public MyGridCrud(Class<T> domainType, CrudFormFactory<T> crudFormFactory) {
        super(domainType, crudFormFactory);
    }
    public MyGridCrud(Class<T> domainType, CrudListener<T> crudListener) {
        super(domainType, crudListener);
    }
    public MyGridCrud(Class<T> domainType, CrudLayout crudLayout, CrudFormFactory<T> crudFormFactory) {
        super(domainType, crudLayout, crudFormFactory);
    }
    public MyGridCrud(Class<T> domainType, CrudLayout crudLayout, CrudFormFactory<T> crudFormFactory, CrudListener<T> crudListener) {
        super(domainType, crudLayout, crudFormFactory, crudListener);
    }

    @Override
    public void refreshGrid() {
        if (LazyFindAllCrudOperationListener.class.isAssignableFrom(this.findAllOperation.getClass())) {
            LazyFindAllCrudOperationListener findAll = (LazyFindAllCrudOperationListener)this.findAllOperation;
            this.grid.setDataProvider(findAll.getDataProvider());
        } else {
            Collection<T> items = this.findAllOperation.findAll();
            recreatePageButtons(items.size());
            List<T> pageItems = new ArrayList<>();
            int itemNumber = 0;
            for (T item : items){
                if (itemNumber < (currentPage - 1) * PAGE_SIZE){
                    ++itemNumber;
                    continue;
                }
                pageItems.add(item);
                if (++itemNumber >= currentPage * PAGE_SIZE){
                    break;
                }
            }
            this.grid.setItems(pageItems);
        }

    }

    @Override
    protected void initLayout() {
        crudFormButtons = new HorizontalLayout();
        this.findAllButton = new Button(VaadinIcon.REFRESH.create(), (e) -> {
            this.findAllButtonClicked();
        });
        this.findAllButton.getElement().setAttribute("title", "Refresh list");
        this.crudLayout.addToolbarComponent(this.findAllButton);
        this.addButton = new Button(VaadinIcon.PLUS.create(), (e) -> {
            this.addButtonClicked();
        });
        this.addButton.getElement().setAttribute("title", "Add");
        this.crudLayout.addToolbarComponent(this.addButton);
        this.updateButton = new Button(VaadinIcon.PENCIL.create(), (e) -> {
            this.updateButtonClicked();
        });
        this.updateButton.getElement().setAttribute("title", "Update");
        this.crudLayout.addToolbarComponent(this.updateButton);
        this.deleteButton = new Button(VaadinIcon.TRASH.create(), (e) -> {
            this.deleteButtonClicked();
        });
        this.deleteButton.getElement().setAttribute("title", "Delete");
        this.crudLayout.addToolbarComponent(this.deleteButton);

        this.grid = new Grid(this.domainType);
        this.pageButtons = new HorizontalLayout();
        VerticalLayout gridPart = new VerticalLayout(this.grid, pageButtons, crudFormButtons);

        this.grid.addSelectionListener((e) -> {
            this.gridSelectionChanged();
        });
        this.crudLayout.setMainComponent(gridPart);
        this.updateButtons();
    }

    public MyGridCrud<T> addCRUDFormButton(String buttonName, GridCrud crud){
        Dialog dialog = new Dialog(crud);
        dialog.setWidth("70%");
        dialog.setHeight("70%");
        crudFormButtons.add(new Button(buttonName, event -> dialog.open()));
        return this;
    }
}
