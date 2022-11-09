package command.customeCommand;

import DAO.DAOFactory;
import DAO.daoRealize.RouteDAOImpl;
import command.Command;
import entity.Route;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long routeID = Long.valueOf(request.getParameter("id"));

        RouteDAOImpl routeDAO = DAOFactory.getInstance().getRouteDAO();

        Route selectedRoute = routeDAO.findRouteByID(routeID);

        request.setAttribute("selectedRoute",selectedRoute);

        request.getRequestDispatcher("issuingTicket.jsp").forward(request,response);
    }
}
