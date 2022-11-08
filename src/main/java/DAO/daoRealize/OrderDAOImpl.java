package DAO.daoRealize;

import DAO.AbstractDAO;
import DAO.DAOFactory;
import DAO.UserDAOImpl;
import DAO.daoInterface.OrderDAO;
import entity.Order;
import entity.Route;
import entity.User;
import helpDAO.DAOHelperMethods;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderDAOImpl extends AbstractDAO implements OrderDAO {
    // table filed
    private static final String ID = "id";
    private static final String CREATE_TIME = "create_time";
    private static final String UPDATE_TIME = "update_time";
    private static final String ORDER_PRISE = "order_prise";
    private static final String COUNT_OF_PURCHASED_TICKETS = "count_of_purchased_tickets";
    private static final String ORDER_STATUS = "order_status";
    private static final String USER_ID = "user_id";
    private static final String ROUTE_ID = "route_id";

    // SQL requests
    private static final String ADD_ORDER = "INSERT INTO purchased_tickets(create_time, update_time, order_prise, count_of_purchased_tickets" +
            ", order_status, user_id, route_id) VALUE(?,?,?,?,?,?,?)";
    private static final String SET_ORDER_STATUS = "UPDATE purchased_tickets SET ORDER_STATUS = ? WHERE id = ?";
    private static final String FIND_ORDER_BY_ID = "SELECT * FROM purchased_tickets WHERE id = ?";
    private static final String FIND_ALL_USERS_ORDERS = "SELECT * FROM purchased_tickets AS O\n" +
            "LEFT OUTER JOIN user_info AS UI ON O.user_id = UI.id\n" +
            "WHERE UI.username = ?";
    private static final String FIND_ALL_ORDERS = "SELECT * FROM purchased_tickets";
    private static final String IS_ORDER_EXIST = "SELECT * FROM purchased_tickets WHERE id = ? ";

    private static OrderDAOImpl orderDAO;

    private OrderDAOImpl(){

    }

    public static synchronized OrderDAOImpl getInstance(){
        if(orderDAO == null){
            return new OrderDAOImpl();
        }
        return orderDAO;
    }

    @Override
    public void addOrder(Order order){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            preparedStatement = con.prepareStatement(ADD_ORDER);
            Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
            preparedStatement.setTimestamp(1,timestamp);
            preparedStatement.setTimestamp(2,timestamp);
            preparedStatement.setBigDecimal(3, order.getOrderPrise());
            preparedStatement.setInt(4, order.getCountOfPurchasedTickets());
            preparedStatement.setBoolean(5, order.isOrderStatus());
            preparedStatement.setLong(6, order.getUser().getID());
            preparedStatement.setLong(7, order.getRoute().getID() );
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
    public List<Order> findAllOrders(){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        List<Order> orderList = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement = con.prepareStatement(FIND_ALL_ORDERS);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                orderList.add(helpToBuildOrder(rs));
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
        return orderList;
    }

    @Override
    public List<Order> findAllUsersOrders(String username){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        List<Order> orderList = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement = con.prepareStatement(FIND_ALL_USERS_ORDERS);
            preparedStatement.setString(1,username);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                orderList.add(helpToBuildOrder(rs));
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
        return orderList;
    }

    @Override
    public Order findOrderByID(Long id){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement = con.prepareStatement(FIND_ORDER_BY_ID);
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                con.commit();
                return helpToBuildOrder(rs);
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
    public void setOrderStatus(Long id){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            preparedStatement = con.prepareStatement(SET_ORDER_STATUS);
            boolean relevant = findOrderByID(id).isOrderStatus();
            preparedStatement.setBoolean(1,!relevant);
            preparedStatement.setLong(2,id);
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

    // need to test
    @Override
    public boolean isOrderExist(Long id){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;

        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement = con.prepareStatement(IS_ORDER_EXIST);
            preparedStatement.setLong(1,id);
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

    public Order helpToBuildOrder(ResultSet rs) throws SQLException {
        Order order = new Order();
        UserDAOImpl userDAO = DAOFactory.getInstance().getUserDAO();
        RouteDAOImpl routeDAO = DAOFactory.getInstance().getRouteDAO();

        Long id = rs.getLong(ID);
        LocalDateTime createTime = rs.getTimestamp(CREATE_TIME).toLocalDateTime();
        LocalDateTime updateTime = rs.getTimestamp(UPDATE_TIME).toLocalDateTime();
        BigDecimal orderPrise = rs.getBigDecimal(ORDER_PRISE);
        int countOfPurchasedTickets = rs.getInt(COUNT_OF_PURCHASED_TICKETS);
        boolean orderStatus = rs.getBoolean(ORDER_STATUS);
        Long user_id = rs.getLong(USER_ID);
        Long route_id = rs.getLong(ROUTE_ID);
        User selectedUser = userDAO.findUserByID(user_id);
        Route selectedRoute = routeDAO.findRouteByID(route_id);
        order.setID(id);
        order.setCreateTime(createTime);
        order.setUpdateTime(updateTime);
        order.setOrderPrise(orderPrise);
        order.setCountOfPurchasedTickets(countOfPurchasedTickets);
        order.setOrderStatus(orderStatus);
        order.setUser(selectedUser);
        order.setRoute(selectedRoute);

        return order;
    }
}
