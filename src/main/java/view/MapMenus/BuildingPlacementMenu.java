package view.MapMenus;

import controller.MapControllers.BuildingPlacementController;
import view.Menu;
import view.enums.commands.MapCommand.BuildingPlacementCommand;
import view.enums.messages.MapMessage.BuildingPlacementMessage;
import view.enums.messages.MapMessage.MapMakerMessage;

import java.util.Scanner;
import java.util.regex.Matcher;

public class BuildingPlacementMenu {
    private Scanner scanner;
    private BuildingPlacementController controller;

    public BuildingPlacementMenu(BuildingPlacementController controller) {
        this.scanner = Menu.getScanner();
        this.controller = controller;
    }

    public String run() {
        String input;
        while (true) {
            input = scanner.nextLine();
            BuildingPlacementCommand cmd = BuildingPlacementCommand.getCommand(input);
            if (cmd == null) {
                MapMakerMessage.printMessage(MapMakerMessage.INVALID_COMMAND);
                continue;
            }
            Matcher matcher = BuildingPlacementCommand.getMatcher(input, cmd);
            switch (cmd) {
                case DROP_BUILDING:
                    runDropBuilding(matcher);
                    break;
                case CHOOSE_CATEGORY:
                    runChooseCategory(matcher);
                    break;
                case BACK:
                    BuildingPlacementMessage.printMessage(BuildingPlacementMessage.ENTER_MAIN);
                    return "back";
            }
        }
    }

    private void runDropBuilding(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String buildingTypeName = matcher.group("type");
        BuildingPlacementMessage.printMessage(controller.dropBuilding(buildingTypeName, x, y));
    }

    private void runChooseCategory(Matcher matcher) {
        String category = matcher.group("buildingCategory");
        BuildingPlacementMessage.printMessage(controller.setBuildingCategory(category));
    }
}
