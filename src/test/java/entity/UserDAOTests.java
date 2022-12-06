package entity;

import customExceptions.userExeptions.InvalidCountOfMoney;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ServiceFactory;
import service.serviceInterfaces.UserService;

import java.math.BigDecimal;

public class UserDAOTests {

    private static final String USERNAME = "TestUser";
    private static final String USER_ROLE = "USER";
    private static final String USER_PASSWORD = "12345678";

    private UserService userService;

    @BeforeEach
    public void init(){
        userService = ServiceFactory.getInstance().getUserService();
    }

    @Test
    public void findUsernameByID(){
        Assertions.assertEquals("Test", userService.findUserByID(1L).getUsername());
    }

    @Test
    public void addUser() {
        int numberUsersBeforeAdding = userService.allUsersCount();
        User user = new User();
        user.setUsername(USERNAME);
        user.setLastName(USERNAME);
        user.setFirstName(USERNAME);
        user.setUserRole(USER_ROLE);
        user.setUserEmail("testUserEmail@gmail.com");
        user.setAccountVerified(true);
        user.setPassword(USER_PASSWORD);
        userService.addUser(user,USER_PASSWORD);
        int numberUsersAfterAdding = userService.allUsersCount();

        Assertions.assertEquals(numberUsersBeforeAdding+1,numberUsersAfterAdding);
    }

    @Test
    public void topUpAccount() throws InvalidCountOfMoney {
        userService.topUpAccount(String.valueOf(10_000),USERNAME);
        User user = userService.findUserByUsername(USERNAME);
        Assertions.assertEquals(user.getUserCountOfMoney(),BigDecimal.valueOf(1000000,2));
    }

    @Test
    public void blockUser(){
        User user = userService.findUserByUsername(USERNAME);
        userService.setUserAccountVerified(user.getID());
        Assertions.assertFalse(userService.findUserByUsername(USERNAME).isAccountVerified());
    }
}
