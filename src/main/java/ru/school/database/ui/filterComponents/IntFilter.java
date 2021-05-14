package ru.school.database.ui.filterComponents;

import com.vaadin.flow.component.textfield.TextField;
import ru.school.database.backend.filteringUtils.InvalidObjectException;

public class IntFilter implements Filter {
    private TextField textField = new TextField();

    public IntFilter(String placeHolder){
        textField.setPlaceholder(placeHolder);
        textField.setClearButtonVisible(true);
    }
    @Override
    public TextField getTextField() {
        return textField;
    }
    @Override
    public Object getObject() throws InvalidObjectException {
        if (textField.isEmpty()){
            return null;
        }
        try {
            return Integer.parseInt(textField.getValue());
        }
        catch (NumberFormatException e){
            textField.setValue("Invalid format");
            throw new InvalidObjectException();
        }
    }
}
