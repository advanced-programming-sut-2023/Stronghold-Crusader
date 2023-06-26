package view.GameMenus;

import controller.GameControllers.SelectedBuildingController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.enums.AssetType.MapAssetType;
import utils.SignupAndLoginUtils;
import utils.Sound;
import view.enums.commands.GameCommand.SelectedBuildingCommand;
import view.enums.messages.GameMessage.SelectedBuildingMessage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SelectedBuildingMenu implements Initializable {
    private static SelectedBuildingController selectedBuildingController;
    private static GraphicGameMenu gameMenu ;
    public ImageView repairButton;
    public ProgressBar progressBar;
    public Text progressBarNumber;
    public AnchorPane mainPain;
    public ImageView buildingImage;


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkHP();
        if (selectedBuildingController.getBuilding().getType() != MapAssetType.MERCENARY_POST
                && selectedBuildingController.getBuilding().getType() != MapAssetType.ENGINEER_GUILD
                && selectedBuildingController.getBuilding().getType() != MapAssetType.BARRACK) {
            progressBar.setProgress(selectedBuildingController.getBuilding().getHitPoint()
                    / selectedBuildingController.getBuilding().getMaxHitPoint());
            progressBarNumber.setText(selectedBuildingController.getBuilding().getHitPoint() + "/"
                    + selectedBuildingController.getBuilding().getMaxHitPoint());
            setImage();
        }
    }



    private void runSetTaxRate(Matcher matcher) {
        int rate = Integer.parseInt(matcher.group("rate"));
        selectedBuildingController.setTaxRate(rate).printMessage();
    }

    private void runSetFoodRate(Matcher matcher) {
        int rate = Integer.parseInt(matcher.group("rate"));
        selectedBuildingController.setFoodRate(rate).printMessage();
    }

    private void runRepair() {
        gameMenu.printError(selectedBuildingController.repair().getMessage());
    }

    private void runDelete() throws IOException {
        gameMenu.printError(selectedBuildingController.deleteBuilding().getMessage());
    }

    private void runChangeProductionMode() {
        selectedBuildingController.changeProductionMode().printMessage();
    }

    private void runCreateUnit(Matcher matcher) {
        HashMap<String, String> inputs = SignupAndLoginUtils.getInputs(matcher, SelectedBuildingCommand.CREATE_UNIT.getRegex());
        selectedBuildingController.createUnit(null).printMessage();
    }

    private void runEntranceGate() {
        selectedBuildingController.changeGate().printMessage();
    }

    public void createSoldier(MouseEvent mouseEvent){
        new Sound(0).play();
        int ordinal = 0;
        Pattern pattern = Pattern.compile("\\d+");
        String name =  ((ImageView) mouseEvent.getPickResult().getIntersectedNode()).getImage().getUrl().toString();
        Matcher matcher = pattern.matcher(name);
        while (matcher.find()) {
            ordinal = Integer.parseInt(matcher.group());
        }
        MapAssetType mapAssetType = MapAssetType.values()[ordinal];
        SelectedBuildingMessage msg = selectedBuildingController.createUnit(mapAssetType);
    }

    public static void setSelectedBuildingController(SelectedBuildingController selectedBuildingController) {
        SelectedBuildingMenu.selectedBuildingController = selectedBuildingController;

    }

    public static GraphicGameMenu getGameMenu() {
        return gameMenu;
    }

    public static void setGameMenu(GraphicGameMenu gameMenu) {
        SelectedBuildingMenu.gameMenu = gameMenu;
    }

    public void repair(MouseEvent mouseEvent) {
        runRepair();
        checkHP();
    }

    public void info(MouseEvent mouseEvent) {
        gameMenu.printError(selectedBuildingController.showInfo());
    }

    public void delete(MouseEvent mouseEvent) throws IOException {
            runDelete();
            if (selectedBuildingController.deleteBuilding().equals(SelectedBuildingMessage.DELETED_BUILDING))
                mainPain.getChildren().clear();

    }

    private void checkHP() {
        if (selectedBuildingController.getBuilding().getHitPoint() == selectedBuildingController.getBuilding().getMaxHitPoint()
        && selectedBuildingController.getBuilding().getType() != MapAssetType.MERCENARY_POST
        && selectedBuildingController.getBuilding().getType() != MapAssetType.ENGINEER_GUILD
        && selectedBuildingController.getBuilding().getType() != MapAssetType.BARRACK)
            repairButton.setDisable(true);
    }

    private void setImage(){
        Image image = selectedBuildingController.getBuilding().getType().getImage();
        buildingImage.setImage(image);
    }
}

