package service.serviceImplementation;

import DAO.DAOFactory;
import DAO.UserDAOImpl;
import entity.User;
import service.serviceInterfaces.UserService;
import validation.RegistrationValidator;

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

        HashMap<String,String> validationErrors = new HashMap<>();

        if(!RegistrationValidator.isFirstNameOrLastNameValid(firstName)){
            validationErrors.put("firstNameError","First name is invalid");
        }
        if(!RegistrationValidator.isFirstNameOrLastNameValid(lastName)){
            validationErrors.put("lastNameError","Last name is invalid");
        }
        if(!RegistrationValidator.isUsernameValid(username)){
            System.out.println(username);
            validationErrors.put("usernameError","Username is invalid");
        }
        if(!RegistrationValidator.isEmailValid(email)){
            System.out.println(email);
            validationErrors.put("emailError","Email is invalid");
        }
        if(!RegistrationValidator.isPasswordValid(password)){
            validationErrors.put("firstPasswordError","Length must be from 8 to 64 characters");
        }
        if(!RegistrationValidator.isPasswordValid(submitPassword)){
            validationErrors.put("secondPasswordError","Length must be from 8 to 64 characters");
        }
        if(!RegistrationValidator.isTheSamePasswords(password, submitPassword)){
            validationErrors.put("passwordsError","Different passwords");
        }

        if (!validationErrors.isEmpty()){
            return validationErrors;
        }

        if(!RegistrationValidator.isUserExist(username)){
            validationErrors.put("userAlreadyExist","An account with the selected name already exists");
        }
        if(!RegistrationValidator.isEmailExist(email)){
            validationErrors.put("emailAlreadyExist","An account with the selected email already exists");
        }

        if (!validationErrors.isEmpty()){
            return validationErrors;
        }

        userDAO.addUser(user);
        return validationErrors;
    }

    @Override
    public List<User> findAllUsers() {
        return userDAO.findAllUsers();
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
    public boolean isEmailExist(String email) {
        return userDAO.isEmailExist(email);
    }

    @Override
    public void setUserAccountVerified(String username) {
        userDAO.setUserAccountVerified(username);

    }

    @Override
    public void topUpAccount(BigDecimal money, String username) {
        userDAO.topUpAccount(money, username);

    }

    @Override
    public void spendMoney(BigDecimal money, String username) {
        userDAO.spendMoney(money, username);
    }

    @Override
    public boolean isUserExist(String username, String password) {
        return userDAO.isUserExist(username, password);
    }
}
