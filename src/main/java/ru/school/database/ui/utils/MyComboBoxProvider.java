package ru.school.database.ui.utils;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.crudui.form.impl.field.provider.AbstractListingProvider;

import java.util.Collection;

public class MyComboBoxProvider<T> extends AbstractListingProvider<ComboBox<T>, T> {
    private ItemLabelGenerator<T> itemLabelGenerator;
    private JpaRepository repository;
    private boolean readOnly = false;

    public MyComboBoxProvider(JpaRepository repository, boolean readOnly) {
        super(repository.findAll());
        this.repository = repository;
        this.readOnly = readOnly;
    }

    public MyComboBoxProvider(String caption, JpaRepository repository) {
        super(caption, repository.findAll());
    }

    public MyComboBoxProvider(String caption, JpaRepository repository,
                              ComponentRenderer<? extends Component, T> renderer, ItemLabelGenerator<T> itemLabelGenerator) {
        super(caption, repository.findAll(), renderer);
        this.itemLabelGenerator = itemLabelGenerator;
    }

    protected ComboBox<T> buildAbstractListing() {
        this.items = repository.findAll();
        ComboBox<T> field = new ComboBox();
        if (this.renderer != null) {
            field.setRenderer(this.renderer);
        }

        if (this.itemLabelGenerator != null) {
            field.setItemLabelGenerator(this.itemLabelGenerator);
        }

        field.setItems(this.items);
        //field.setEnabled(!readOnly);
        //field.setReadOnly(readOnly);
        return field;
    }
}
