import controller.UserControllers.LoginController;
import model.ConstantManager;
import model.Map.Map;
import model.Map.MapManager;
import model.Stronghold;
import utils.Vector2D;

public class Main {
    public static void main(String[] args) {
//        Map map = new Map(new Vector2D(100,100), "Desert");
//        map.setPlayerCount(3);
//        MapManager.save(map, "1001");

        Stronghold.load();
        ConstantManager.load();
        LoginController controller = new LoginController();
        controller.run();
    }
}
