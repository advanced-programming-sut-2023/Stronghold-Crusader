package view.enums.commands.UserCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommand {
    PROFILE_MENU("\\s*profile\\s+menu\\s*"),
    GAME_MAKER_MENU("\\s*new\\s+game\\s*"),
    LOG_OUT("\\s*log\\s*out\\s*");
    private String regex;

    MainMenuCommand(String regex) {
        this.regex = regex;
    }

    public static MainMenuCommand getCommand(String input) {
        for (MainMenuCommand cmd : MainMenuCommand.values()) {
            if (input.matches(cmd.regex)) return cmd;
        }
        return null;
    }

    public static Matcher getMatcher(String input, MainMenuCommand cmd) {
        Pattern pattern = Pattern.compile(cmd.regex);
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }
}
