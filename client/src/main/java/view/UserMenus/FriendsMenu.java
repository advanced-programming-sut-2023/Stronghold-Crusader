package view.UserMenus;

import controller.UserControllers.FriendsMenuController;
import controller.UserControllers.MainController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Stronghold;
import model.User.User;
import utils.MenusUtils;

import java.io.IOException;
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
    public TableColumn<FriendsTable, ImageView> isFriendColumn;
    public TableColumn<FriendsTable, String> emailColumn;
    public ArrayList<User> senders = new ArrayList<>();


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
        createSenders();
        avatarColumn.setCellValueFactory(new PropertyValueFactory<FriendsTable, Circle>("avatar"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<FriendsTable, String>("username"));
        selectColumn.setCellValueFactory(new PropertyValueFactory<FriendsTable, Button>("accept"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<FriendsTable, String>("email"));
        isFriendColumn.setCellValueFactory(new PropertyValueFactory<FriendsTable, ImageView>("isFriend"));
        updateSendersAndFriends();
        searchBox.textProperty().addListener((observable, oldText, newText) ->
                translateUserToTableItem(friendsMenuController.getUsersFromText(searchBox.getText())));
        table.setItems(showList);

    }

    private void updateSendersAndFriends() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), e -> {
            MainController.currentUser.updateLists();
            createSenders();
            friendsMenuController.createFriends();
        }));
        timeline.setCycleCount(-1);
        timeline.play();
    }

    private void createSenders() {
        for (String username : MainController.currentUser.getSenders())
            senders.add(Stronghold.getInstance().getUser(username));
    }


    private void translateUserToTableItem(ArrayList<User> users) {
        showList.clear();
        table.getItems().clear();
        for (User user : users) {
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

    public void openProfile(MouseEvent mouseEvent) throws IOException {
        if (table.getSelectionModel().getSelectedItem() != null) {
            User user = Stronghold.getInstance().getUser(table.getSelectionModel().getSelectedItem().getUsername());
            MenusUtils.createProfileShowPopUp(user, MainController.getCurrentUser().isFriend(user)).show(stage);
        }
    }

    public void showSenders(ActionEvent actionEvent) {
        translateUserToTableItem(senders);
        table.setItems(showList);
    }

    public void showFriends(ActionEvent actionEvent) {
        translateUserToTableItem(friendsMenuController.getUsersFromText(""));
        table.setItems(showList);
    }
}
