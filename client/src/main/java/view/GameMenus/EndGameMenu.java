package view.GameMenus;

import controller.GameControllers.EndGameController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EndGameMenu {
    private final EndGameController endGameController;

    public EndGameMenu(EndGameController endGameController) {
        this.endGameController = endGameController;
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
