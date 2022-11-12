package service.serviceInterfaces;

import customExceptions.userExeptions.InvalidCountOfMoney;
import entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface UserService {
    Map<String, String> addUser(User user,String submitPassword);
    List<User> findAllUsers(int offset, int noOfRecords);
    int allUsersCount();
    User findUserByUsername(String username);
    User findUserByID(Long id);
    String findUserPassword(String username);
    boolean isEmailExist(String email);
    void setUserAccountVerified(Long id);
    void topUpAccount(String money, String username) throws InvalidCountOfMoney;
    void spendMoney(BigDecimal money,String username);
    boolean isUserExist(String username,String password);
}
