package service.serviceImplementation;

import DAO.DAOFactory;
import DAO.daoRealize.OrderDAOImpl;
import entity.Order;
import entity.Route;
import entity.User;
import service.ServiceFactory;
import service.serviceInterfaces.OrderService;
import service.serviceInterfaces.RouteService;
import service.serviceInterfaces.UserService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String,String> addOrder(User user, Long routeId, int ticketsCount, BigDecimal ticketPrise) {
        BigDecimal orderPrise = ticketPrise.multiply(BigDecimal.valueOf(ticketsCount));
        Map<String,String> errorMap = new HashMap<>();
        if(orderPrise.compareTo(user.getUserCountOfMoney())>0){
            errorMap.put("insufficientFunds","Недостатньо коштів на рахунку");
            return errorMap;
        }
        RouteService routeService = ServiceFactory.getInstance().getRouteService();
        UserService userService = ServiceFactory.getInstance().getUserService();
        Route selectedRoute = routeService.findRouteByID(routeId);
        if(selectedRoute == null){
            System.out.println("rout not found");
        }
        userService.spendMoney(orderPrise, user.getUsername());

        Order order = new Order();
        order.setRoute(selectedRoute);
        order.setUser(user);
        order.setOrderStatus(true);
        order.setOrderPrise(orderPrise);
        orderDAO.addOrder(order);

        return errorMap;
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
