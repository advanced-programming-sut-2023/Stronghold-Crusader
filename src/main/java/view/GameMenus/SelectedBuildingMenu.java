package view.GameMenus;

import controller.GameControllers.SelectedBuildingController;
import javafx.scene.input.MouseEvent;
import model.enums.AssetType.MapAssetType;
import utils.SignupAndLoginUtils;
import view.Menu;
import view.enums.commands.GameCommand.SelectedBuildingCommand;
import view.enums.messages.GameMessage.SelectedBuildingMessage;

import java.util.HashMap;
import java.util.regex.Matcher;

public class SelectedBuildingMenu {
    private final SelectedBuildingController selectedBuildingController;

    public SelectedBuildingMenu(SelectedBuildingController selectedBuildingController) {
        this.selectedBuildingController = selectedBuildingController;
    }

    public void run() {
        String nextCommand;
        Matcher matcher;
        while (true) {
            nextCommand = Menu.getScanner().nextLine();
            SelectedBuildingCommand typeOfCommand = SelectedBuildingCommand.getCommand(nextCommand);
            if (typeOfCommand == null) {
                SelectedBuildingMessage.INVALID_COMMAND.printMessage();
                continue;
            }
            matcher = SelectedBuildingCommand.getMatcher(nextCommand, typeOfCommand);
            switch (typeOfCommand) {
                case REPAIR:
                    runRepair();
                    break;
                case INFO:
                    System.out.println(selectedBuildingController.showInfo());
                    break;
                case Change_PRODUCTION:
                    runChangeProductionMode();
                    break;
                case SET_FOOD_RATE:
                    runSetFoodRate(matcher);
                    break;
                case SET_TAX_RATE:
                    runSetTaxRate(matcher);
                    break;
                case CREATE_UNIT:
                    runCreateUnit(matcher);
                    break;
                case DELETE:
                    runDelete();
                    break;
                case CHANGE_ENTRANCE:
                    runEntranceGate();
                    break;
                case BACK:
                    return;
            }
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
        selectedBuildingController.repair().printMessage();
    }

    private void runDelete() {
        selectedBuildingController.deleteBuilding().printMessage();
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
        int ordinal = 50;
        MapAssetType mapAssetType = MapAssetType.values()[ordinal];
        selectedBuildingController.createUnit(mapAssetType);
    }
}

