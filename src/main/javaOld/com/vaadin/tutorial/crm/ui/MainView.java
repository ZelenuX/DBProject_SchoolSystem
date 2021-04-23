package com.vaadin.tutorial.crm.ui;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.backend.controllers.PeopleService;
import com.vaadin.tutorial.crm.backend.entities.Flat;
import com.vaadin.tutorial.crm.backend.entities.Person;

@Route("")
public class MainView extends VerticalLayout {
    private PeopleService peopleService;
    private Grid<Person> personGrid = new Grid<>(Person.class);
    private TextField filterNameField = new TextField();

    private void configureGrid(){
        personGrid.addClassName("contact-grid");
        personGrid.setSizeFull();
        personGrid.setColumns("name", "surname", "age");

        //personGrid.removeColumnByKey("flat");
        personGrid.addColumn(person -> {
            Flat flat = person.getFlat();
            return flat == null ? "-" : String.valueOf(flat.getPrice()) + " $";
        }).setHeader("flat");
    }
    private void updateGrid(){
        personGrid.setItems(peopleService.findAll(filterNameField.getValue()));
    }
    private void configureFilter(){
        filterNameField.setPlaceholder("Filter by name...");
        filterNameField.setClearButtonVisible(true);
        filterNameField.setValueChangeMode(ValueChangeMode.LAZY);
        filterNameField.addValueChangeListener(e -> updateGrid());
    }

    public MainView(PeopleService peopleService) {
        this.peopleService = peopleService;
        TextField textField = new TextField("Your name");
        textField.addThemeName("bordered");
        Button button = new Button("Say hello",
                e -> Notification.show(textField.getValue()));
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.addClickShortcut(Key.ENTER);
        addClassName("centered-content");
        setSizeFull();
        configureGrid();
        configureFilter();
        add(textField, button, filterNameField, personGrid);
        updateGrid();
    }

}
