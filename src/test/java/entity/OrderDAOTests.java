package entity;

import customExceptions.userExeptions.InvalidCountOfMoney;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ServiceFactory;
import service.serviceInterfaces.OrderService;
import service.serviceInterfaces.UserService;

import java.math.BigDecimal;
import java.util.List;

public class OrderDAOTests {
    private static final String USERNAME = "Test";

    OrderService orderService;
    UserService userService;

    @BeforeEach
    public void init(){
        orderService = ServiceFactory.getInstance().getOrderService();
        userService = ServiceFactory.getInstance().getUserService();
    }

    @Test
    public void findAllUserOrders() throws InvalidCountOfMoney {
        List<Order> userOrdersList = orderService.findAllUsersOrders(USERNAME);
        userService.topUpAccount("4000",USERNAME);
        Assertions.assertEquals(userOrdersList.size(),0);
    }

    @Test
    public void addOrder(){
        User user = userService.findUserByUsername(USERNAME);
        List<Order> userOrdersList = orderService.findAllUsersOrders(USERNAME);
        orderService.addOrder(user,1L,2, BigDecimal.valueOf(800));
        Assertions.assertEquals(userOrdersList.size()+1,orderService.findAllUsersOrders(USERNAME).size());
    }

    @Test
    public void findAllOrders(){
        Assertions.assertEquals(1,orderService.findAllOrders().size());
    }
}
