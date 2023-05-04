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
        if (!Material.Contains(inputs.get("resourceType").toUpperCase()))
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

    public String showAllTrades() {
        if (trades.size() == 0) return "There are not trades!";
        StringBuilder result = new StringBuilder();
        result.append("all trades:").append("\n");
        for (Trade trade : trades) {
            result.append(trade.toString()).append("\n");
        }
        return result.toString();
    }

    public String tradeHistory() {
        StringBuilder result = new StringBuilder();
        for (Trade trade : trades) {
            if (trade.getOwner() == game.getCurrentPlayer() || trade.getAcceptor() == game.getCurrentPlayer())
                result.append(trade).append("\n");
        }
        if (result.length() == 0) return "you don't have any trades yet!";
        return result.toString();
    }

    public String showNewTradesForPlayer() {
        if (game.getCurrentPlayer().getNewTrades().size() == 0) return "You don't have any new trades";
        StringBuilder result = new StringBuilder();
        for (Trade trade : game.getCurrentPlayer().getNewTrades()) {
            if (trade.getOwner().equals(game.getCurrentPlayer()))
                result.append(trade.showAcceptedTrade()).append("\n");
        }
        result.append("new Trades :").append("\n");
        for (Trade trade : game.getCurrentPlayer().getNewTrades()) {
            result.append(trade.toString()).append("\n");
        }
        game.getCurrentPlayer().getNewTrades().clear();
        return result.toString();
    }

    public TradeMenuMessage accept_trade(HashMap<String, String> inputs) {
        Trade trade = getTradeById(Integer.parseInt(inputs.get("id")));
        if (trade == null)
            return TradeMenuMessage.INVALID_ID;

        int amount = Integer.parseInt(trade.getInfo().y);
        Material material = Material.valueOf(trade.getInfo().x.toUpperCase());
        if (game.getCurrentPlayer().getGovernance().getStorageCapacity(material)
                < amount)
            return TradeMenuMessage.MATERIAL_NEEDED;
        if (trade.isAccepted())
            return TradeMenuMessage.ALREADY_ACCEPTED;
        game.getCurrentPlayer().getGovernance().changeStorageStock(material, -1 * amount);
        game.getCurrentPlayer().getGovernance().changeGold(trade.getPrice());
        trade.setAcceptorMessage(inputs.get("message"));
        trade.accept();
        return TradeMenuMessage.ACCEPTED;
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

    private Trade getTradeById(int id) {
        for (Trade trade : trades) {
            if (id == trade.getId())
                return trade;
        }
        return null;
    }
}
