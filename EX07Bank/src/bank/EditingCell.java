package bank;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;

public class EditingCell extends TextFieldTableCell<Card, String> {

    private TextField textField;

    @Override
    public void startEdit() {
        TableRow<Card> tableRow = getTableRow();
        Card bc = tableRow.getItem();

        if (bc.getCardType().equals("Credit card") || !getTableColumn().getId().equals("creditLimitColumn")) {
            super.startEdit();
            createTextField();
            setText(null);
            setGraphic(textField);
            textField.selectAll();
        }
    }

    private void createTextField() {
        textField = new TextField(getString());
        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        textField.focusedProperty().addListener(
                (ObservableValue<? extends Boolean> arg0,
                 Boolean arg1, Boolean arg2) -> {
                    if (!arg2) {
                        commitEdit(textField.getText());
                    }
                });

        setOnKeyPressed(ke -> {
            KeyCode keyCode = ke.getCode();
            if (keyCode.equals(KeyCode.ENTER)){
                commitEdit(textField.getText());
            }
        });

    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}
