package view.GameMenus.TradeMenus;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Game.Trade;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DonateMenu extends Application implements Initializable {

    public TableView<TableItem> donateTrades;
    @FXML
    private TableColumn<TableItem, String> stateColumn;
    public TableColumn<TableItem, Integer> IDColumn;
    public TableColumn<TableItem, String> acceptorColumn;
    public TableColumn<TableItem, String> goodColumn;
    public TableColumn<TableItem, Integer> amountColumn;
    public TableColumn<TableItem, String> messageColumn;


    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane anchorPane = FXMLLoader.load(DonateMenu.class.getResource("/FXML/Gamefxml/TradeMenus/DonatesMenu.fxml"));
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<TableItem> tradeList = FXCollections.observableArrayList();
        stateColumn.setCellValueFactory(new PropertyValueFactory<TableItem, String>("State"));
        IDColumn.setCellValueFactory(new PropertyValueFactory<TableItem, Integer>("ID"));
        acceptorColumn.setCellValueFactory(new PropertyValueFactory<TableItem, String>("To"));
        goodColumn.setCellValueFactory(new PropertyValueFactory<TableItem, String>("Good"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<TableItem, Integer>("Amount"));
        messageColumn.setCellValueFactory(new PropertyValueFactory<TableItem, String>("Message"));

        ArrayList<Trade> trades = new ArrayList<>(TradeMenu.getTradeController().getDonates());
        for (Trade trade : trades) {
            TableItem tableItem = new TableItem("Yes", trade.getID(), trade.getOwner().getNickname(),
                    trade.getMaterial().toString(), trade.getAmount(), trade.getMessage());
            tradeList.add(tableItem);
        }

        donateTrades.setItems(tradeList);
    }
}
