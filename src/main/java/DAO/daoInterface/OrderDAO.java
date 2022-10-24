package DAO.daoInterface;

import entity.Order;

import java.util.List;

public interface OrderDAO {
    void addOrder(Order order);
    List<Order> findAllOrders();
    List<Order> findAllUsersOrders(String username);
    Order findOrderByID(int id);
    void setOrderStatus(int id);
    boolean isOrderExist(int id);

}
