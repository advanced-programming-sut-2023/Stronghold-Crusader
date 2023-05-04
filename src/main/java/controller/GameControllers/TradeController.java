package controller.GameControllers;

import model.Game.Game;
import model.Trade;
import model.User.Player;
import model.enums.AssetType.Material;
import utils.SignupAndLoginUtils;
import view.GameMenus.TradeMenu;
import view.enums.messages.TradeMenuMessage;

import java.util.ArrayList;
import java.util.HashMap;

public class TradeController {
    private final Game game;
    private static final ArrayList<Trade> trades = new ArrayList<>();

    public TradeController(Game game) {
        this.game = game;
    }

    public void run() {
        TradeMenu menu = new TradeMenu(this);
        while (true) {
            if (menu.run().equals("back")) return;
        }
    }

    public TradeMenuMessage request(HashMap<String, String> inputs) {
        if (hasEmptyFieldInRequest(inputs))
            return TradeMenuMessage.EMPTY_FIELD;
        if (!Material.isContains(inputs.get("resourceType").toUpperCase()))
            return TradeMenuMessage.INVALID_MATERIAL;
        if (!SignupAndLoginUtils.isNumberValid(inputs.get("resourceAmount")) ||
                !SignupAndLoginUtils.isNumberValid(inputs.get("price")))
            return TradeMenuMessage.INVALID_AMOUNT;

        int resourceAmount = Integer.parseInt(inputs.get("resourceAmount"));
        Material resourceType = Material.valueOf(inputs.get("resourceType").toUpperCase());

        if (game.getCurrentPlayer().getGovernance().getGold() < Integer.parseInt(inputs.get("price")))
            return TradeMenuMessage.GOLD_NEEDED;
        addTrade(resourceType, resourceAmount, Integer.parseInt(inputs.get("price")), inputs.get("message"));
        return TradeMenuMessage.REQUEST_SUCCESS;
    }

    public String showTrades() {
        StringBuilder result = new StringBuilder();
        if (trades.size() == 0) return "There are not trades!";
        for (Trade trade : trades) {
            result.append(trade.toString()).append("\n");
        }
        return result.toString();
    }

    private boolean hasEmptyFieldInRequest(HashMap<String, String> inputs) {
        return inputs.get("resourceType") == null || inputs.get("resourceAmount") == null
                || inputs.get("price") == null || inputs.get("message") == null;
    }

    private void addTrade(Material resourceType, int resourceAmount, int price, String message) {
        Trade newTrade = new Trade(game.getCurrentPlayer(), message, resourceType, resourceAmount, price);
        trades.add(newTrade);
        for (Player player : game.getPlayers()) {
            if (!player.equals(game.getCurrentPlayer())) player.getNewTrades().add(newTrade);
        }
    }
}
