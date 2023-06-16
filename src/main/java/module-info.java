module project.group {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.swing;
    requires com.google.gson;
    requires java.desktop;

    exports model.enums.AssetType to com.google.gson;
    exports model.enums to com.google.gson;
    exports  model.Map to com.google.gson;
    opens model to com.google.gson;
    opens model.User to com.google.gson, javafx.fxml;
    opens utils to com.google.gson;
    opens model.MapAsset.Building to com.google.gson;
    opens model.MapAsset.MobileUnit to com.google.gson;
    opens controller.GameControllers to com.google.gson;
    opens model.MapAsset to com.google.gson;
    opens model.enums to com.google.gson;
    opens model.enums.AssetType to com.google.gson;
    opens assets.graphic.tiles to com.google.gson;
    opens model.Game to com.google.gson;
    opens model.Game.Store to com.google.gson;
    opens model.Map to com.google.gson;

    opens view.UserMenus to javafx.fxml;
    opens view.GameMenus to javafx.fxml;
    opens view.MapMenus to javafx.fxml;
    opens view.MapMenus.dropBuildingMenu to javafx.fxml;

    exports view.UserMenus;
    exports view.GameMenus;
    exports view.MapMenus;
    exports view;
    exports model.User;
    exports view.MapMenus.dropBuildingMenu;
}