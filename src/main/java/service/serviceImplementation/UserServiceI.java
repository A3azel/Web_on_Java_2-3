package service.serviceImplementation;

import DAO.DAOFactory;
import DAO.UserDAOImpl;
import customExceptions.userExeptions.InvalidCountOfMoney;
import entity.User;
import security.SecurityHelperMethods;
import service.serviceInterfaces.UserService;
import validation.Validator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceI implements UserService {
    private static UserServiceI instance;
    private final UserDAOImpl userDAO;

    public UserServiceI() {
        userDAO = DAOFactory.getInstance().getUserDAO();
    }

    public static synchronized UserServiceI getInstance(){
        if(instance==null){
            return new UserServiceI();
        }
        return instance;
    }

    @Override
    public Map<String, String> addUser(User user,String submitPassword) {
        String username = user.getUsername();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String email = user.getUserEmail();
        String password = user.getPassword();
        String hashPassword = null;

        HashMap<String,String> validationErrors = new HashMap<>();

        if(!Validator.isFirstNameOrLastNameValid(firstName)){
            validationErrors.put("firstNameError","Не валідне ім'я");
        }
        if(!Validator.isFirstNameOrLastNameValid(lastName)){
            validationErrors.put("lastNameError","Не валідне прізвище");
        }
        if(!Validator.isUsernameValid(username)){
            validationErrors.put("usernameError","Не валідний нікнейм (від 4 до 64 символів)");
        }
        if(!Validator.isEmailValid(email)){
            validationErrors.put("emailError","Не валідна пошта");
        }
        if(!Validator.isPasswordValid(password)){
            validationErrors.put("firstPasswordError","Довжина паролю повинна бути від 8 до 64 символів");
        }
        if(!Validator.isPasswordValid(submitPassword)){
            validationErrors.put("secondPasswordError","Довжина паролю повинна бути від 8 до 64 символів");
        }
        if(!Validator.isTheSamePasswords(password, submitPassword)){
            validationErrors.put("passwordsError","Паролі відрізняються");
        }

        if (!validationErrors.isEmpty()){
            return validationErrors;
        }

        if(Validator.isUserExist(username)){
            validationErrors.put("usernameError","Даний нікнейм вже зарезервований");
        }
        if(Validator.isEmailExist(email)){
            validationErrors.put("emailError","Дана пошта вже зарезервована");
        }

        if (!validationErrors.isEmpty()){
            return validationErrors;
        }
        try {
            hashPassword = SecurityHelperMethods.getSaltedHash(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        user.setPassword(hashPassword);

        userDAO.addUser(user);
        return validationErrors;
    }

    @Override
    public int allUsersCount() {
        return userDAO.allUsersCount();
    }

    @Override
    public List<User> findAllUsers(int offset, int noOfRecords) {
        return userDAO.findAllUsers(offset, noOfRecords);
    }

    @Override
    public User findUserByUsername(String username) {
        return userDAO.findUserByUsername(username);
    }

    @Override
    public User findUserByID(Long id) {
        return userDAO.findUserByID(id);
    }

    @Override
    public String findUserPassword(String username) {
        return userDAO.findUserPasswordByUsername(username);
    }

    @Override
    public boolean isEmailExist(String email) {
        return userDAO.isEmailExist(email);
    }

    @Override
    public void setUserAccountVerified(Long id) {
        userDAO.setUserAccountVerified(id);

    }

    @Override
    public void topUpAccount(String money, String username) throws InvalidCountOfMoney {
        if(!Validator.isCountOfMoneyValid(money)){
            //throw new InvalidCountOfMoney("Приклад вводу: 100000.00");
        }
        BigDecimal countOfMoney = BigDecimal.valueOf(Double.parseDouble(money));
        userDAO.topUpAccount(countOfMoney, username);
    }

    @Override
    public void spendMoney(BigDecimal money, String username) {
        userDAO.spendMoney(money, username);
    }

    @Override
    public boolean isUserExist(String username, String password) {
        String userPassword = findUserPassword(username);
        if(userPassword==null){
            return false;
        }
        try {
            return SecurityHelperMethods.check(password,userPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
