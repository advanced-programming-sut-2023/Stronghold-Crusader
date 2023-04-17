package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileCommands {
    CHANGE_USERNAME("^\s*profile\s+change\s+-u\s+(?<newUsername>\\w+)\s*$");
    private String regex;
    ProfileCommands(String regex) {
        this.regex = regex;
    }

    public static ProfileCommands getCommand(String regex){
        for(ProfileCommands cmd : ProfileCommands.values()){
            if(regex.matches(cmd.regex)) return cmd;
        }
        return null;
    }

    public static Matcher getMatcher(String input, ProfileCommands cmd){
        Pattern pattern = Pattern.compile(cmd.regex);
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }
}
