package DAO.DAORealize;

import DAO.AbstractDAO;
import DAO.DAOInterface.RoleDAOI;
import helpDAO.DAOHelperMethods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDAO extends AbstractDAO implements RoleDAOI {
    // table filed
    private static final String ID = "id";
    private static final String USER_ROLE = "user_role";


    // SQL requests
    private static final String FIND_USER_ROLE_ID = "SELECT user_role FROM user_role WHERE id = ?";

    private static RoleDAO instance;

    private RoleDAO(){

    }

    public static synchronized RoleDAO getInstance(){
        if(instance == null){
            return new RoleDAO();
        }
        return instance;
    }

    public String findUserRoleByID(int id){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement = con.prepareStatement(FIND_USER_ROLE_ID);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                con.commit();
                return rs.getString(USER_ROLE);
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
        throw new IllegalArgumentException("");
    }



}
