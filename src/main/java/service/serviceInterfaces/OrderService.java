package service.serviceInterfaces;

import entity.Order;
import entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface OrderService {
    Map<String,String> addOrder(User user, Long routeId, int ticketsCount, BigDecimal ticketPrise);
    List<Order> findAllOrders();
    List<Order> findAllUsersOrders(String username);
    Order findOrderByID(Long id);
    void setOrderStatus(Long id);
    boolean isOrderExist(Long id);
}
