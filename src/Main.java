import model.Stronghold;
import view.SignupAndLoginMenu;

public class Main {
    public static void main(String[] args) {
        Stronghold.load();
        SignupAndLoginMenu menu = new SignupAndLoginMenu();
        menu.run();
    }
}
