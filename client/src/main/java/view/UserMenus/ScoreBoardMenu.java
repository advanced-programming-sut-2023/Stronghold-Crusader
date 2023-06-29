package view.UserMenus;

import controller.UserControllers.MainController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
import view.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class ScoreBoardMenu extends Application {
    public Pane rootPane;
    public TableView<ScoreBoardTable> table;
    public TableColumn<ScoreBoardTable, Circle> avatarColumn;
    public TableColumn<ScoreBoardTable, Integer> rankColumn;
    public TableColumn<ScoreBoardTable, String> usernameColumn;
    public TableColumn<ScoreBoardTable, Integer> highScoreColumn;
    public TableColumn<ScoreBoardTable, ImageView> connectionColumn;
    public TableColumn<ScoreBoardTable, ImageView> televisionColumn;
    public TableColumn<ScoreBoardTable, Button> selectColumn;
    private final ObservableList<ScoreBoardTable> rankingTable = FXCollections.observableArrayList();


    @Override
    public void start(Stage stage) throws Exception {
        rootPane = FXMLLoader.load(
                new URL(ScoreBoardMenu.class.getResource("/FXML/ScoreboardMenu.fxml").toExternalForm()));
        rootPane.setBackground(new Background(new BackgroundImage(new Image(
                ProfileMenu.class.getResource("/assets/backgrounds/profileMenu.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1, 1, true, true, false, false))));
        Scene scene = new Scene(rootPane);
        scene.getStylesheets().add(ScoreBoardMenu.class.getResource("/CSS/scoreboardStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setFullScreen(true);
        stage.show();
    }

    @FXML
    public void initialize() {
        rankColumn.setCellValueFactory(new PropertyValueFactory<ScoreBoardTable, Integer>("rank"));
        avatarColumn.setCellValueFactory(new PropertyValueFactory<ScoreBoardTable, Circle>("avatar"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<ScoreBoardTable, String>("username"));
        highScoreColumn.setCellValueFactory(new PropertyValueFactory<ScoreBoardTable, Integer>("highScore"));
        connectionColumn.setCellValueFactory(new PropertyValueFactory<ScoreBoardTable, ImageView>("connectionMode"));
        televisionColumn.setCellValueFactory(new PropertyValueFactory<ScoreBoardTable, ImageView>("television"));
        selectColumn.setCellValueFactory(new PropertyValueFactory<ScoreBoardTable, Button>("select"));
        addUsers();
        makeTimeLineForUpdatingData();
    }

    private void makeTimeLineForUpdatingData() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), e -> {
            addUsers();
        }));
        timeline.setCycleCount(-1);
        timeline.play();
    }

    private void addUsers() {
        rankingTable.clear();
        table.getItems().clear();
        ArrayList<User> ranking = Stronghold.getInstance().getUserRankings();
        for (User user : ranking) {
            rankingTable.add(new ScoreBoardTable(user));
        }
        table.setItems(rankingTable);
    }

    public void openProfile(MouseEvent mouseEvent) throws IOException {
        if (table.getSelectionModel().getSelectedItem() != null) {
            User user = Stronghold.getInstance().getUser(table.getSelectionModel().getSelectedItem().getUsername());
            MenusUtils.createProfileShowPopUp(user, MainController.getCurrentUser().isFriend(user)).show(Main.mainStage);
        }
    }

    public void back(ActionEvent actionEvent) throws Exception {
        new MainMenu().start(Main.mainStage);
    }

    public void update(ActionEvent actionEvent) {
        addUsers();
    }
}
