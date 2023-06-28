package view.UserMenus;

import controller.UserControllers.FriendsMenuController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.User.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FriendsMenu extends Application implements Initializable {
    private static Stage stage;
    private static FriendsMenuController friendsMenuController;

    public TableView<FriendsTable> table;
    public TableColumn<FriendsTable, Circle> avatarColumn;
    public TableColumn<FriendsTable, String> usernameColumn;
    public TableColumn<FriendsTable, Button> selectColumn;

    public ObservableList<FriendsTable> showList = FXCollections.observableArrayList();
    public TextField searchBox;


    @Override
    public void start(Stage stage) throws Exception {
        Pane rootPane = FXMLLoader.load(FriendsMenu.class.getResource("/FXML/Userfxml/FriendMenu/FriendsMenu.fxml"));
        rootPane.setBackground(new Background(new BackgroundImage(new Image(
                ProfileMenu.class.getResource("/assets/backgrounds/profileMenu.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1, 1, true, true, false, false))));
        stage.setScene(new Scene(rootPane));
        FriendsMenu.stage = stage;
        stage.setFullScreen(true);
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        avatarColumn.setCellValueFactory(new PropertyValueFactory<FriendsTable, Circle>("avatar"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<FriendsTable, String>("username"));
        selectColumn.setCellValueFactory(new PropertyValueFactory<FriendsTable, Button>("accept"));
        searchBox.textProperty().addListener((observable, oldText, newText) ->
                translateUserToTableItem(friendsMenuController.getUsersFromText(searchBox.getText())));
                table.setItems(showList);
        }
    private void translateUserToTableItem(ArrayList<User> users) {
        showList.clear();
        for (User user: users) {
            showList.add(new FriendsTable(user));
        }
    }

    public static void setFriendsMenuController(FriendsMenuController friendsMenuController) {
        FriendsMenu.friendsMenuController = friendsMenuController;
    }

    public void back(ActionEvent actionEvent) throws Exception {
        new ProfileMenu().start(stage);
    }

    public static Stage getStage() {
        return stage;
    }
}
