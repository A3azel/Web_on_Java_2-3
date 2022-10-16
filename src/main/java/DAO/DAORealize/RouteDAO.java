package DAO.DAORealize;

import DAO.AbstractDAO;
import DAO.DAOFactory;
import DAO.DAOInterface.RouteDAOI;
import entity.Route;
import helpDAO.DAOHelperMethods;

import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RouteDAO extends AbstractDAO implements RouteDAOI {
    // table filed
    private static final String ID = "id";
    private static final String CREATE_TIME = "create_time";
    private static final String UPDATE_TIME = "update_time";
    private static final String START_STATION_ID = "start_station_id";
    private static final String DEPARTURE_TIME = "departure_time";
    private static final String TRAVEL_TIME = "travel_time";
    private static final String ARRIVAL_STATION_ID = "arrival_station_id";
    private static final String ARRIVAL_TIME = "arrival_time";
    private static final String TRAIN_ID = "train_id";
    private static final String NUMBER_OF_FREE_SEATS = "number_of_free_seats";
    private static final String PRISE_OF_TICKET = "prise_of_ticket";
    private static final String RELEVANT = "relevant";

    // SQL requests
    private static final String ADD_ROUTE = "INSERT INTO route(create_time, update_time, start_station_id, departure_time, travel_time" +
            ", arrival_station_id, arrival_time, train_id, number_of_free_seats, prise_of_ticket, relevant) VALUE(?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SET_ROUTE_RELEVANT = "UPDATE route SET relevant = ? WHERE id = ?";
    private static final String FIND_ROUTE_BETWEEN_TWO_STATIONS = "SELECT * FROM route WHERE start_station_id = ? AND arrival_station_id = ? AND departure_time BETWEEN ? and ?";
    private static final String FIND_ROUTE_BETWEEN_TWO_CITES = "SELECT * FROM route as R\n" +
            "LEFT OUTER JOIN station_list as S on R.start_station_id = S.id\n" +
            "LEFT OUTER JOIN station_list as SL on R.arrival_station_id = SL.id\n" +
            "WHERE S.city_id = ? and SL.city_id = ? AND departure_time BETWEEN ? and ?";
    private static final String FIND_ROUTE_BY_ID = "SELECT * FROM route WHERE id = ?";
    private static final String FIND_ALL_ROUTES = "SELECT * FROM route";

    private static RouteDAO routeDAO;

    private RouteDAO(){

    }

    public static synchronized RouteDAO getInstance(){
        if(routeDAO == null){
            return new RouteDAO();
        }
        return routeDAO;
    }

    public void addRoute(Route route){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            preparedStatement = con.prepareStatement(ADD_ROUTE);
            Calendar calendar = Calendar.getInstance();
            StationDAO stationDAO = DAOFactory.getInstance().getStationDAO();
            TrainDAO trainDAO = DAOFactory.getInstance().getTrainDAO();
            int startStationID = stationDAO.findStationByStationName(route.getStartStation()).getID();
            int arrivalStationID = stationDAO.findStationByStationName(route.getArrivalStation()).getID();
            int trainID = trainDAO.findTrainByTrainNumber(route.getTrain()).getID();
            java.sql.Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
            preparedStatement.setTimestamp(1,timestamp);
            preparedStatement.setTimestamp(2,timestamp);
            preparedStatement.setInt(3,startStationID);
            preparedStatement.setTimestamp(4, Timestamp.valueOf(route.getDepartureTime()));
            preparedStatement.setTime(5, Time.valueOf(route.getTravelTime()));
            preparedStatement.setInt(6,arrivalStationID);
            preparedStatement.setTimestamp(7, Timestamp.valueOf(route.getArrivalTime()));
            preparedStatement.setInt(8,trainID);
            preparedStatement.setInt(9,route.getNumberOfFreeSeats());
            preparedStatement.setBigDecimal(10,route.getPriseOfTicket());
            preparedStatement.setBoolean(11,true);
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

    public List<Route> findAllUsers(){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        List<Route> routeList = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement = con.prepareStatement(FIND_ALL_ROUTES);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                routeList.add(helpToBuildRote(rs));
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
        return routeList;
    }

    public Route findRouteByID(int id){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement = con.prepareStatement(FIND_ROUTE_BY_ID);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                con.commit();
                return helpToBuildRote(rs);
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
        return new Route();
    }

    public void setRouteRelevant(int id){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            preparedStatement = con.prepareStatement(SET_ROUTE_RELEVANT);
            boolean relevant = findRouteByID(id).isRelevant();
            preparedStatement.setBoolean(1,!relevant);
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

    public List<Route> findAllBetweenTwoStations(String startStation, String arrivalStation, LocalDate data, LocalTime localTime){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        List<Route> routeList = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            StationDAO stationDAO = DAOFactory.getInstance().getStationDAO();
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            preparedStatement = con.prepareStatement(FIND_ROUTE_BETWEEN_TWO_STATIONS);
            preparedStatement.setInt(1,stationDAO.findStationByStationName(startStation).getID());
            preparedStatement.setInt(2,stationDAO.findStationByStationName(arrivalStation).getID());
            LocalDateTime localDateTime = LocalDateTime.of(data,localTime);
            LocalDateTime finalLocalDateTime = localDateTime.plusDays(7);
            preparedStatement.setTimestamp(3, Timestamp.valueOf(localDateTime));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(finalLocalDateTime));
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                routeList.add(helpToBuildRote(rs));
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
        return routeList;
    }

    public List<Route> findAllBetweenTwoCites(String startCity, String arrivalCity, LocalDate data, LocalTime localTime){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        List<Route> routeList = new ArrayList<>();
        try {
            CityDAO cityDAO = DAOFactory.getInstance().getCityDAO();
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            preparedStatement = con.prepareStatement(FIND_ROUTE_BETWEEN_TWO_CITES);
            preparedStatement.setInt(1,cityDAO.findCityByCityName(startCity).getID());
            preparedStatement.setInt(2,cityDAO.findCityByCityName(arrivalCity).getID());
            LocalDateTime localDateTime = LocalDateTime.of(data,localTime);
            LocalDateTime finalLocalDateTime = localDateTime.plusDays(7);
            preparedStatement.setTimestamp(3, Timestamp.valueOf(localDateTime));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(finalLocalDateTime));
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                routeList.add(helpToBuildRote(rs));
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
        return routeList;
    }

    public Route helpToBuildRote(ResultSet rs) throws SQLException {
        Route route = new Route();
        int id = rs.getInt(ID);
        LocalDateTime createTime = rs.getTimestamp(CREATE_TIME).toLocalDateTime();
        LocalDateTime updateTime = rs.getTimestamp(UPDATE_TIME).toLocalDateTime();
        StationDAO stationDAO = DAOFactory.getInstance().getStationDAO();
        TrainDAO trainDAO = DAOFactory.getInstance().getTrainDAO();
        String startStation = stationDAO.findStationByID(rs.getInt(START_STATION_ID)).getStationName();
        LocalDateTime departureTime = rs.getTimestamp(DEPARTURE_TIME).toLocalDateTime();
        LocalTime travelTime = LocalTime.parse(new SimpleDateFormat("HH:mm").format(rs.getTime(TRAVEL_TIME)));
        String arrivalStation = stationDAO.findStationByID(rs.getInt(ARRIVAL_STATION_ID)).getStationName();
        LocalDateTime arrivalTime = rs.getTimestamp(ARRIVAL_TIME).toLocalDateTime();
        int numberOfFreeSeats = rs.getInt(NUMBER_OF_FREE_SEATS);
        BigDecimal priseOfTicket = rs.getBigDecimal(PRISE_OF_TICKET);
        String train = trainDAO.findTrainByID(rs.getInt(TRAIN_ID)).getTrainNumber();
        boolean relevant = rs.getBoolean(RELEVANT);
        route.setID(id);
        route.setCreateTime(createTime);
        route.setUpdateTime(updateTime);
        route.setStartStation(startStation);
        route.setDepartureTime(departureTime);
        route.setTravelTime(travelTime);
        route.setArrivalStation(arrivalStation);
        route.setArrivalTime(arrivalTime);
        route.setNumberOfFreeSeats(numberOfFreeSeats);
        route.setPriseOfTicket(priseOfTicket);
        route.setTrain(train);
        route.setRelevant(relevant);
        return route;
    }


}
