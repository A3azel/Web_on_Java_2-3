package DAO.DAORealize;

import DAO.AbstractDAO;
import DAO.DAOInterface.OrderDAOI;

public class OrderDAO extends AbstractDAO implements OrderDAOI {
    // table filed
    private static final String ID = "id";
    private static final String CREATE_TIME = "create_time";
    private static final String UPDATE_TIME = "update_time";
    private static final String ORDER_PRISE = "order_prise";
    private static final String COUNT_OF_PURCHASED_TICKETS = "count_of_purchased_tickets";
    private static final String ORDER_STATUS = "order_status";
    private static final String USER_ID = "user_id";
    private static final String TRAIN_ID = "train_id";
    private static final String TICKET_TYPE_ID = "ticket_type_id";

    // SQL requests
    private static final String ADD_ORDER = "INSERT INTO user_info(create_time, update_time, username, first_name, last_name, user_password" +
            ", user_count_of_money, account_verified, user_email, role_id) VALUE(?,?,?,?,?,?,?,?,?,?)";
    private static final String SET_ORDER_STATUS = "UPDATE user_info SET account_verified = ? WHERE id = ?";
    private static final String FIND_ORDER_BY_ID = "SELECT * FROM user_info WHERE username = ?";
    private static final String FIND_ALL_USERS_ORDERS = "SELECT * FROM user_info WHERE id = ?";
    private static final String FIND_ALL_ORDERS = "SELECT * FROM user_info";
    private static final String BLOCK_ORDER = "UPDATE user_info SET user_count_of_money = user_count_of_money + ? WHERE id = ?";
    private static final String IS_ORDER_EXIST = "SELECT * FROM user_info WHERE username = ? AND user_password = ?";

    private static OrderDAO orderDAO;

    private OrderDAO(){

    }

    public static synchronized OrderDAO getInstance(){
        if(orderDAO == null){
            return new OrderDAO();
        }
        return orderDAO;
    }
}
