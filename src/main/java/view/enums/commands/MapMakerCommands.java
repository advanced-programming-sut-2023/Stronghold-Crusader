package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MapMakerCommands {
    // TODO : add commands
    CHANGE_ENVIRONMENT(""),
    PLACE_ASSETS(""),
    SET_TEXTURE(""),
    CLEAR_CELL(""),
    DROP_ROCK(""),
    DROP_TREE(""),
    DROP_HEADQUARTERS(""),
    DROP_BUILDING(""),
    DROP_UNIT(""),
    BACK("");
    private String regex;
    MapMakerCommands(String regex){
        this.regex = regex;
    }
    public static MapMakerCommands getCommand(String input){
        for(MapMakerCommands cmd : MapMakerCommands.values()){
            if(input.matches(cmd.regex)) return cmd;
        }
        return null;
    }

    public static Matcher getMatcher(String input, MapMakerCommands cmd){
        Pattern pattern = Pattern.compile(cmd.regex);
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }
}
