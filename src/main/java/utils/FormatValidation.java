package utils;

public enum FormatValidation {
    USERNAME("[\\w\\d\\_]+"),
    PASSWORD_LENGTH(".{6,}"),
    PASSWORD_LETTERS("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^\\w\\d]).+$"),
    EMAIL("[\\w\\d\\.\\_]+\\@[\\w\\d\\_]+\\.[\\w\\d]+"),
    YES("Y|y.+"),
    NO("N|n.+"),
    BOOLEAN_ANSWER("t|T|f|F"),
    BUILDING_CATEGORY("DandA|Production|TAndA|Symbolic|Store|OxTether");


    private final String regex;
    FormatValidation(String regex) {
        this.regex = regex;
    }
    public static boolean isFormatValid(String input, FormatValidation format) {
        return input.matches(format.regex);
    }
}
