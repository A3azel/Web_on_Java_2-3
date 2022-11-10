package service.serviceInterfaces;

import entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface UserService {
    Map<String, String> addUser(User user,String submitPassword);
    List<User> findAllUsers();
    User findUserByUsername(String username);
    User findUserByID(Long id);
    boolean isEmailExist(String email);
    void setUserAccountVerified(String username);
    void topUpAccount(BigDecimal money, String username);
    void spendMoney(BigDecimal money,String username);
    boolean isUserExist(String username,String password);
}
