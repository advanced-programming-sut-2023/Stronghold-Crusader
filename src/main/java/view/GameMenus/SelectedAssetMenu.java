package view.GameMenus;

import controller.GameControllers.SelectedBuildingController;
import view.Menu;
import view.enums.commands.SelectedBuildingCommand;
import view.enums.messages.SelectedBuildingMessage;

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
            }
        }
    }

    private void runRepair(){
        System.out.println(selectedBuildingController.repair().getMessage());
    }
}

