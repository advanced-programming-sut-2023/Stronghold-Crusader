package utils;

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

}
