import DAO.DAOFactory;
import DAO.UserDAOImpl;
import entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class TestUserDAO {
    public static void testUserDAO(){
        UserDAOImpl userDAO = DAOFactory.getInstance().getUserDAO();
        List<User> userList;

        // find all trains
        userList = userDAO.findAllUsers();
        HelpedMethods.viewAllUsers(userList);

        // add new train
        System.out.println("~~~~~~~~~Added new Test user~~~~~~~~~");
        User user = new User();
        user.setUpdateTime(LocalDateTime.now());
        user.setCreateTime(LocalDateTime.now());
        user.setFirstName("Test2");
        user.setLastName("Test2");
        user.setUsername("Test2");
        user.setPassword("testpass2");
        user.setUserEmail("testEmail2@gmail.com");
        user.setUserCountOfMoney(BigDecimal.ZERO);
        user.setAccountVerified(true);
        user.setUserRole("USER");
        userDAO.addUser(user);
        userList = userDAO.findAllUsers();
        HelpedMethods.viewAllUsers(userList);

        // remove new train
        System.out.println("~~~~~~~~~Set account status for Test user~~~~~~~~~");
        userDAO.setUserAccountVerified("Test2");
        HelpedMethods.viewAllUsers(userList);
    }
}
