import controller.SignupAndLoginController;
import model.Stronghold;

public class Main {
    public static void main(String[] args) {
        Stronghold.load();
        SignupAndLoginController menu = new SignupAndLoginController();
        menu.run();
    }
}
