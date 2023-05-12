package model.enums.AssetType;

public enum UnitState {
    DEFENSIVE(3),
    STANDING(0),
    OFFENSIVE(10);
    private final int triggerRange;

    UnitState(int triggerRange) {
        this.triggerRange = triggerRange;
    }

    public int getTriggerRange() {
        return triggerRange;
    }
}
