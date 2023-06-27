package view.GameMenus.TradeMenus;

import controller.GameControllers.TradeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import model.Game.Trade;
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
    private static TradeController tradeController;
    private final HashMap<Paint, Material> materials = new HashMap<>();
    private static Player sentTo;
    private Trade trade;

    private static Stage stage = new Stage();

    @Override
    public void start(Stage newStage) throws Exception {
        stage = newStage;
        AnchorPane rootPane =
                FXMLLoader.load(TradeMenu.class.getResource("/FXML/Gamefxml/TradeMenus/tradeMenu.fxml"));
        stage.setScene(new Scene(rootPane));
        stage.show();
    }


    public String run() {
        // System.out.println(tradeController.showNewTradesForPlayer());
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
                case TRADE_HISTORY:
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


    private void acceptTradeRun(Matcher matcher) {
        HashMap<String, String> inputs = SignupAndLoginUtils.getInputs(matcher, TradeMenuCommand.ACCEPT_TRADE.getRegex());
        tradeController.accept_trade(inputs).printMessage();
    }


    public void newTrade(MouseEvent mouseEvent) {
        AnchorPane anchorPane = new AnchorPane();
        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        hBox1.setTranslateX(50);
        hBox1.setTranslateY(100);
        hBox2.setTranslateY(200);
        hBox2.setTranslateX(50);
        hBox2.setSpacing(100);
        hBox2.setAlignment(Pos.CENTER);
        anchorPane.setMinHeight(500);
        anchorPane.setMinWidth(800);
        anchorPane.getChildren().add(hBox1);
        anchorPane.getChildren().add(hBox2);
        createAvatarsAndSelect(hBox1, hBox2);
        stage.setScene(new Scene(anchorPane));
        stage.show();
    }

    private void createAvatarsAndSelect(HBox hBox, HBox hBox2) {
        for (Player player : tradeController.getGame().getPlayers()) {
            if (player.equals(tradeController.getGame().getCurrentPlayer())) continue;
            ImageView imageView = new ImageView();
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            imageView.setImage(new Image(player.getAvatarPath()));
            hBox.getChildren().add(imageView);
            hBox2.getChildren().add(new Text(player.getNickname()));
            imageView.setOnMouseClicked(mouseEvent -> {
                try {
                    createNewTrade(materials, player);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private void createNewTrade(HashMap<Paint, Material> materials, Player player) throws IOException {
        sentTo = player;
        AnchorPane anchorPane = FXMLLoader.load(TradeMenu.class.getResource("/FXML/Gamefxml/TradeMenus/newTradeMenu.fxml"));
        int counter = 0;
        for (Material material : Material.values()) {
            Circle circle = new Circle(23);
            circle.setFill(new ImagePattern(material.getImage()));
            circle.setTranslateX(87 + 70 * (counter % 9));
            circle.setTranslateY(63 + (int) (counter / 9) * 65);
            circle.setOnMouseClicked(mouseEvent -> {
                ((Circle) anchorPane.getChildren().get(0)).setFill(circle.getFill());
                anchorPane.getChildren().get(1).setDisable(false);
                anchorPane.getChildren().get(2).setDisable(false);
                anchorPane.getChildren().get(3).setDisable(true);
                ((Text) anchorPane.getChildren().get(4)).setText("0");
            });
            materials.put(circle.getFill(), material);
            anchorPane.getChildren().add(circle);
            counter++;
        }
        stage.setScene(new Scene(anchorPane));
        stage.show();
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

    public static void setTradeController(TradeController tradeController) {
        TradeMenu.tradeController = tradeController;
    }

    public void confirmSituation(MouseEvent mouseEvent) {
        boolean requestMode = mouseEvent.getSource().toString().matches("[\\S\\s]*request[\\S\\s]*");
        ImagePattern img = (ImagePattern) acceptedMaterial.getFill();
        String material = getMaterialFromURL(img.getImage().getUrl());
        trade = new Trade(tradeController.getGame().getCurrentPlayer(), sentTo, Material.getMaterial(material),
                Integer.parseInt(number.getText()), requestMode);
        donateButton.setDisable(true);
        requestButton.setDisable(true);
        confirmButton.setDisable(false);
    }

    private String getMaterialFromURL(String url) {
        String[] sections = url.split("/");
        String[] result = sections[sections.length - 1].split("\\.");
        return result[0];
    }

    public void send(MouseEvent mouseEvent) throws IOException {
        number.setText("0");
        String result = trade.isRequest() ? "request" : "donate";
        if (result.equals("donate"))
            createPopUp(tradeController.isMaterialEnough(trade.getMaterial(), trade.getAmount()));
        else
            createPopUp(true);
        donateButton.setDisable(false);
        requestButton.setDisable(false);
        confirmButton.setDisable(true);
        tradeController.addTrade(trade);
    }

    private void createPopUp(Boolean Mode) throws IOException {
        Popup popup = new Popup();
        AnchorPane pane = loadPane(popup, Mode);
        popup.getContent().add(pane);
        popup.show(stage);
    }

    private AnchorPane loadPane(Popup popup, Boolean mode) throws IOException {
        AnchorPane pane;
        if (!mode) {
            pane = FXMLLoader.load(TradeMenu.class.getResource("/FXML/Gamefxml/TradeMenus/ErrorPopUp.fxml"));
            ((Circle) pane.getChildren().get(2)).setFill(acceptedMaterial.getFill());
            popup.setAutoHide(true);
        } else {
            pane = FXMLLoader.load(TradeMenu.class.getResource("/FXML/Gamefxml/TradeMenus/successTrade.fxml"));
            popup.setAutoHide(false);
            ((Button) pane.getChildren().get(1)).setOnMouseClicked(e -> {
                trade.setMessage(((TextField) pane.getChildren().get(2)).getText());
                popup.hide();
            });
        }
        return pane;
    }


    public void back(MouseEvent mouseEvent) throws Exception {
        this.start(stage);
    }

    public void goToDonatesMenu(MouseEvent mouseEvent) throws Exception {
        new DonateMenu().start(stage);
    }

    public static TradeController getTradeController() {
        return tradeController;
    }
}
