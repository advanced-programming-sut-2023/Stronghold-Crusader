package view.GameMenus;

import controller.GameControllers.SelectedBuildingController;
import utils.SignupAndLoginUtils;
import view.Menu;
import view.enums.commands.SelectedBuildingCommand;
import view.enums.messages.SelectedBuildingMessage;

import java.util.HashMap;
import java.util.regex.Matcher;

public class SelectedAssetMenu {
    private final SelectedBuildingController selectedBuildingController;

    public SelectedAssetMenu(SelectedBuildingController selectedBuildingController){
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
                    return;
            }
        }
    }

    private void runRepair(){System.out.println(selectedBuildingController.repair().getMessage());}
    private void runDelete(){System.out.println(selectedBuildingController.deleteBuilding().getMessage());}
    private void runChangeProductionMode(){System.out.println(selectedBuildingController.changeProductionMode().getMessage());}
    private  void runCreateUnit(Matcher matcher){
        HashMap<String,String> inputs =SignupAndLoginUtils.getInputs(matcher,SelectedBuildingCommand.CREATE_UNIT.getRegex());
        SelectedBuildingMessage selectedBuildingMessage = selectedBuildingController.createUnit(inputs);
    }
}

