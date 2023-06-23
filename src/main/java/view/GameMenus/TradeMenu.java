package view.GameMenus;

import controller.GameControllers.TradeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.User.Player;
import model.enums.AssetType.Material;
import utils.SignupAndLoginUtils;
import view.Menu;
import view.enums.commands.TradeMenuCommand;
import view.enums.messages.TradeMenuMessage;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;

public class TradeMenu extends Application {
    public Button donateButton;
    public Button requestButton;
    public Text number;
    public Circle acceptedMaterial;
    public Button confirmButton;
    TradeController tradeController;
    private final HashMap<Circle, Material> materials = new HashMap<>();

    Stage stage = new Stage();

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        AnchorPane rootPane =
                FXMLLoader.load(TradeMenu.class.getResource("/FXML/Gamefxml/TradeMenus/tradeMenu.fxml"));
        stage.setScene(new Scene(rootPane));
        stage.show();
    }

    public TradeMenu(TradeController tradeController) {
        this.tradeController = tradeController;
    }

    public String run() {
        System.out.println(tradeController.showNewTradesForPlayer());
        String nextCommand;
        Matcher matcher;
        while (true) {
            nextCommand = Menu.getScanner().nextLine();
            TradeMenuCommand typeOfCommand = TradeMenuCommand.getCommand(nextCommand);
            if (typeOfCommand == null) {
                TradeMenuMessage.INVALID_COMMAND.printMessage();
                continue;
            }
            matcher = TradeMenuCommand.getMatcher(nextCommand, typeOfCommand);
            switch (typeOfCommand) {
                case REQUEST:
                    requestRun(matcher);
                    break;
                case ACCEPT_TRADE:
                    acceptTradeRun(matcher);
                    break;
                case TRADE_LIST:
                    tradeListRun();
                    break;
                case TRADE_HISTORY:
                    tradeHistoryRun();
                    break;
                case BACK:
                    System.out.println(TradeMenuMessage.ENTER_MAIN);
                    return "back";
            }
        }
    }

    private void requestRun(Matcher matcher) {
        HashMap<String, String> inputs = SignupAndLoginUtils.getInputs(matcher, TradeMenuCommand.REQUEST.getRegex());
        tradeController.request(inputs).printMessage();
    }

    private void tradeListRun() {
        System.out.println(tradeController.showAllTrades());
    }

    private void tradeHistoryRun() {
        System.out.println(tradeController.tradeHistory());
    }

    private void acceptTradeRun(Matcher matcher) {
        HashMap<String, String> inputs = SignupAndLoginUtils.getInputs(matcher, TradeMenuCommand.ACCEPT_TRADE.getRegex());
        tradeController.accept_trade(inputs).printMessage();
    }


    public void newTrade(MouseEvent mouseEvent) {
        AnchorPane anchorPane = new AnchorPane();
        HBox hBox = new HBox();
        anchorPane.setMaxHeight(500);
        anchorPane.setMaxWidth(800);
        anchorPane.getChildren().add(hBox);
        createAvatarsAndSelect(hBox);
        stage.setScene(new Scene(anchorPane));
    }

    private void createAvatarsAndSelect(HBox hBox) {
        for (Player player : tradeController.getGame().getPlayers()) {
            if (player.equals(tradeController.getGame().getCurrentPlayer())) continue;
            ImageView imageView = new ImageView();
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            imageView.setImage(new Image(player.getAvatarPath()));
            hBox.getChildren().add(imageView);
            imageView.setOnMouseClicked(mouseEvent -> {
                try {
                    createNewTrade(player);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private void createNewTrade(Player player) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(TradeMenu.class.getResource("/FXML/Gamefxml/TradeMenus/tradeMenu.fxml"));
        donateButton.setDisable(true);
        requestButton.setDisable(true);
        confirmButton.setDisable(true);
        int counter = 0;
        for (Material material : Material.values()) {
            Circle circle = new Circle(23);
            circle.setFill(new ImagePattern(material.getImage()));
            circle.setTranslateX(87 + 70 * counter);
            circle.setTranslateY(63 + (int) (counter / 9) * 65);
            circle.setOnMouseClicked(mouseEvent -> {
                acceptedMaterial = circle;
                donateButton.setDisable(false);
                requestButton.setDisable(false);
            });
            materials.put(circle, material);
            counter++;
        }
        anchorPane.getChildren().addAll(materials.keySet());
        stage.setScene(new Scene(anchorPane));

    }

    public void plus(MouseEvent mouseEvent) {
        int num = Integer.parseInt(number.getText());
        number.setText(Integer.valueOf(num + 1).toString());
    }

    public void minus(MouseEvent mouseEvent) {
        int num = Integer.parseInt(number.getText());
        if (num == 0) num += 1;
        number.setText(Integer.valueOf(num - 1).toString());

    }
}
