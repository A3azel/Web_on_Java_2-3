package validation;

import service.ServiceFactory;
import service.serviceInterfaces.UserService;

import java.util.regex.Pattern;

public class RegistrationValidator {

    private static final String FIRST_NAME_AND_LAST_NAME_REGEX = "^[A-Za-zА-Яа-яЁёІіЇїЄє]{1,40}$";
    private static final String USERNAME_REGEX = "\\w{4,64}";
    private static final String EMAIL_REGEX = "^([A-Za-z0-9_-]+\\.)*[A-Za-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";

    public static boolean isUserExist(String username){
        UserService userService = ServiceFactory.getInstance().getUserService();
        return userService.findUserByUsername(username)!= null;
    }

    public static boolean isEmailExist(String email){
        UserService userService = ServiceFactory.getInstance().getUserService();
        return userService.isEmailExist(email);
    }

    public static boolean isFirstNameOrLastNameValid(String name){
        return Pattern.matches(FIRST_NAME_AND_LAST_NAME_REGEX,name);
    }

    public static boolean isUsernameValid(String username){
        return Pattern.matches(USERNAME_REGEX,username);
    }

    public static boolean isEmailValid(String email){
        return Pattern.matches(EMAIL_REGEX,email);
    }

    public static boolean isPasswordValid(String password){
        return password.length()>=8 && password.length()<=64;
    }

    public static boolean isTheSamePasswords(String firstPassword, String secondPassword){
        return firstPassword.equals(secondPassword);
    }

}
