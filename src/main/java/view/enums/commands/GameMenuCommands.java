package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    MODIFY_MAP("");
    private String regex;
    GameMenuCommands(String regex) {
        this.regex = regex;
    }

    public static GameMenuCommands getCommand(String input){
        for(GameMenuCommands cmd : GameMenuCommands.values()){
            if(input.matches(cmd.regex)) return cmd;
        }
        return null;
    }

    public static Matcher getMatcher(String input, GameMenuCommands cmd){
        Pattern pattern = Pattern.compile(cmd.regex);
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }
}
