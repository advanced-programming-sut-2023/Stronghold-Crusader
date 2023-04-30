package view.MapMenus;

import controller.MapControllers.ShowMapController;
import view.Menu;
import view.enums.commands.ProfileCommand;
import view.enums.messages.ProfileMessage;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ShowMapMenu {

    private final ShowMapController showMapController;
    private final Scanner scanner;

    public ShowMapMenu(ShowMapController showMapController) {
        this.showMapController = showMapController;
        this.scanner = Menu.getScanner();
    }

    public String run() {
        String input;
        while (true) {
            input = scanner.nextLine();
            ProfileCommand cmd = ProfileCommand.getCommand(input);
            if (cmd == null) {
                ProfileMessage.printMessage(ProfileMessage.INVALID_COMMAND);
                continue;
            }
            Matcher matcher = ProfileCommand.getMatcher(input, cmd);
            switch (cmd) {
                case CHANGE_USERNAME:
                    runChangeUsername(matcher);
                    break;
                case BACK:
                    return "gameMenu";
                default:
                    ProfileMessage.printMessage(ProfileMessage.INVALID_COMMAND);
                    break;
            }
        }
    }
}
