import model.Stronghold;
import controller.SignupAndLoginController;

public class Main {
    public static void main(String[] args) {
        Stronghold.load();
        SignupAndLoginController menu = new SignupAndLoginController();
        menu.run();
    }
}
