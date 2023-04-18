package utils;

import model.Stronghold;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupAndLoginUtils {
    public static HashMap<String, String> getInputs(Matcher matcher, String regex) {
        Matcher listOfGroups = Pattern.compile("\\?\\<(?<groupName>[^\\>]+)\\>").matcher(regex);
        String groupName;
        HashMap<String, String> inputs = new HashMap<String, String>();
        while (listOfGroups.find()) {
            groupName = listOfGroups.group("groupName");
            inputs.put(groupName, matcher.group(groupName));
        }
        return inputs;
    }
    public static String generateRandomPassword() {
        String randomPass = "";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String upperAlphabet = lowerAlphabet.toUpperCase();
        String nonAlphanumeric = "@#$%^&****@#-_---";
        while (randomPass.length() < 6) {
            int character = (int)(Math.random()*26);
            randomPass += lowerAlphabet.substring(character, character+1);
             character = (int)(Math.random()*26);
            randomPass += upperAlphabet.substring(character, character+1);
            if (randomPass.length() == 6) {
                character %= 18;
                randomPass += nonAlphanumeric.substring(character, character+1);
                randomPass += Integer.valueOf(character).toString();
            }
        }
        return randomPass;
    }
    public static String generateRandomUsername(String username) {
        String newName = username;
        while (Stronghold.doesUserExist(newName)) {
            newName += Integer.valueOf((int)(Math.random() * 9 )).toString();
        }
        return newName;
    }
}
