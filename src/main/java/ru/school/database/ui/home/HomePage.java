package ru.school.database.ui.home;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;

import java.util.HashMap;
import java.util.Map;

public class HomePage extends HorizontalLayout {
    public HomePage(){
        Map<Tab, Component> tabsToComponents = new HashMap<>();

        Tab t1 = new Tab("t1");
        Label label = new Label("home page -> tab 1");
        tabsToComponents.put(t1, label);
        Tab t2 = new Tab("t2");
        Button button = new Button("home page -> tab2", event -> Notification.show("this is button from tab2"));
        tabsToComponents.put(t2, button);

        Tabs tabs = new Tabs(t1, t2);
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.addSelectedChangeListener(event -> {
            tabsToComponents.values().forEach(component -> component.setVisible(false));
            tabsToComponents.get(tabs.getSelectedTab()).setVisible(true);
        });
        tabsToComponents.values().forEach(component -> component.setVisible(false));
        tabsToComponents.get(t1).setVisible(true);
        Div pages = new Div(label, button);

        this.add(tabs, pages);
    }
}
