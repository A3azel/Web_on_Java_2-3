package command.customeCommand.adminCommand;

import command.Command;
import entity.User;
import service.ServiceFactory;
import service.serviceInterfaces.RouteService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddRouteCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user==null || !user.getUserRole().equals("ADMIN")){
            request.getRequestDispatcher("login.jsp").forward(request,response);
            return;
        }
        String trainNumber = request.getParameter("trainNumber").trim();
        String startCityName = request.getParameter("startCityName").trim();
        String startStationName = request.getParameter("startStationName").trim();
        String arrivalCityName = request.getParameter("arrivalCityName").trim();
        String arrivalStationName = request.getParameter("arrivalStationName").trim();
        String departureTime = request.getParameter("departureTime").trim();
        String arrivalTime = request.getParameter("arrivalTime").trim();
        String numberOfFreeSeats = request.getParameter("numberOfFreeSeats").trim();
        String priseOfTicket = request.getParameter("priseOfTicket").trim();

        RouteService routeService = ServiceFactory.getInstance().getRouteService();
        Map<String,String> errorMap = routeService.addRoute(trainNumber,startCityName,startStationName,arrivalCityName,arrivalStationName,departureTime
                ,arrivalTime,numberOfFreeSeats,priseOfTicket);

        if(!errorMap.isEmpty()){
            request.setAttribute("trainNumber",trainNumber);
            request.setAttribute("startCityName",startCityName);
            request.setAttribute("startStationName",startStationName);
            request.setAttribute("arrivalCityName",arrivalCityName);
            request.setAttribute("arrivalStationName",arrivalStationName);
            request.setAttribute("numberOfFreeSeats",numberOfFreeSeats);
            request.setAttribute("priseOfTicket",priseOfTicket);
            passToErrorPage(request,response,errorMap);
            return;
        }
        response.sendRedirect("controller?action=allRoutsForAdmin&page=1");
    }

    private void passToErrorPage(HttpServletRequest request, HttpServletResponse response, Map<String,String> errorMap){
        for (HashMap.Entry<String, String> ent : errorMap.entrySet()){
            request.setAttribute(ent.getKey(), ent.getValue());
        }
        try {
            request.getRequestDispatcher("addRoute.jsp").forward(request,response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }
}
