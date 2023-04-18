package view.enums.commands;

import com.sun.tools.javac.Main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands {
    PROFILE_MENU("\\s*profile\\s+menu\\s*");
    private String regex;
    MainMenuCommands(String regex) {
        this.regex = regex;
    }

    public static MainMenuCommands getCommand(String input){
        for(MainMenuCommands cmd : MainMenuCommands.values()){
            if(input.matches(cmd.regex)) return cmd;
        }
        return null;
    }

    public static Matcher getMatcher(String input, MainMenuCommands cmd){
        Pattern pattern = Pattern.compile(cmd.regex);
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }
}
