package command.customeCommand;

import command.Command;
import entity.User;
import service.ServiceFactory;
import service.serviceInterfaces.OrderService;
import service.serviceInterfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class MakeOrderCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        UserService userService = ServiceFactory.getInstance().getUserService();
        int countOfTickets = Integer.parseInt(request.getParameter("countOfTickets"));
        BigDecimal ticketPrise = BigDecimal.valueOf(Double.parseDouble(request.getParameter("ticketPrise")));
        Long routeID = Long.parseLong(request.getParameter("routeID"));
        User user = (User) request.getSession().getAttribute("user");
        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        Map<String,String> errorMap =orderService.addOrder(user,routeID,countOfTickets,ticketPrise);
        if(!errorMap.isEmpty()){
            passToErrorPage(request,response,errorMap,routeID);
            return;
        }
        request.getSession().setAttribute("user",userService.findUserByUsername(user.getUsername()));
        response.sendRedirect("personalOffice.jsp");
    }

    public void passToErrorPage(HttpServletRequest request, HttpServletResponse response, Map<String,String> errorMap,Long id){
        for (HashMap.Entry<String, String> ent : errorMap.entrySet()){
            request.setAttribute(ent.getKey(), ent.getValue());
        }
        try {
            request.getRequestDispatcher("controller?action=order&id="+id).forward(request,response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }

}
