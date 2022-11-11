package command.customeCommand;

import command.Command;
import entity.Order;
import entity.User;
import service.ServiceFactory;
import service.serviceInterfaces.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserPurchasedTicketsCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        User user = (User)request.getSession().getAttribute("user");
        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        List<Order> orderList = orderService.findAllUsersOrders(user.getUsername());
        request.setAttribute("ticketList",orderList);

        request.getRequestDispatcher("allUserPurchasedTickets.jsp").forward(request,response);
    }
}
