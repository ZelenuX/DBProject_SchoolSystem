package ru.school.database.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.crudui.crud.impl.GridCrud;
import ru.school.database.backend.controllers.PeopleService;
import ru.school.database.backend.controllers.StudentsService;
import ru.school.database.backend.controllers.TeachersService;
import ru.school.database.backend.entities.Students;
import ru.school.database.ui.home.HomePage;
import ru.school.database.ui.utils.CRUDFormBuilder;
import ru.school.database.ui.utils.TabsVerticalLayout;

import java.util.HashMap;
import java.util.Map;

@org.springframework.stereotype.Component
@Route("")
@UIScope
public class MainView extends AppLayout {
    private Map<Tab, Component> tabsToComponents = new HashMap<>();
    private Tabs tabs = new Tabs();
    private Div pages = new Div();

    private final PeopleService peopleService;
    private final StudentsService studentsService;
    private final TeachersService teachersService;

    private void addSuperTab(String name, Component page){
        Tab tab = new Tab(name);
        tabsToComponents.put(tab, page);
        tabs.add(tab);
        pages.add(page);
    }
    private void configurePagesAndTabs(){
        tabs.setWidthFull();
        tabs.setFlexGrowForEnclosedTabs(1);
        pages.setSizeFull();

        tabs.addSelectedChangeListener(event -> {
            Component selectedPage = tabsToComponents.get(tabs.getSelectedTab());
            tabsToComponents.values().forEach(component -> component.setVisible(component == selectedPage));
        });
        Component selectedPage = tabsToComponents.get(tabs.getSelectedTab());
        tabsToComponents.values().forEach(component -> component.setVisible(component == selectedPage));

        tabs.getStyle().set("background", "#e0e0e0");
    }

    private void addTeacherSuperTab(){
        addSuperTab("Teacher",
                new TabsVerticalLayout()
                        .addTab("profile", new Label("Teacher profile"))
                        .addTab("students", new Label("You don't have any students for now (in dev.)")));
    }
    private void addStudentSuperTab(){
        addSuperTab("Student",
                new TabsVerticalLayout()
                        .addTab("profile", new Label("Student profile"))
                        .addTab("marks", new Label("Student marks")));
    }
    private void addAdminSuperTab(){
        TabsVerticalLayout tabs = new TabsVerticalLayout();
        addSuperTab("Admin", tabs);
        tabs.addTab("people", CRUDFormBuilder.build(peopleService));
        tabs.addTab("students", CRUDFormBuilder.build(studentsService));
        tabs.addTab("teachers", CRUDFormBuilder.build(teachersService));
    }

    public MainView(
            @Autowired PeopleService peopleService,
            @Autowired StudentsService studentsService,
            @Autowired TeachersService teachersService) {
        this.peopleService = peopleService;
        this.studentsService = studentsService;
        this.teachersService = teachersService;
        this.addToNavbar(tabs);
        this.setContent(pages);

        addSuperTab("Home", new HomePage());
        addTeacherSuperTab();
        addStudentSuperTab();
        addAdminSuperTab();

        configurePagesAndTabs();
    }
}
