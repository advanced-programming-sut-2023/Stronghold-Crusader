package utils;

import model.Stronghold;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupAndLoginUtils {
    public static HashMap<String, String> getInputs(Matcher matcher, String regex) {
        Matcher listOfGroups = Pattern.compile("\\?<(?<groupName>[^>]+)>").matcher(regex);
        String groupName;
        HashMap<String, String> inputs = new HashMap<>();
        while (listOfGroups.find()) {
            groupName = listOfGroups.group("groupName");
            String value = matcher.group(groupName);
            value = removeDoubleQuotation(value);
            inputs.put(groupName, value);
        }
        return inputs;
    }

    public static String generateRandomPassword() {
        String randomPass = "";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String upperAlphabet = lowerAlphabet.toUpperCase();
        String nonAlphanumeric = "@#$%^&****@#-_---";
        while (randomPass.length() < 6) {
            int character = (int) (Math.random() * 26);
            randomPass += lowerAlphabet.substring(character, character + 1);
            character = (int) (Math.random() * 26);
            randomPass += upperAlphabet.substring(character, character + 1);
            if (randomPass.length() == 6) {
                character %= 18;
                randomPass += nonAlphanumeric.substring(character, character + 1);
                randomPass += Integer.valueOf(character).toString();
            }
        }
        return randomPass;
    }

    public static String generateRandomUsername(String username) {
        String newName = username;
        while (Stronghold.getInstance().userExists(newName)) {
            newName += Integer.valueOf((int) (Math.random() * 9)).toString();
        }
        return newName;
    }

    public static String removeDoubleQuotation(String word) {
        if (word == null) return null;
        if (word.matches("(?=.*[\\s]).+")) {
            return word.substring(1, word.length() - 1);
        }
        return word;
    }

}
