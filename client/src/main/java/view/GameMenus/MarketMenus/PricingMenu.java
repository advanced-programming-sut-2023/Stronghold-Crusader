package view.GameMenus.MarketMenus;

import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.Game.Store.StoreMaterial;
import model.enums.AssetType.Material;

import java.io.IOException;
import java.util.ArrayList;

public class PricingMenu {
    public ScrollPane scrollPane;
    public HBox Hbox;
    public static MarketMenu mainMarketMenu;

    public void initialize(){
        ArrayList<StoreMaterial> materials = StoreMaterial.getMaterialList();
        Hbox = new HBox();
        for (StoreMaterial material : materials) {
            VBox vBox = new VBox();
            ImageView imageView = new ImageView(Material.getMaterial(material.getName().toLowerCase()).getImage());
            imageView.setFitWidth(60);
            imageView.setFitHeight(50);
            Text text = new Text(material.toString());
            text.setTextAlignment(TextAlignment.CENTER);
            vBox.getChildren().addAll(imageView, text);
            vBox.setPrefHeight(70);
            vBox.setPrefWidth(70);
            vBox.setAlignment(Pos.CENTER);
            Hbox.getChildren().add(vBox);
        }
        scrollPane.setContent(Hbox);
    }

    public void back() throws IOException {
        mainMarketMenu.getMain();
    }
}
