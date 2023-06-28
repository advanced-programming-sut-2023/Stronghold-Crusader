package utils;

import view.enums.messages.UserMessage.SignupAndLoginMessage;

public enum FormatValidation {
    USERNAME("[\\w\\d\\_]+"),
    PASSWORD_LENGTH(".{1,5}"),
    PASSWORD_LETTERS_LOWERCASE("[^a-z]+"),
    PASSWORD_LETTERS_UPPERCASE("[^A-Z]+"),
    PASSWORD_LETTERS_NUMBER("[^\\d]+"),
    PASSWORD_LETTERS_SPECIAL_CHARACTER("[\\d\\w]+"),
    EMAIL("[\\w\\d\\.\\_]+\\@[\\w\\d\\_]+\\.[\\w\\d]+"),
    YES("Y|y.+"),
    NO("N|n.+"),
    BOOLEAN_ANSWER("t|T|f|F"),
    BUILDING_CATEGORY("defense_and_attack|production|training_and_employment|storage|normal|ox_tether");


    private final String regex;

    FormatValidation(String regex) {
        this.regex = regex;
    }

    public static boolean isFormatValid(String input, FormatValidation format) {
        return input.matches(format.regex);
    }
    public static SignupAndLoginMessage isPasswordValid(String input) {
        if (input.matches(PASSWORD_LENGTH.regex)) return SignupAndLoginMessage.PASSWORD_WEEK_LENGTH;
        if (input.matches(PASSWORD_LETTERS_LOWERCASE.regex)) return SignupAndLoginMessage.PASSWORD_WEEK_LOWERCASE_PROBLEM;
        if (input.matches(PASSWORD_LETTERS_UPPERCASE.regex)) return SignupAndLoginMessage.PASSWORD_WEEK_UPPERCASE_PROBLEM;
        if (input.matches(PASSWORD_LETTERS_NUMBER.regex)) return SignupAndLoginMessage.PASSWORD_WEEK_NUMBER_PROBLEM;
        if (input.matches(PASSWORD_LETTERS_SPECIAL_CHARACTER.regex)) return SignupAndLoginMessage.PASSWORD_WEEK_SPECIAL_CHARACTER_PROBLEM;
        return SignupAndLoginMessage.SUCCESS_PROCESS;
    }
}
