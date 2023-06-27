package view.GameMenus.TradeMenus;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.Game.Trade;
import view.enums.messages.TradeMenuMessage;


public class TableItem {
    private final ImageView state = new ImageView();
    private final int ID ;
    private final String to;
    private final String owner;
    private final Circle good;
    private final String type;
    private final int amount;
    private final String message;
    private final Button denyButton = new Button("deny");
    private final Button acceptButton = new Button("accept");

    public TableItem(Trade trade) {
        setState(trade);
        state.setFitHeight(35);
        state.setFitWidth(35);
        this.ID = trade.getID();
        to = trade.getAcceptor().getNickname();
        owner = trade.getOwner().getNickname();
        good = new Circle(17);
        type = trade.isRequest() ? "request" : "donate";
        good.setFill(new ImagePattern(trade.getMaterial().getImage()));
        amount = trade.getAmount();
        message = (TradeMenu.getTradeController().getGame().getCurrentPlayer().equals(trade.getOwner()))
                ? trade.getAcceptorMessage() : trade.getMessage();
        setClickedButtonOption(trade);
    }

    private void setClickedButtonOption(Trade trade) {
        acceptButton.setOnMouseClicked(e -> {
           String message =  TradeMenu.getTradeController().accept_trade(trade).getMessage();
           if (message.equals(TradeMenuMessage.ACCEPTED.getMessage())){
               setState(trade);
               acceptButton.setVisible(false);
               denyButton.setVisible(false);
           }

        });
        denyButton.setOnMouseClicked(e ->{
            trade.deny();
            acceptButton.setVisible(false);
            denyButton.setVisible(false);
            setState(trade);
        });
    }

    private void setState(Trade trade) {
        if (!trade.isFinished()) {
            this.state.setImage(new Image(TableItem.class.getResource("/assets/icons/undefine.png").toString()));
            return;
        }
        if(trade.isAccepted())
            this.state.setImage(new Image(TableItem.class.getResource("/assets/icons/accepted.png").toString()));
        else this.state.setImage(new Image(TableItem.class.getResource("/assets/icons/deny.png").toString()));
    }

    public ImageView getState() {
        return state;
    }

    public int getID() {
        return ID;
    }

    public String getTo() {
        return to;
    }

    public Circle getGood() {
        return good;
    }

    public int getAmount() {
        return amount;
    }

    public String getMessage() {
        return message;
    }

    public String getOwner() {
        return owner;
    }

    public String getType() {
        return type;
    }

    public Button getDenyButton() {
        return denyButton;
    }

    public Button getAcceptButton() {
        return acceptButton;
    }
}
