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
                case MOVE_UNIT:
                    runMoveUnit(matcher);
                    break;
                case PATROL_UNIT:
                    runPatrolUnit(matcher);
                    break;
                case SET_STATE:
                    selectedUnitController.setState(matcher.group("state")).printMessage();
                    break;
                case ATTACK:
                    runAttack(matcher);
                    break;
                case SHOW_SELECTED_UNITS:
                    System.out.print(selectedUnitController.selectedUnitInfo());
                    break;
                case BACK:
                    return;
            }
        }
    }

    private void runAttack(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        selectedUnitController.setAttackTarget(x, y).printMessage();
    }

    private void runMoveUnit(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        selectedUnitController.moveUnit(x, y).printMessage();
    }

    private void runPatrolUnit(Matcher matcher) {
        int x1 = Integer.parseInt(matcher.group("x1"));
        int y1 = Integer.parseInt(matcher.group("y1"));
        int x2 = Integer.parseInt(matcher.group("x2"));
        int y2 = Integer.parseInt(matcher.group("y2"));
        selectedUnitController.petrolUnit(x1, y1, x2, y2).printMessage();
    }
}
