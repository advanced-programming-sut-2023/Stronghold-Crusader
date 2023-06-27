package view.GameMenus.TradeMenus;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.Game.Trade;

public class TableItem {
    private final ImageView state = new ImageView();
    private final int ID ;
    private final String to;
    private final Circle good;
    private final int amount;
    private final String message;

    public TableItem(Trade trade) {
        setState(trade.isAccepted());
        state.setFitHeight(35);
        state.setFitWidth(35);
        this.ID = trade.getID();
        to = trade.getAcceptor().getNickname();
        good = new Circle(17);
        good.setFill(new ImagePattern(trade.getMaterial().getImage()));
        amount = trade.getAmount();
        message = trade.getMessage();
    }

    private void setState(boolean accepted) {

        if(accepted)
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
}
