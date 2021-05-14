package ru.school.database.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import ru.school.database.backend.forComplexQueries.BestStudent;
import ru.school.database.backend.forComplexQueries.SpecialInfoExtractor;
import ru.school.database.backend.controllers.*;
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
    private Component buildBestStudentsPage(){
        Grid<BestStudent> bestStudentGrid = new Grid<>(BestStudent.class);
        bestStudentGrid.setColumns("firstName", "secondName", "lastName", "averageValue");
        bestStudentGrid.setSortableColumns("firstName", "secondName", "lastName");
        Button refreshButton = new Button("refresh", event -> {
            bestStudentGrid.setItems(specialInfoExtractor.getBestStudents());
            Notification.show("best found");
        });
        bestStudentGrid.setItems(specialInfoExtractor.getBestStudents());
        return new VerticalLayout(refreshButton, bestStudentGrid);
    }

    private void addTeacherSuperTab(){
        addSuperTab("Teacher", new TabsVerticalLayout()
                .addTab("best students", buildBestStudentsPage())
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
        tabs.addTab("teachers (i)", CRUDFormBuilder.buildIdeal(teachersService)
                .addCRUDFormButton("people", CRUDFormBuilder.buildHorizontal(peopleService)));
        tabs.addTab("students (i)", CRUDFormBuilder.buildIdeal(studentsService)
                .addCRUDFormButton("people", CRUDFormBuilder.buildHorizontal(peopleService)));
        tabs.addTab("final marks (i)", CRUDFormBuilder.buildIdeal(finalMarksService)
                .addCRUDFormButton("students", CRUDFormBuilder.buildHorizontal(studentsService))
                .addCRUDFormButton("disciplines", CRUDFormBuilder.buildHorizontal(disciplinesService)));
        tabs.addTab("lesson marks (i)", CRUDFormBuilder.buildIdeal(lessonMarksService)
                .addCRUDFormButton("students", CRUDFormBuilder.buildHorizontal(studentsService))
                .addCRUDFormButton("lessons", CRUDFormBuilder.buildHorizontal(lessonsService)));
        tabs.addTab("people", CRUDFormBuilder.build(peopleService));
        tabs.addTab("students", CRUDFormBuilder.build(studentsService));
        tabs.addTab("teachers", CRUDFormBuilder.build(teachersService));
        tabs.addTab("disciplines", CRUDFormBuilder.build(disciplinesService));
        tabs.addTab("teachers_disciplines", CRUDFormBuilder.build(teachersDisciplinesService));
        tabs.addTab("programs", CRUDFormBuilder.build(programsService));
        tabs.addTab("students_programs", CRUDFormBuilder.build(studentsProgramsService));
        tabs.addTab("helpers", CRUDFormBuilder.build(helpersService));
        tabs.addTab("programs_teachers_disciplines", CRUDFormBuilder.build(programsDisciplinesService));
        tabs.addTab("timetable", CRUDFormBuilder.build(timetableService));
        tabs.addTab("lessons", CRUDFormBuilder.build(lessonsService));
        tabs.addTab("lesson marks", CRUDFormBuilder.build(lessonMarksService));
        tabs.addTab("home tasks", CRUDFormBuilder.build(homeTasksService));
        tabs.addTab("homeworks", CRUDFormBuilder.build(homeworksService));
        tabs.addTab("final marks", CRUDFormBuilder.build(finalMarksService));
    }

    private final PeopleService peopleService;
    private final StudentsService studentsService;
    private final TeachersService teachersService;
    private final DisciplinesService disciplinesService;
    private final TeachersDisciplinesService teachersDisciplinesService;
    private final HelpersService helpersService;
    private final ProgramsService programsService;
    private final StudentsProgramsService studentsProgramsService;
    private final ProgramsDisciplinesService programsDisciplinesService;
    private final TimetableService timetableService;
    private final LessonsService lessonsService;
    private final HomeTasksService homeTasksService;
    private final FinalMarksService finalMarksService;
    private final LessonMarksService lessonMarksService;
    private final HomeworksService homeworksService;
    private final SpecialInfoExtractor specialInfoExtractor;

    public MainView(
            @Autowired PeopleService peopleService,
            @Autowired StudentsService studentsService,
            @Autowired TeachersService teachersService,
            @Autowired DisciplinesService disciplinesService,
            @Autowired TeachersDisciplinesService teachersDisciplinesService,
            @Autowired HelpersService helpersService,
            @Autowired ProgramsService programsService,
            @Autowired StudentsProgramsService studentsProgramsService,
            @Autowired ProgramsDisciplinesService programsDisciplinesService,
            @Autowired TimetableService timetableService,
            @Autowired LessonsService lessonsService,
            @Autowired HomeTasksService homeTasksService,
            @Autowired FinalMarksService finalMarksService,
            @Autowired LessonMarksService lessonMarksService,
            @Autowired HomeworksService homeworksService,
            @Autowired SpecialInfoExtractor specialInfoExtractor) {
        this.peopleService = peopleService;
        this.studentsService = studentsService;
        this.teachersService = teachersService;
        this.disciplinesService = disciplinesService;
        this.teachersDisciplinesService = teachersDisciplinesService;
        this.helpersService = helpersService;
        this.programsService = programsService;
        this.studentsProgramsService = studentsProgramsService;
        this.programsDisciplinesService = programsDisciplinesService;
        this.timetableService = timetableService;
        this.lessonsService = lessonsService;
        this.homeTasksService = homeTasksService;
        this.finalMarksService = finalMarksService;
        this.lessonMarksService = lessonMarksService;
        this.homeworksService = homeworksService;
        this.specialInfoExtractor = specialInfoExtractor;
        this.addToNavbar(tabs);
        this.setContent(pages);

        addSuperTab("Home", new HomePage());
        addTeacherSuperTab();
        addStudentSuperTab();
        addAdminSuperTab();

        configurePagesAndTabs();
    }
}
