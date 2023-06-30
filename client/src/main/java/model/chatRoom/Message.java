package model.chatRoom;

import java.util.ArrayList;
import java.util.HashMap;

public class Message {
    public enum Reaction {
        LIKE, DISLIKE, HEART, FIRE;
    }

    private final String senderUsername;
    private String text;
    private final int hour;
    private final int minute;
    private boolean seenStatus = false;
    private final ArrayList<String> invisibleFor;
    private HashMap<String, Reaction> reactions;

    public Message(String senderUsername, String text, int hour, int minute) {
        this.senderUsername = senderUsername;
        this.text = text;
        this.hour = hour;
        this.minute = minute;
        reactions = new HashMap<>();
        invisibleFor = new ArrayList<>();
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public String getText() {
        return text;
    }

    public void setSeenStatus(boolean seenStatus) {
        this.seenStatus = seenStatus;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void addReaction(String username, Reaction reaction) {
        reactions.put(username, reaction);
    }

    public HashMap<String, Reaction> getReactions() {
        return reactions;
    }

    public void replaceReaction(String username, Reaction old, Reaction newReaction) {
        reactions.replace(username, old, newReaction);
    }

    public boolean isVisibleForUser(String name) {
        return invisibleFor.contains(name);
    }

    public void setInvisibleFor(String name){
        invisibleFor.add(name);
    }

    public ArrayList<String> getInvisibleFor() {
        return invisibleFor;
    }

    public boolean isSeenStatus() {
        return seenStatus;
    }
}
