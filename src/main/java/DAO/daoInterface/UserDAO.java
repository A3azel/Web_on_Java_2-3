package DAO.daoInterface;

import entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserDAO {
    void addUser(User user);
    List<User> findAllUsers();
    User findUserByUsername(String username);
    User findUserByID(int id);
    void setUserAccountVerified(String username);
    void topUpAccount(BigDecimal money, String username);
    void spendMoney(BigDecimal money,String username);
    boolean isUserExist(String username,String password);
}
