package view.GameMenus;

import controller.GameControllers.EndGameController;
import view.Menu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EndGameMenu {
    private final EndGameController endGameController;

    public EndGameMenu(EndGameController endGameController) {
        this.endGameController = endGameController;
    }

    public void run() {
        String selectedUser = "\\s*select\\s+-u\\s+(?<username>\\S+)\\s*";
        String exit = "\\s*exit\\s*";
        System.out.println(endGameController.showPlayers());
        while (true) {
            String command = Menu.getScanner().nextLine();
            if (command.matches(selectedUser)) {
                selectUserRun(getMatcher(command, selectedUser));
            } else if (command.matches(exit)) break;
            else System.out.println("invalid command!");
        }
    }

    private Matcher getMatcher(String input, String cmd) {
        Pattern pattern = Pattern.compile(cmd);
        Matcher matcher = pattern.matcher(input);
        matcher.find();
        return matcher;
    }

    private void selectUserRun(Matcher matcher) {
        System.out.println(endGameController.showInfo(matcher.group("username")));
    }
}
