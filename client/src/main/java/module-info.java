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
    opens model.Television to javafx.fxml;
    opens network to com.google.gson;
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
    opens model.chatRoom to com.google.gson;

    opens view.UserMenus to javafx.fxml;
    opens view.GameMenus to javafx.fxml;
    opens view.MapMenus to javafx.fxml;
    opens view.MapMenus.dropBuildingMenu to javafx.fxml;

    exports view.UserMenus;
    exports view.GameMenus;
    exports view.MapMenus;
    exports view.ChatMenus;
    exports view;
    exports model.User;
    exports model.Game.Store;
    exports model.MapAsset.MobileUnit;
    exports model.chatRoom;
    exports utils;
    exports view.MapMenus.dropBuildingMenu;
    exports view.GameMenus.MarketMenus;
    exports controller.GameControllers;
    exports controller.UserControllers;
    exports controller.ChatControllers;
    opens view.GameMenus.MarketMenus to javafx.fxml;
    exports view.GameMenus.TradeMenus;
    opens view.GameMenus.TradeMenus to javafx.fxml;
    exports view.GameMenus.SelectedUnitMenus;
    opens view.GameMenus.SelectedUnitMenus to javafx.fxml;
}