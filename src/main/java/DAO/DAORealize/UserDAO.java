package DAO.DAORealize;

import DAO.AbstractDAO;
import DAO.DAOFactory;
import DAO.DAOInterface.UserDAOI;
import entity.User;
import helpDAO.DAOHelperMethods;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UserDAO extends AbstractDAO implements UserDAOI {
    // table filed
    private static final String ID = "id";
    private static final String CREATE_TIME = "create_time";
    private static final String UPDATE_TIME = "update_time";
    private static final String USERNAME = "username";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String USER_PASSWORD = "user_password";
    private static final String USER_COUNT_OF_MONEY = "user_count_of_money";
    private static final String ACCOUNT_VERIFIED = "account_verified";
    private static final String USER_EMAIL = "user_email";
    private static final String ROLE_ID = "role_id";

    // SQL requests
    private static final String ADD_USER = "INSERT INTO user_info(create_time, update_time, username, first_name, last_name, user_password" +
            ", user_count_of_money, account_verified, user_email, role_id) VALUE(?,?,?,?,?,?,?,?,?,?)";
    private static final String SET_USER_ACCOUNT_VERIFIED = "UPDATE user_info SET account_verified = ? WHERE id = ?";
    private static final String FIND_USER = "SELECT * FROM user_info WHERE username = ?";
    private static final String FIND_USER_BY_ID = "SELECT * FROM user_info WHERE id = ?";
    private static final String FIND_ALL_USERS = "SELECT * FROM user_info";
    private static final String TOP_UP_ACCOUNT = "UPDATE user_info SET user_count_of_money = user_count_of_money + ? WHERE id = ?";
    private static final String SPEND_MONEY = "UPDATE user_info SET user_count_of_money = user_count_of_money - ? WHERE id = ?";
    private static final String IS_USER_EXIST = "SELECT * FROM user_info WHERE username = ? AND user_password = ?";

    private static UserDAO userDAO;

    private UserDAO(){

    }

    public static synchronized UserDAO getInstance(){
        if(userDAO == null){
            return new UserDAO();
        }
        return userDAO;
    }

    public void addUser(User user){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            preparedStatement = con.prepareStatement(ADD_USER);
            Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
            preparedStatement.setTimestamp(1,timestamp);
            preparedStatement.setTimestamp(2,timestamp);
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setBigDecimal(7, user.getUserCountOfMoney());
            preparedStatement.setBoolean(8, user.isAccountVerified());
            preparedStatement.setString(9, user.getUserEmail());
            preparedStatement.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            DAOHelperMethods.rollback(con);
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DAOHelperMethods.closeCon(preparedStatement);
            DAOHelperMethods.closeCon(con);
        }
    }

    public List<User> findAllUsers(){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        List<User> userList = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement = con.prepareStatement(FIND_ALL_USERS);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                userList.add(helpToBuildUser(rs));
            }
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            DAOHelperMethods.rollback(con);
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DAOHelperMethods.closeCon(preparedStatement);
            DAOHelperMethods.closeCon(con);
        }
        return userList;
    }

    public User findUserByUsername(String username){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement = con.prepareStatement(FIND_USER);
            preparedStatement.setString(1,username);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                con.commit();
                return helpToBuildUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            DAOHelperMethods.rollback(con);
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DAOHelperMethods.closeCon(preparedStatement);
            DAOHelperMethods.closeCon(con);
        }
        return new User();
    }

    public User findUserByID(int id){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement = con.prepareStatement(FIND_USER_BY_ID);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                con.commit();
                return helpToBuildUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            DAOHelperMethods.rollback(con);
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DAOHelperMethods.closeCon(preparedStatement);
            DAOHelperMethods.closeCon(con);
        }
        return new User();
    }

    public void setUserAccountVerified(String username){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            preparedStatement = con.prepareStatement(SET_USER_ACCOUNT_VERIFIED);
            boolean relevant = findUserByUsername(username).isAccountVerified();
            preparedStatement.setBoolean(1,!relevant);
            preparedStatement.setString(2,username);
            preparedStatement.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            DAOHelperMethods.rollback(con);
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DAOHelperMethods.closeCon(preparedStatement);
            DAOHelperMethods.closeCon(con);
        }
    }

    public void topUpAccount(BigDecimal money,String username) {
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        if(money.compareTo(BigDecimal.valueOf(0))<=0){
            throw new IllegalArgumentException("Гроші не можуть бути відємними");
        }
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            preparedStatement = con.prepareStatement(TOP_UP_ACCOUNT);
            preparedStatement.setBigDecimal(1,money);
            preparedStatement.setString(2,username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            DAOHelperMethods.rollback(con);
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DAOHelperMethods.closeCon(preparedStatement);
            DAOHelperMethods.closeCon(con);
        }
    }

    public void spendMoney(BigDecimal money,String username){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        BigDecimal userBalance = findUserByUsername(username).getUserCountOfMoney();
        if(userBalance.compareTo(money)<=0){
            throw new IllegalArgumentException("Недостатньо коштів на рахунку");
        }
        try{
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            preparedStatement = con.prepareStatement(SPEND_MONEY);
            preparedStatement.setBigDecimal(1,money);
            preparedStatement.setString(2,username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            DAOHelperMethods.rollback(con);
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DAOHelperMethods.closeCon(preparedStatement);
            DAOHelperMethods.closeCon(con);
        }
    }

// need to test
    public boolean isUserExist(String username,String password){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;

        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement = con.prepareStatement(IS_USER_EXIST);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            DAOHelperMethods.rollback(con);
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DAOHelperMethods.closeCon(preparedStatement);
            DAOHelperMethods.closeCon(con);
        }
        return false;
    }

    // add role
    public User helpToBuildUser(ResultSet rs) throws SQLException {
        User user = new User();
        int id = rs.getInt(ID);
        LocalDateTime createTime = rs.getTimestamp(CREATE_TIME).toLocalDateTime();
        LocalDateTime updateTime = rs.getTimestamp(UPDATE_TIME).toLocalDateTime();
        RoleDAO roleDAO = DAOFactory.getInstance().getRoleDAO();
        String username = rs.getString(USERNAME);
        String firstName = rs.getString(FIRST_NAME);
        String lastName = rs.getString(LAST_NAME);
        String userPassword = rs.getString(USER_PASSWORD);
        BigDecimal userCountOfMoney = rs.getBigDecimal(USER_COUNT_OF_MONEY);
        boolean accountVerified = rs.getBoolean(ACCOUNT_VERIFIED);
        String userEmail = rs.getString(USER_EMAIL);
        String userRole = roleDAO.findUserRoleByID(rs.getInt(ROLE_ID));

        user.setID(id);
        user.setCreateTime(createTime);
        user.setUpdateTime(updateTime);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(userPassword);
        user.setUserCountOfMoney(userCountOfMoney);
        user.setAccountVerified(accountVerified);
        user.setUserEmail(userEmail);
        user.setUserRole(userRole);

        return user;
    }

}

