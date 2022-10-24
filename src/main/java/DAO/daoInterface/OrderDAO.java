package DAO.daoInterface;

import entity.Order;

import java.util.List;

public interface OrderDAO {
    void addOrder(Order order);
    List<Order> findAllOrders();
    List<Order> findAllUsersOrders(String username);
    Order findOrderByID(Long id);
    void setOrderStatus(Long id);
    boolean isOrderExist(Long id);

}
