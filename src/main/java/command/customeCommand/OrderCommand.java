package command.customeCommand;

import DAO.DAOFactory;
import DAO.daoRealize.RouteDAOImpl;
import command.Command;
import entity.Route;
import service.ServiceFactory;
import service.serviceInterfaces.RouteService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        Long routeID = Long.valueOf(request.getParameter("id"));

        RouteService routeService = ServiceFactory.getInstance().getRouteService();

        Route selectedRoute = routeService.findRouteByID(routeID);

        request.setAttribute("selectedRoute",selectedRoute);
        request.setAttribute("countOfFreeTickets",selectedRoute.getNumberOfFreeSeats());
        request.setAttribute("ticketPrise",selectedRoute.getPriseOfTicket());

        request.getRequestDispatcher("issuingTicket.jsp").forward(request,response);
    }
}
