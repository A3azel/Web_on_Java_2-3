package command.customeCommand;

import command.Command;
import entity.Order;
import service.ServiceFactory;
import service.serviceInterfaces.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderInfoCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long ID = Long.parseLong(request.getParameter("orderID"));
        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        Order order = orderService.findOrderByID(ID);
        request.setAttribute("order",order);
        request.getRequestDispatcher("purchasedTicket.jsp").forward(request,response);
    }
}
