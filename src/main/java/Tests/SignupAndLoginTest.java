package Tests;

import controller.SignupAndLoginController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.SignupAndLoginUtils;
import view.enums.commands.SignupAndLoginCommands;
import view.enums.messages.SignupAndLoginMessages;

import java.util.HashMap;
import java.util.regex.Matcher;

public class SignupAndLoginTest {
    @Test
    public void GetInputsTest() {
        Matcher matcher;
        HashMap<String, String> inputs = new HashMap<>();
        inputs.put("username", "username");
        inputs.put("password", "password");
        inputs.put("passwordConfirmation", null);
        inputs.put("slogan", "a slogan");
        inputs.put("email", "email");
        inputs.put("nickname", "nickname");
        //movement between inputs
        String command = "user create -u username -email email -p password -s \"a slogan\" -n nickname";
        matcher = SignupAndLoginCommands.getMatcher(command, SignupAndLoginCommands.CREATE_USER);
        HashMap<String, String> test =
                SignupAndLoginUtils.getInputs(matcher, SignupAndLoginCommands.CREATE_USER.getRegex());
        Assertions.assertEquals(inputs, test);

        command = "user create -p password   -s \"a slogan\" -n nickname -u username -email email ";
        matcher = SignupAndLoginCommands.getMatcher(command, SignupAndLoginCommands.CREATE_USER);
        test = SignupAndLoginUtils.getInputs(matcher, SignupAndLoginCommands.CREATE_USER.getRegex());
        Assertions.assertEquals(inputs, test);

        //get inputs in double quotation
        command = "user create -u \"kian username\"  -email \"I am an Email\" -p \"pass 123\" -s \"a slogan\" -n \"kian G\"";
        matcher = SignupAndLoginCommands.getMatcher(command, SignupAndLoginCommands.CREATE_USER);
        test = SignupAndLoginUtils.getInputs(matcher, SignupAndLoginCommands.CREATE_USER.getRegex());
        inputs.replace("username", "username", "kian username");
        inputs.replace("password", "password", "pass 123");
        inputs.replace("email", "email","I am an Email");
        inputs.replace("nickname", "nickname", "kian G");
        Assertions.assertEquals(inputs, test);
    }

    public void Test1() { //SignUp errors:
        HashMap<String, String> inputs = new HashMap<>();
        SignupAndLoginController controller = new SignupAndLoginController();
        inputs.put("username", "username");
        inputs.put("password", "password#P123");
        inputs.put("passwordConfirmation", "password#P123");
        inputs.put("slogan", "a slogan");
        inputs.put("email", "sth@sth.sth");
        inputs.put("nickname", "nickname");
        //Email format
        inputs.replace("email", "sth@sth.sth", "kian@gmail");
        SignupAndLoginMessages message = controller.signup(inputs);
        Assertions.assertEquals(message, SignupAndLoginMessages.INVALID_EMAIL_FORMAT);
        //Username format
        inputs.replace("username", "username", "kian@Gs4");
        message = controller.signup(inputs);
        Assertions.assertEquals(message, SignupAndLoginMessages.INVALID_USERNAME_FORMAT);
        //
    }
}

