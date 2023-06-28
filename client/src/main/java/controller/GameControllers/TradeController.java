package controller.GameControllers;

import model.Game.Game;
import model.Game.Trade;
import model.User.Player;
import model.enums.AssetType.Material;
import utils.SignupAndLoginUtils;
import view.GameMenus.TradeMenus.TradeMenu;
import view.enums.messages.TradeMenuMessage;

import java.util.ArrayList;
import java.util.HashMap;

public class TradeController {
    public static final ArrayList<Trade> trades = new ArrayList<>();
    private final Game game;

    public TradeController(Game game) {
        this.game = game;
    }

    public void run() {
        TradeMenu menu = new TradeMenu();
        while (true) {
            if (menu.run().equals("back")) return;
        }
    }

    public ArrayList<Trade> getMyTrades(){
        ArrayList<Trade> donates = new ArrayList<>();
        for (Trade trade: trades) {
            if (trade.getOwner().equals(game.getCurrentPlayer()))
                donates.add(trade);
        }
            return donates;
    }

    public ArrayList<Trade> getTradesToMe(){
        ArrayList<Trade> donates = new ArrayList<>();
        for (Trade trade: trades) {
            if (trade.getAcceptor().equals(game.getCurrentPlayer()))
                donates.add(trade);
        }
        return donates;
    }



    public TradeMenuMessage request(HashMap<String, String> inputs) {
        if (hasEmptyFieldInRequest(inputs))
            return TradeMenuMessage.EMPTY_FIELD;
        if (!Material.contains(inputs.get("resourceType").toUpperCase()))
            return TradeMenuMessage.INVALID_MATERIAL;
        if (!SignupAndLoginUtils.isNumberValid(inputs.get("resourceAmount")) ||
                !SignupAndLoginUtils.isNumberValid(inputs.get("price")))
            return TradeMenuMessage.INVALID_AMOUNT;

        int resourceAmount = Integer.parseInt(inputs.get("resourceAmount"));
        Material resourceType = Material.valueOf(inputs.get("resourceType").toUpperCase());

        if (game.getCurrentPlayer().getGovernance().getGold().get() < Integer.parseInt(inputs.get("price")))
            return TradeMenuMessage.GOLD_NEEDED;
        Trade trade = new Trade(game.getCurrentPlayer(), null, resourceType, resourceAmount, true );
        addTrade(trade);
        return TradeMenuMessage.REQUEST_SUCCESS;
    }



    public TradeMenuMessage accept_trade(Trade trade) {

        int amount = trade.getAmount();
        Material material = trade.getMaterial();
        if (game.getCurrentPlayer().getGovernance().getStorageCapacity(material)
                < amount)
            return TradeMenuMessage.MATERIAL_NEEDED;
        if (trade.isAccepted())
            return TradeMenuMessage.ALREADY_ACCEPTED;
        game.getCurrentPlayer().getGovernance().changeStorageStock(material, -1 * amount);
        //  trade.getOwner().getGovernance().changeGold(trade.isRequest());
        trade.accept();
        return TradeMenuMessage.ACCEPTED;
    }

    private boolean hasEmptyFieldInRequest(HashMap<String, String> inputs) {
        return inputs.get("resourceType") == null || inputs.get("resourceAmount") == null
                || inputs.get("price") == null || inputs.get("message") == null;
    }

    public void addTrade(Trade newTrade) {
        trades.add(newTrade);
        for (Player player : game.getPlayers()) {
            if (player.equals(newTrade.getAcceptor())) player.getNewTrades().add(newTrade);
        }
    }

    private Trade getTradeById(int id) {
        for (Trade trade : trades) {
            if (id == trade.getID())
                return trade;
        }
        return null;
    }
    public boolean isMaterialEnough(Material material, int amount) {
       return game.getCurrentPlayer().getGovernance().getStorageCapacity(material) >=  amount;
    }

    public Game getGame() {
        return game;
    }
}
