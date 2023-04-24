package model.MapAsset.MobileUnit;

import Settings.UnitConstants.MobileUnitConstants;
import model.Player;
import model.enums.MapAssetType;
import utils.Vector2D;

public class MobileUnitBuilder {

    private static MobileUnit creatingMobileUnit(Vector2D coordinate, Player owner, MapAssetType type, MobileUnitConstants mobileUnitType) {
        MobileUnit mobileObject = new MobileUnit(coordinate, owner, type);
        setAttrs(mobileObject, mobileUnitType);
        return mobileObject;
    }


    //TODO change name
    private static void setAttrs(MobileUnit mobileObject, MobileUnitConstants mobileUnitType) {
        mobileObject.setDefenceMultiplier(mobileUnitType.getDefenseMultiplier());
        mobileObject.setEngineersCount(mobileUnitType.getEngineerCount());
        mobileObject.setMoveSpeed(mobileUnitType.getMoveSpeed());
        mobileObject.setMaxHitPoint(mobileUnitType.getMaxHitPoint());
    }

    public static MobileUnit makeEngineer(Vector2D coordinate, Player owner) {
        return creatingMobileUnit(coordinate, owner, MapAssetType.ENGINEER, MobileUnitConstants.ENGINEER);
    }

    public static MobileUnit makeLadderMan(Vector2D coordinate, Player owner) {
        return creatingMobileUnit(coordinate, owner, MapAssetType.LADDER_MAN, MobileUnitConstants.LADDER_MAN);
    }

    public static MobileUnit makeSiegeTower(Vector2D coordinate, Player owner) {
        return creatingMobileUnit(coordinate, owner, MapAssetType.SIEGE_TOWER, MobileUnitConstants.SIEGE_TOWER);
    }

    public static MobileUnit makeMobileShield(Vector2D coordinate, Player owner) {
        return creatingMobileUnit(coordinate, owner, MapAssetType.MOBILE_SHIELD, MobileUnitConstants.MOBILE_SHIELD);
    }

    public static MobileUnit makeCow(Vector2D coordinate, Player owner) {
        return creatingMobileUnit(coordinate, owner, MapAssetType.COW, MobileUnitConstants.COW);
    }
}
