import controller.UserControllers.LoginController;
import model.ConstantManager;
import model.Stronghold;

public class Main {
    public static void main(String[] args) {
        Stronghold.load();
        ConstantManager.load();
        LoginController controller = new LoginController();
        controller.run();
    }
}
