package DAO.daoRealize;

import DAO.AbstractDAO;
import DAO.daoInterface.RoleDAO;
import helpDAO.DAOHelperMethods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDAOImpl extends AbstractDAO implements RoleDAO {
    // table filed
    private static final String ID = "id";
    private static final String USER_ROLE = "user_role";

    // SQL requests
    private static final String FIND_USER_ROLE_ID = "SELECT user_role FROM user_role WHERE id = ?";
    private static final String FIND_USER_ROLE_ID_BY_ROLE = "SELECT id FROM user_role WHERE user_role = ?";

    private static RoleDAOImpl instance;

    private RoleDAOImpl(){

    }

    public static synchronized RoleDAOImpl getInstance(){
        if(instance == null){
            return new RoleDAOImpl();
        }
        return instance;
    }

    @Override
    public String findUserRoleByID(Long id){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement = con.prepareStatement(FIND_USER_ROLE_ID);
            preparedStatement.setLong(1,id);
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

    @Override
    public String findIDByUserRole(String role){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement = con.prepareStatement(FIND_USER_ROLE_ID_BY_ROLE);
            preparedStatement.setString(1,role);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                con.commit();
                return rs.getString(ID);
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
        throw new IllegalArgumentException("Role not found");
    }



}
