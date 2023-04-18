package utils;

public enum FormatValidation {
    USERNAME("[\\w\\d\\_]+"),
    PASSWORDLENGTH(".{6,}"),
    PASSWORDLETTERS("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^\\w\\d])$"),
    EMAIL("[\\w\\d\\.\\_]+\\@[\\w\\d\\_]+\\.[\\w\\d]");


    private String regex;
    FormatValidation(String regex) {
        this.regex = regex;
    }
    public static boolean isFormatValid(String input, FormatValidation format) {
        return input.matches(format.regex);
    }
}
