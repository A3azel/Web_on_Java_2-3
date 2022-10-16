package DAO.DAORealize;

import DAO.AbstractDAO;
import DAO.DAOInterface.CityDAOI;
import entity.City;
import helpDAO.DAOHelperMethods;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CityDAO extends AbstractDAO implements CityDAOI {
    // table filed
    private static final String ID = "id";
    private static final String CREATE_TIME = "create_time";
    private static final String UPDATE_TIME = "update_time";
    private static final String CITY_NAME = "city_name";
    private static final String RELEVANT = "relevant";

    // SQL requests
    private static final String ADD_CITY = "INSERT INTO cities(create_time,update_time,city_name,relevant) VALUE(?,?,?,?)";
    private static final String UPDATE_CITY = "UPDATE cities SET update_time= ?, city_name = ?,relevant = ? WHERE id = ?";
    private static final String SET_CITY_RELEVANT = "UPDATE cities SET relevant = ? WHERE city_name = ?";
    private static final String FIND_CITY = "SELECT * FROM cities WHERE city_name = ?";
    private static final String FIND_CITY_BY_ID = "SELECT * FROM cities WHERE id = ?";
    private static final String FIND_ALL_CITES = "SELECT * FROM cities";

    private static CityDAO cityDAO;

    private CityDAO() {
    }

    public static synchronized CityDAO getInstance(){
        if(cityDAO == null){
            return new CityDAO();
        }
        return cityDAO;
    }

    public void addCity(City city){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            preparedStatement = con.prepareStatement(ADD_CITY);
            Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
            preparedStatement.setTimestamp(1,timestamp);
            preparedStatement.setTimestamp(2,timestamp);
            preparedStatement.setString(3,city.getCityName());
            preparedStatement.setBoolean(4,true);
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


    public void updateCity(City city){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            preparedStatement = con.prepareStatement(UPDATE_CITY);
            Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
            preparedStatement.setTimestamp(1,timestamp);
            preparedStatement.setString(2,city.getCityName());
            preparedStatement.setBoolean(3, city.isRelevant());
            preparedStatement.setInt(4,city.getID());
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

    public List<City> findAllCites(){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        List<City> cityList = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement = con.prepareStatement(FIND_ALL_CITES);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                cityList.add(helpToBuildCity(rs));
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
        return cityList;
    }

    public City findCityByCityName(String cityName){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement = con.prepareStatement(FIND_CITY);
            preparedStatement.setString(1,cityName);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                con.commit();
                return helpToBuildCity(rs);
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
        return new City();
    }

    public City findCityByID(int id){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement = con.prepareStatement(FIND_CITY_BY_ID);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                con.commit();
                return helpToBuildCity(rs);
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
        return new City();
    }

    public void setCityRelevant(String cityName){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            preparedStatement = con.prepareStatement(SET_CITY_RELEVANT);
            boolean relevant = findCityByCityName(cityName).isRelevant();
            preparedStatement.setBoolean(1,!relevant);
            preparedStatement.setString(2,cityName);
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

    public City helpToBuildCity(ResultSet rs) throws SQLException {
        City city = new City();
        int id = rs.getInt(ID);
        LocalDateTime createTime = rs.getTimestamp(CREATE_TIME).toLocalDateTime();
        LocalDateTime updateTime = rs.getTimestamp(UPDATE_TIME).toLocalDateTime();
        String cityName = rs.getString(CITY_NAME);
        boolean relevant = rs.getBoolean(RELEVANT);
        city.setID(id);
        city.setCreateTime(createTime);
        city.setUpdateTime(updateTime);
        city.setCityName(cityName);
        city.setRelevant(relevant);
        return city;
    }

}
