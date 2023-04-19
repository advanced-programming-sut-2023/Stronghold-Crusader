package view.enums.messages;

import view.MainMenu;
import view.enums.commands.MainMenuCommands;

public enum MainMenuMessages {
    INVALID_COMMAND("invalid command");
    private String message;
    private MainMenuMessages(String message){
        this.message = message;
    }

    public void printMessage(MainMenuMessages msg){
        System.out.println(msg.message);
    }
}
