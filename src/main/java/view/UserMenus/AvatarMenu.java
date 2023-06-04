package view.UserMenus;

import controller.UserControllers.AvatarController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Stronghold;
import model.User.User;

import java.io.File;

public class AvatarMenu extends Application {
    private static Stage stage;
    public VBox othersAvatarVbox;
    public GridPane defaultsGrid;
    private static AvatarController controller;
    public ImageView avatarDisplay;

    public static void setController(AvatarController controller) {
        AvatarMenu.controller = controller;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane rootPane = FXMLLoader.load(ProfileMenu.class.getResource("/FXML/avatarMenu.fxml"));
        rootPane.setBackground(new Background(new BackgroundImage(new Image(
                ProfileMenu.class.getResource("/assets/backgrounds/profileMenu.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1, 1, true, true, false, false))));
        stage.setScene(new Scene(rootPane));
        stage.setFullScreen(true);
        AvatarMenu.stage = stage;
    }

    @FXML
    private void initialize() {
        updateAvatarDisplay();
        initializeDefaultsGrid();
        initializeOtherUsersAvatarManu();
    }

    private void initializeOtherUsersAvatarManu() {
        for (User user : Stronghold.getInstance().getUsers())
            if (!user.equals(controller.getCurrentUser()))
                othersAvatarVbox.getChildren().add(createRow(user));
    }

    private HBox createRow(User user) {
        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        Text username = new Text(user.getUsername());
        username.setFont(new Font(20));
        ImageView imageView = new ImageView();
        if(user.getAvatarPath() != null)
            imageView.setImage(new Image(user.getAvatarPath()));
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        HBox hBox = new HBox(username, region, imageView);
        hBox.setOnMouseClicked(mouseEvent -> {
            controller.setAvatar(user.getAvatarPath());
            updateAvatarDisplay();
        });
        return hBox;
    }

    private void initializeDefaultsGrid() {
        for (int i = 0; i <= 5; i++)
            defaultsGrid.add(createDefaultAvatar(i + 1), i % 2, i / 2);
    }

    private ImageView createDefaultAvatar(int counter) {
        ImageView output = new ImageView();
        output.setImage(new Image(AvatarMenu.class.getResource("/assets/avatars/defaults/a" + counter + ".jpg").toExternalForm()));
        output.setFitWidth(100);
        output.setFitHeight(100);
        output.setOnMouseClicked(mouseEvent -> {
            controller.setAvatar(output.getImage().getUrl());
            updateAvatarDisplay();
        });
        return output;
    }

    private void updateAvatarDisplay() {
        avatarDisplay.setImage(new Image(controller.getCurrentUser().getAvatarPath()));
    }

    public void uploadAvatarClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("jpg file", "*.jpg")
                , new FileChooser.ExtensionFilter("jpeg file", "*.jpeg")
                , new FileChooser.ExtensionFilter("png file", "*.png")
        );
        File selectedPic = fileChooser.showOpenDialog(stage);
        if (selectedPic == null) return;
        controller.setAvatar(selectedPic.getPath());
        updateAvatarDisplay();
    }
}
