package view.GameMenus.TradeMenus;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.Game.Trade;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RequestMenu extends Application implements Initializable {
    public TableView<TableItem> requestTrades;
    public TableColumn<TableItem, String> typeColumn;

    public TableColumn<TableItem, ImageView> stateColumn;
    public TableColumn<TableItem, Integer> IDColumn;
    public TableColumn<TableItem, String> acceptorColumn;
    public TableColumn<TableItem, Circle> goodColumn;
    public TableColumn<TableItem, Integer> amountColumn;
    public TableColumn<TableItem, String> messageColumn;
    private static Stage stage;
    public TableColumn<TableItem, Button> denyColumn;
    public TableColumn<TableItem, Button> acceptColumn;

    @Override
    public void start(Stage newStage) throws IOException {
        stage = newStage;
        AnchorPane anchorPane = FXMLLoader.load(myTradeList.class.getResource("/FXML/Gamefxml/TradeMenus/RequestMenu.fxml"));
        Scene scene = new Scene(anchorPane);
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<TableItem> tradeList = FXCollections.observableArrayList();
        stateColumn.setCellValueFactory(new PropertyValueFactory<TableItem, ImageView>("state"));
        IDColumn.setCellValueFactory(new PropertyValueFactory<TableItem, Integer>("ID"));
        acceptorColumn.setCellValueFactory(new PropertyValueFactory<TableItem, String>("owner"));
        goodColumn.setCellValueFactory(new PropertyValueFactory<TableItem, Circle>("good"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<TableItem, Integer>("amount"));
        messageColumn.setCellValueFactory(new PropertyValueFactory<TableItem, String>("message"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<TableItem, String>("type"));
        acceptColumn.setCellValueFactory(new PropertyValueFactory<TableItem, Button>("acceptButton"));
        denyColumn.setCellValueFactory(new PropertyValueFactory<TableItem, Button>("denyButton"));


        ArrayList<Trade> trades = new ArrayList<>(TradeMenu.getTradeController().getTradesToMe());
        for (Trade trade : trades) {
            TableItem tableItem = new TableItem(trade);
            tradeList.add(tableItem);
        }

        requestTrades.setItems(tradeList);
    }



    public void back(MouseEvent mouseEvent) throws Exception {
        new TradeMenu().start(stage);
    }
}
