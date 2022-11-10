package service.serviceImplementation;

import DAO.DAOFactory;
import DAO.daoRealize.OrderDAOImpl;
import entity.Order;
import service.serviceInterfaces.OrderService;

import java.util.List;

public class OrderServiceI implements OrderService {

    private static OrderServiceI instance;
    private final OrderDAOImpl orderDAO;

    public OrderServiceI() {
        orderDAO = DAOFactory.getInstance().getOrderDAO();
    }

    public static synchronized OrderServiceI getInstance(){
        if(instance==null){
            return new OrderServiceI();
        }
        return instance;
    }

    @Override
    public void addOrder(Order order) {
        orderDAO.addOrder(order);
    }

    @Override
    public List<Order> findAllOrders() {
        return orderDAO.findAllOrders();
    }

    @Override
    public List<Order> findAllUsersOrders(String username) {
        return orderDAO.findAllUsersOrders(username);
    }

    @Override
    public Order findOrderByID(Long id) {
        return orderDAO.findOrderByID(id);
    }

    @Override
    public void setOrderStatus(Long id) {
        orderDAO.setOrderStatus(id);
    }

    @Override
    public boolean isOrderExist(Long id) {
        return orderDAO.isOrderExist(id);
    }
}
