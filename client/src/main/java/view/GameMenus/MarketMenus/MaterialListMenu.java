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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaterialListMenu {
    public ScrollPane scrollPane;
    public HBox Hbox;
    public static MarketMenu mainMarketMenu;
    private static ArrayList<StoreMaterial> materials;

    public static void setMaterials(ArrayList<StoreMaterial> materiallist) {
        materials = materiallist;
    }

    public void initialize(){
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
            setOnClick(vBox);
        }
        scrollPane.setContent(Hbox);
    }

    public void back() throws IOException {
        mainMarketMenu.getMain();
    }

    public void setOnClick(VBox vBox){
        vBox.setOnMouseClicked(e -> {
            String text = ((Text) vBox.getChildren().get(1)).getText();
            Pattern pattern = Pattern.compile("(?<name>\\S+)\n\\S+");
            Matcher matcher = pattern.matcher(text);
            matcher.find();
            try {
                mainMarketMenu.getPurchaseMenu(matcher.group("name"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
