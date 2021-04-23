package ru.school.database.ui.utils;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;

import java.util.HashMap;
import java.util.Map;

public class TabsVerticalLayout extends HorizontalLayout {
    private Tabs tabs = new Tabs();
    private Div pages = new Div();
    private Map<Tab, Component> tabsToPages = new HashMap<>();

    private void onSelectedTabChange(){
        tabsToPages.values().forEach(page -> page.setVisible(false));
        Tab selectedTab = tabs.getSelectedTab();
        if (selectedTab != null) {
            tabsToPages.get(selectedTab).setVisible(true);
        }
    }

    public TabsVerticalLayout(){
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.setFlexGrowForEnclosedTabs(1);
        tabs.addSelectedChangeListener(event -> onSelectedTabChange());
        onSelectedTabChange();
        this.setSizeFull();
        this.add(tabs, pages);
        tabs.getStyle().set("background", "#f0f0f0");
        tabs.setWidth("200px");
        pages.setWidthFull();
        pages.setHeightFull();
    }
    public TabsVerticalLayout addTab(String tabName, Component page){
        Tab tab = new Tab(tabName);
        tabsToPages.put(tab, page);
        tabs.add(tab);
        pages.add(page);
        if (tabsToPages.size() == 1){
            tab.setSelected(true);
        }
        onSelectedTabChange();
        return this;
    }
}
