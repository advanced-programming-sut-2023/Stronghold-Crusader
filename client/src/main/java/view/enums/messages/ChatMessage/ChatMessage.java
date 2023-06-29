package view.enums.messages.ChatMessage;

public enum ChatMessage {
    USER_NOT_FOUND("Username does not exist"),
    CHAT_PARTICIPANT_LIMIT("You can have more than two participants in a private chat"),
    SELECT_PARTICIPANT("Please select at least one other participant"),
    USER_ADDED_SUCCESSFULLY("User was added successfully"),
    SELECT_MODE("Please select chat type"),
    ENTER_CHAT_ID("Please enter chat id"),
    CHAT_CREATED_SUCCESSFULLY("Chat was created successfully");
    private final String message;

    ChatMessage(String message) {
        this.message = message;
    }

    public static void printMessage(ChatMessage msg) {
        System.out.println(msg.message);
    }

    public String getMessage() {
        return message;
    }
}
