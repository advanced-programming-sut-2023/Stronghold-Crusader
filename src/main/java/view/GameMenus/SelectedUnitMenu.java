package view.GameMenus;

import controller.GameControllers.SelectedUnitController;
import view.Menu;
import view.enums.commands.GameCommand.SelectedUnitCommand;
import view.enums.messages.GameMessage.SelectedUnitMessage;

import java.util.regex.Matcher;

public class SelectedUnitMenu {
    private final SelectedUnitController selectedUnitController;

    public SelectedUnitMenu(SelectedUnitController selectedUnitController) {
        this.selectedUnitController = selectedUnitController;
    }

    public void run() {
        String nextCommand;
        Matcher matcher;
        while (true) {
            nextCommand = Menu.getScanner().nextLine();
            SelectedUnitCommand typeOfCommand = SelectedUnitCommand.getCommand(nextCommand);
            if (typeOfCommand == null) {
                SelectedUnitMessage.INVALID_COMMAND.printMessage();
                continue;
            }
            matcher = SelectedUnitCommand.getMatcher(nextCommand, typeOfCommand);
            switch (typeOfCommand) {
                
            }
        }
    }
}
