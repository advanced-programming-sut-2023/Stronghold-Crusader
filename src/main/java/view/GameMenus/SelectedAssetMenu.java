package view.GameMenus;

import controller.GameControllers.SelectedBuildingController;
import utils.SignupAndLoginUtils;
import view.Menu;
import view.enums.commands.GameCommand.SelectedBuildingCommand;
import view.enums.messages.GameMessage.SelectedBuildingMessage;

import java.util.HashMap;
import java.util.regex.Matcher;

public class SelectedAssetMenu {
    private final SelectedBuildingController selectedBuildingController;

    public SelectedAssetMenu(SelectedBuildingController selectedBuildingController) {
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
                case STOP_PRODUCTION:
                    runChangeProductionMode();
                case RESUME_PRODUCTION:
                    runChangeProductionMode();
                case CREATE_UNIT:
                    runCreateUnit(matcher);
                case DELETE:
                    runDelete();
                case CHANGE_ENTRANCE:
                    runEntranceGate();
                    return;
            }
        }
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
        selectedBuildingController.createUnit(inputs).printMessage();
    }
    private void runEntranceGate(){
        selectedBuildingController.changeGate().printMessage();
    }
}

