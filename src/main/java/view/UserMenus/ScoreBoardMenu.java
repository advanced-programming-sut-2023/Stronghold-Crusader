package view.UserMenus;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Game.Game;
import model.Stronghold;
import model.User.User;

import java.net.URL;
import java.util.ArrayList;

public class ScoreBoardMenu extends Application {
    private static Stage stage;
    public Pane rootPane;
    public TableView userRanking;

    @Override
    public void start(Stage stage) throws Exception {
        Stronghold.load();
        rootPane = FXMLLoader.load(
                new URL(ScoreBoardMenu.class.getResource("/FXML/ScoreboardMenu.fxml").toExternalForm()));
        rootPane.setBackground(new Background(new BackgroundImage(new Image(
                ProfileMenu.class.getResource("/assets/backgrounds/profileMenu.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1, 1, true, true, false, false))));
        Scene scene = new Scene(rootPane);
        scene.getStylesheets().add(ScoreBoardMenu.class.getResource("/CSS/scoreboardStyle.css").toExternalForm());
        stage.setScene(scene);
        ScoreBoardMenu.stage = stage;
        stage.setResizable(false);
        stage.setFullScreen(true);
        stage.show();
    }

    @FXML
    public void initialize(){
        addUsers();
    }

    private void addUsers() {
        ArrayList<User> ranking = Stronghold.getInstance().getUserRankings();
        ObservableList<User> userList = FXCollections.observableArrayList();
        for (int i = 0; i < ranking.size() && i < 10; i++) {
            User user = ranking.get(i);
            userList.add(user);
        }
        userRanking.setItems(userList);
    }
}
