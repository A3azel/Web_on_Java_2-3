package DAO.daoRealize;

import DAO.AbstractDAO;
import DAO.DAOFactory;
import DAO.daoInterface.StationDAO;
import entity.Station;
import helpDAO.DAOHelperMethods;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StationDAOImpl extends AbstractDAO implements StationDAO {
    // table filed
    private static final String ID = "id";
    private static final String CREATE_TIME = "create_time";
    private static final String UPDATE_TIME = "update_time";
    private static final String STATION_NAME = "station_name";
    private static final String RELEVANT = "relevant";
    private static final String CITY_ID = "city_id";

    // SQL requests
    private static final String ADD_STATION = "INSERT INTO station_list(create_time,update_time,station_name,relevant,city_id) VALUE(?,?,?,?,?)";
    private static final String UPDATE_STATION = "UPDATE station_list SET update_time= ?, station_name = ?,relevant = ?, city_id = ? WHERE id = ?";
    private static final String SET_STATION_RELEVANT = "UPDATE station_list SET relevant = ? WHERE station_name = ?";
    private static final String FIND_STATION = "SELECT * FROM station_list WHERE station_name = ?";
    private static final String FIND_STATION_BY_ID = "SELECT * FROM station_list WHERE ID = ?";
    private static final String FIND_ALL_STATION = "SELECT * FROM station_list";

    private static StationDAOImpl stationDAO;

    private StationDAOImpl(){

    }

    public static synchronized StationDAOImpl getInstance(){
        if(stationDAO == null){
            return new StationDAOImpl();
        }
        return stationDAO;
    }

    @Override
    public void addStation(Station station){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            preparedStatement = con.prepareStatement(ADD_STATION);
            Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
            preparedStatement.setTimestamp(1,timestamp);
            preparedStatement.setTimestamp(2,timestamp);
            preparedStatement.setString(3,station.getStationName());
            preparedStatement.setBoolean(4,true);
            preparedStatement.setLong(5, station.getCity().getID());
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

    @Override
    public void updateStation(Station station){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            preparedStatement = con.prepareStatement(UPDATE_STATION);
            Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
            preparedStatement.setTimestamp(1,timestamp);
            preparedStatement.setString(2,station.getStationName());
            preparedStatement.setBoolean(3, station.isRelevant());
            preparedStatement.setLong(4,station.getID());
            preparedStatement.setLong(5,station.getCity().getID());
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

    @Override
    public List<Station> findAllStations(){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        List<Station> stationList = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement = con.prepareStatement(FIND_ALL_STATION);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                stationList.add(helpToBuildStation(rs));
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
        return stationList;
    }

    @Override
    public Station findStationByStationName(String stationName){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement = con.prepareStatement(FIND_STATION);
            preparedStatement.setString(1,stationName);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                con.commit();
                return helpToBuildStation(rs);
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
        return new Station();
    }

    @Override
    public Station findStationByID(Long id){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement = con.prepareStatement(FIND_STATION_BY_ID);
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                con.commit();
                return helpToBuildStation(rs);
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
        return new Station();
    }

    @Override
    public void setStationRelevant(String stationName){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            preparedStatement = con.prepareStatement(SET_STATION_RELEVANT);
            boolean relevant = findStationByStationName(stationName).isRelevant();
            preparedStatement.setBoolean(1,!relevant);
            preparedStatement.setString(2,stationName);
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

    public Station helpToBuildStation(ResultSet rs) throws SQLException {
        Station station = new Station();
        Long id = rs.getLong(ID);
        LocalDateTime createTime = rs.getTimestamp(CREATE_TIME).toLocalDateTime();
        LocalDateTime updateTime = rs.getTimestamp(UPDATE_TIME).toLocalDateTime();
        String stationName = rs.getString(STATION_NAME);
        boolean relevant = rs.getBoolean(RELEVANT);
        Long cityID = rs.getLong(CITY_ID);
        station.setID(id);
        station.setCreateTime(createTime);
        station.setUpdateTime(updateTime);
        station.setStationName(stationName);
        station.setRelevant(relevant);
        station.setCity(DAOFactory.getInstance().getCityDAO().findCityByID(cityID));
        return station;
    }



}
