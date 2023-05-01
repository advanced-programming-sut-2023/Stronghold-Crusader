package view.MapMenus.BuildingPlacementMenu;

import controller.MapControllers.BuildingPlacementController;
import view.Menu;
import view.enums.commands.MapCommand.BuildingPlacementCommand;
import view.enums.messages.MapMessage.MapMakerMessage;

import java.util.Scanner;

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
            if (input == null) {
                MapMakerMessage.printMessage(MapMakerMessage.INVALID_COMMAND);
                continue;
            }
            switch (cmd) {
                case DANDA:
                    DAndAPlacementMenu DandAMenu = new DAndAPlacementMenu();
                    break;
                case TANDA:
                    TAndEPlacementMenu TandEmenu = new TAndEPlacementMenu();
                    break;
                case SYMBOLIC:
                    SymbolicPlacementMenu symbolicPlacementMenu = new SymbolicPlacementMenu();
                    break;
                case PRODUCTION:
                    ProductionPlacementMenu productionPlacementMenu = new ProductionPlacementMenu();
                    break;
                case BACK:
                    return "back";
            }
        }
    }
}
