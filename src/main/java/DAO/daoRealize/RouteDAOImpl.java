package DAO.daoRealize;

import DAO.AbstractDAO;
import DAO.DAOFactory;
import DAO.daoInterface.CityDAO;
import DAO.daoInterface.RouteDAO;
import DAO.daoInterface.StationDAO;
import DAO.daoInterface.TrainDAO;
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

public class RouteDAOImpl extends AbstractDAO implements RouteDAO {
    // table filed
    private static final String ID = "id";
    private static final String CREATE_TIME = "create_time";
    private static final String UPDATE_TIME = "update_time";
    private static final String DEPARTURE_CITY_ID = "departure_city_id";
    private static final String ARRIVAL_CITY_ID = "arrival_city_id";
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
    private static final String ADD_ROUTE = "INSERT INTO route(create_time, update_time, departure_city_id,arrival_city_id, start_station_id, departure_time, travel_time" +
            ", arrival_station_id, arrival_time, train_id, number_of_free_seats, prise_of_ticket, relevant) VALUE(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SET_ROUTE_RELEVANT = "UPDATE route SET relevant = ? WHERE id = ?";
    private static final String FIND_ROUTE_BETWEEN_TWO_STATIONS = "SELECT * FROM route WHERE start_station_id = ? AND arrival_station_id = ? AND departure_time BETWEEN ? and ?";
    private static final String FIND_ROUTE_BETWEEN_TWO_CITES = "SELECT * FROM route as R\n" +
            "LEFT OUTER JOIN station as S on R.start_station_id = S.id\n" +
            "LEFT OUTER JOIN station as SL on R.arrival_station_id = SL.id\n" +
            "WHERE S.city_id = ? and SL.city_id = ? AND departure_time BETWEEN ? and ?";
    private static final String FIND_ROUTE_BY_ID = "SELECT * FROM route WHERE id = ?";
    private static final String FIND_ALL_ROUTES = "SELECT * FROM route";

    private static RouteDAOImpl routeDAO;

    private RouteDAOImpl(){

    }

    public static synchronized RouteDAOImpl getInstance(){
        if(routeDAO == null){
            return new RouteDAOImpl();
        }
        return routeDAO;
    }

    @Override
    public void addRoute(Route route){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            preparedStatement = con.prepareStatement(ADD_ROUTE);
            Calendar calendar = Calendar.getInstance();
            StationDAO stationDAO = DAOFactory.getInstance().getStationDAO();
            CityDAO cityDAO = DAOFactory.getInstance().getCityDAO();
            TrainDAO trainDAO = DAOFactory.getInstance().getTrainDAO();
            Long startStationID = stationDAO.findStationByStationName(route.getStartStation()).getID();
            Long arrivalStationID = stationDAO.findStationByStationName(route.getArrivalStation()).getID();
            Long departureCityID = cityDAO.findCityByCityName(route.getDepartureCity()).getID();
            Long arrivalCityID = cityDAO.findCityByCityName(route.getArrivalCity()).getID();
            Long trainID = trainDAO.findTrainByTrainNumber(route.getTrain()).getID();
            java.sql.Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
            preparedStatement.setTimestamp(1,timestamp);
            preparedStatement.setTimestamp(2,timestamp);
            preparedStatement.setLong(3,departureCityID);
            preparedStatement.setLong(4,arrivalCityID);
            preparedStatement.setLong(5,startStationID);
            preparedStatement.setTimestamp(6, Timestamp.valueOf(route.getDepartureTime()));
            preparedStatement.setTime(7, Time.valueOf(route.getTravelTime()));
            preparedStatement.setLong(8,arrivalStationID);
            preparedStatement.setTimestamp(9, Timestamp.valueOf(route.getArrivalTime()));
            preparedStatement.setLong(10,trainID);
            preparedStatement.setInt(11,route.getNumberOfFreeSeats());
            preparedStatement.setBigDecimal(12,route.getPriseOfTicket());
            preparedStatement.setBoolean(13,true);
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
    public List<Route> findAllRouts(){
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

    @Override
    public Route findRouteByID(Long id){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement = con.prepareStatement(FIND_ROUTE_BY_ID);
            preparedStatement.setLong(1,id);
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

    @Override
    public void setRouteRelevant(Long id){
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

    @Override
    public List<Route> findAllBetweenTwoStations(String startStation, String arrivalStation, LocalDate data, LocalTime localTime){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        List<Route> routeList = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            StationDAOImpl stationDAO = DAOFactory.getInstance().getStationDAO();
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            preparedStatement = con.prepareStatement(FIND_ROUTE_BETWEEN_TWO_STATIONS);
            preparedStatement.setLong(1,stationDAO.findStationByStationName(startStation).getID());
            preparedStatement.setLong(2,stationDAO.findStationByStationName(arrivalStation).getID());
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

    @Override
    public List<Route> findAllBetweenTwoCites(String startCity, String arrivalCity, LocalDate data, LocalTime localTime){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        List<Route> routeList = new ArrayList<>();
        try {
            CityDAOImpl cityDAO = DAOFactory.getInstance().getCityDAO();
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            preparedStatement = con.prepareStatement(FIND_ROUTE_BETWEEN_TWO_CITES);
            preparedStatement.setLong(1,cityDAO.findCityByCityName(startCity).getID());
            preparedStatement.setLong(2,cityDAO.findCityByCityName(arrivalCity).getID());
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
        Long id = rs.getLong(ID);
        LocalDateTime createTime = rs.getTimestamp(CREATE_TIME).toLocalDateTime();
        LocalDateTime updateTime = rs.getTimestamp(UPDATE_TIME).toLocalDateTime();
        StationDAO stationDAO = DAOFactory.getInstance().getStationDAO();
        TrainDAO trainDAO = DAOFactory.getInstance().getTrainDAO();
        CityDAO cityDAO = DAOFactory.getInstance().getCityDAO();
        String startStation = stationDAO.findStationByID(rs.getLong(START_STATION_ID)).getStationName();
        LocalDateTime departureTime = rs.getTimestamp(DEPARTURE_TIME).toLocalDateTime();
        LocalTime travelTime = LocalTime.parse(new SimpleDateFormat("HH:mm").format(rs.getTime(TRAVEL_TIME)));
        String departureCity = cityDAO.findCityByID(rs.getLong(DEPARTURE_CITY_ID)).getCityName();
        String arrivalCity = cityDAO.findCityByID(rs.getLong(ARRIVAL_CITY_ID)).getCityName();
        String arrivalStation = stationDAO.findStationByID(rs.getLong(ARRIVAL_STATION_ID)).getStationName();
        LocalDateTime arrivalTime = rs.getTimestamp(ARRIVAL_TIME).toLocalDateTime();
        int numberOfFreeSeats = rs.getInt(NUMBER_OF_FREE_SEATS);
        BigDecimal priseOfTicket = rs.getBigDecimal(PRISE_OF_TICKET);
        String train = trainDAO.findTrainByID(rs.getLong(TRAIN_ID)).getTrainNumber();
        boolean relevant = rs.getBoolean(RELEVANT);
        route.setID(id);
        route.setCreateTime(createTime);
        route.setUpdateTime(updateTime);
        route.setDepartureCity(departureCity);
        route.setArrivalCity(arrivalCity);
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
