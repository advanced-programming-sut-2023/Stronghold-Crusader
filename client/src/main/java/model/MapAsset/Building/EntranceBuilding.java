package model.MapAsset.Building;

import model.User.Player;
import utils.Vector2D;

public class EntranceBuilding extends Building {
    private boolean isOpen;
    private boolean hasFlag;

    public EntranceBuilding(EntranceBuilding reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
        isOpen = true;
        hasFlag = false;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public boolean hasFlag() {
        return hasFlag;
    }

    public void open() {
        isOpen = true;
    }

    public void close() {
        isOpen = false;
    }

    public void setFlag(boolean flag) {
        hasFlag = flag;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", isOpen=" + isOpen +
                ", hasFlag=" + hasFlag;
    }
}
