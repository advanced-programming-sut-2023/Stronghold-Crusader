import controller.LoginController;
import model.Stronghold;

public class Main {
    public static void main(String[] args) {
        Stronghold.load();
        LoginController controller = new LoginController();
        controller.run();
    }
}
