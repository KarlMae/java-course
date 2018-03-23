package bank;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    TextField limitField;
    @FXML
    TextField numberField;
    @FXML
    TextField nameField;
    @FXML
    ChoiceBox typeBox = new ChoiceBox();
    @FXML
    Button generateRandomNumberButton;
    @FXML
    Button submitButton;
    @FXML
    TableColumn<Card, String> ownerColumn = new TableColumn<>();
    @FXML
    TableColumn<Card, String> typeColumn = new TableColumn<>();
    @FXML
    TableColumn<Card, String> numberColumn = new TableColumn<>();
    @FXML
    TableColumn<Card, String> creditLimitColumn = new TableColumn<>();
    @FXML
    private TableView<Card> table = new TableView<>();

    public void addCard() {

        String creditLimit;
        if (typeBox.getValue().toString().equals("bank card")) {
            creditLimit = "0";
        } else {
            creditLimit = limitField.getText();
        }

        Card card = new Card(nameField.getText(), typeBox.getValue().toString(), numberField.getText(), creditLimit);
        table.getItems().add(card);
        nameField.clear();
        numberField.clear();
        limitField.clear();
        typeBox.setValue(null);
        table.refresh();
    }


    // Get all cards
    public ObservableList<Card> getCard() {
        ObservableList<Card> cards = FXCollections.observableArrayList();
        cards.add(new Card("Karl Mae", "Credit card", "420", "Unlimited"));
        return cards;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.setEditable(true);

        //Table columns
        ownerColumn.setCellValueFactory(new PropertyValueFactory<Card, String>("ownerName"));
        ownerColumn.setCellFactory(TextFieldTableCell.<Card>forTableColumn());
        ownerColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<Card, String> t) -> {
                    ((Card) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setOwnerName(t.getNewValue());
                });

        typeColumn.setCellValueFactory(new PropertyValueFactory<Card, String>("cardType"));

        creditLimitColumn.setCellValueFactory(new PropertyValueFactory<Card, String>("cardNumber"));
        creditLimitColumn.setCellFactory(TextFieldTableCell.<Card>forTableColumn());
        creditLimitColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<Card, String> t) -> {
                    ((Card) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setCredit(t.getNewValue());
                });

        numberColumn.setCellValueFactory(new PropertyValueFactory<Card, String>("cardNumber"));

        table.getItems().setAll(getCard());
    }

    public void generateCardNumber() {
        Random random = new Random();
        long number = (long) (random.nextDouble() * 10000000000000000L);
        numberField.setText(String.valueOf(number));
    }
}
