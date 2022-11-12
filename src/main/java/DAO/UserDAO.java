package DAO;

import entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserDAO {
    void addUser(User user);
    List<User> findAllUsers(int offset, int noOfRecords);
    int allUsersCount();
    boolean isEmailExist(String email);
    User findUserByUsername(String username);
    String findUserPasswordByUsername(String username);
    User findUserByID(Long id);
    void setUserAccountVerified(Long id);
    void topUpAccount(BigDecimal money, String username);
    void spendMoney(BigDecimal money,String username);
    boolean isUserExist(String username,String password);
}
