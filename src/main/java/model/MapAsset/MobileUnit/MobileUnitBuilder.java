package model.MapAsset.MobileUnit;

import Settings.UnitConstants.MobileUnitConstants;
import model.Player;
import utils.Vector2D;

public class MobileUnitBuilder {

    public static MobileUnit creatingMobileUnit(Vector2D coordinate, Player owner, MobileUnitConstants mobileUnitType) {
        MobileUnit mobileObject = new MobileUnit(coordinate, owner, mobileUnitType.getType());
        setAttrs(mobileObject, mobileUnitType);
        return mobileObject;
    }


    private static void setAttrs(MobileUnit mobileObject, MobileUnitConstants mobileUnitType) {
        mobileObject.setDefenceMultiplier(mobileUnitType.getDefenseMultiplier());
        mobileObject.setEngineersCount(mobileUnitType.getEngineerCount());
        mobileObject.setMoveSpeed(mobileUnitType.getMoveSpeed());
        mobileObject.setMaxHitPoint(mobileUnitType.getMaxHitPoint());
    }


}
