package view.enums.commands.GameCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MarketCommand {
    PRICE_LIST("^\\s*show\\s+price\\s+list\\s*$"),
    BUY("^\\s*buy\\s+-i\\s*(?<item>\\S+)\\s+-a\\s+(?<amount>\\S+)\\s*$"),
    SELL("^\\s*sell\\s+-i\\s*(?<item>\\S+)\\s+-a\\s+(?<amount>\\S+)\\s*$"),
    BACK("\\s*back\\s*");
    private final String regex;

    MarketCommand(String regex) {
        this.regex = regex;
    }

    public static MarketCommand getCommand(String input) {
        for (MarketCommand cmd : MarketCommand.values()) {
            if (input.matches(cmd.regex)) return cmd;
        }
        return null;
    }

    public static Matcher getMatcher(String input, MarketCommand cmd) {
        Pattern pattern = Pattern.compile(cmd.regex);
        Matcher matcher = pattern.matcher(input);
        matcher.find();
        return matcher;
    }
}
