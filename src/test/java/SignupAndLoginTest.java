import controller.UserControllers.SignupController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.FormatValidation;
import utils.SignupAndLoginUtils;
import view.enums.commands.SignupAndLoginCommand;
import view.enums.messages.SignupAndLoginMessage;

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
        matcher = SignupAndLoginCommand.getMatcher(command, SignupAndLoginCommand.CREATE_USER);
        HashMap<String, String> test =
                SignupAndLoginUtils.getInputs(matcher, SignupAndLoginCommand.CREATE_USER.getRegex());
        test.remove("sloganTest");
        Assertions.assertEquals(inputs, test);

        command = "user create -p password   -s \"a slogan\" -n nickname -u username -email email ";
        matcher = SignupAndLoginCommand.getMatcher(command, SignupAndLoginCommand.CREATE_USER);
        test = SignupAndLoginUtils.getInputs(matcher, SignupAndLoginCommand.CREATE_USER.getRegex());
        test.remove("sloganTest");
        Assertions.assertEquals(inputs, test);

        //get inputs in double quotation
        command = "user create -u \"kian username\"  -email \"I am an Email\" -p \"pass 123\" -s \"a slogan\" -n \"kian G\"";
        matcher = SignupAndLoginCommand.getMatcher(command, SignupAndLoginCommand.CREATE_USER);
        test = SignupAndLoginUtils.getInputs(matcher, SignupAndLoginCommand.CREATE_USER.getRegex());
        inputs.replace("username", "username", "kian username");
        inputs.replace("password", "password", "pass 123");
        inputs.replace("email", "email", "I am an Email");
        inputs.replace("nickname", "nickname", "kian G");
        test.remove("sloganTest");
        Assertions.assertEquals(inputs, test);
    }

    @Test
    public void FormatValidations() { //Format Validation errors:
        boolean checkEmail = FormatValidation.isFormatValid("sth@sth..com", FormatValidation.EMAIL);
        Assertions.assertFalse(checkEmail);
        checkEmail = FormatValidation.isFormatValid("sth@com", FormatValidation.EMAIL);
        Assertions.assertFalse(checkEmail);
        checkEmail = FormatValidation.isFormatValid("s.th_1@sth__.END", FormatValidation.EMAIL);
        Assertions.assertTrue(checkEmail);
        boolean checkUsername = FormatValidation.isFormatValid("kianK_123456789", FormatValidation.USERNAME);
        Assertions.assertTrue(checkUsername);
        checkUsername = FormatValidation.isFormatValid("kianK.123456789", FormatValidation.USERNAME);
        Assertions.assertFalse(checkUsername);
        checkUsername = FormatValidation.isFormatValid("kian 123", FormatValidation.USERNAME);
        Assertions.assertFalse(checkUsername);
        boolean checkPassword = FormatValidation.isFormatValid("Ab12@", FormatValidation.PASSWORD_LENGTH);
        Assertions.assertFalse(checkPassword);
        checkPassword = FormatValidation.isFormatValid("Abc123456789", FormatValidation.PASSWORD_LETTERS);
        Assertions.assertFalse(checkPassword);
        checkPassword = FormatValidation.isFormatValid("Abc.123456789", FormatValidation.PASSWORD_LETTERS);
        Assertions.assertTrue(checkPassword);
    }

    @Test
    public void signUp() {
        String command;
        Matcher matcher;
        HashMap<String, String> inputs;
        SignupController controller = new SignupController();
        //Empty Field:
        command = "user create -u username -email email -p password -s   -n nickname";
        matcher = SignupAndLoginCommand.getMatcher(command, SignupAndLoginCommand.CREATE_USER);
        inputs =
                SignupAndLoginUtils.getInputs(matcher, SignupAndLoginCommand.CREATE_USER.getRegex());
        Assertions.assertEquals(controller.signup(inputs), SignupAndLoginMessage.EMPTY_FIELD);
        //Empty Field:
        command = "user create -u username  -p password   -n nickname";
        matcher = SignupAndLoginCommand.getMatcher(command, SignupAndLoginCommand.CREATE_USER);
        inputs =
                SignupAndLoginUtils.getInputs(matcher, SignupAndLoginCommand.CREATE_USER.getRegex());
        Assertions.assertEquals(controller.signup(inputs), SignupAndLoginMessage.EMPTY_FIELD);
        command = "user create -u username -email email -p password    -n nickname";
        matcher = SignupAndLoginCommand.getMatcher(command, SignupAndLoginCommand.CREATE_USER);
        inputs =
                SignupAndLoginUtils.getInputs(matcher, SignupAndLoginCommand.CREATE_USER.getRegex());
        controller.changeNullSloganToEmpty(inputs);
        Assertions.assertNotEquals(controller.signup(inputs), SignupAndLoginMessage.EMPTY_FIELD);
    }
}

