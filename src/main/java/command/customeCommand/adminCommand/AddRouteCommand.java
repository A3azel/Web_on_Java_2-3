package command.customeCommand.adminCommand;

import command.Command;
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
        String trainNumber = request.getParameter("trainNumber");
        String startCityName = request.getParameter("startCityName");
        String startStationName = request.getParameter("startStationName");
        String arrivalCityName = request.getParameter("arrivalCityName");
        String arrivalStationName = request.getParameter("arrivalStationName");
        String departureTime = request.getParameter("departureTime");
        String arrivalTime = request.getParameter("arrivalTime");
        String travelTime = request.getParameter("travelTime");
        String numberOfFreeSeats = request.getParameter("numberOfFreeSeats");
        String priseOfTicket = request.getParameter("priseOfTicket");

        RouteService routeService = ServiceFactory.getInstance().getRouteService();
        Map<String,String> errorMap = routeService.addRoute(trainNumber,startCityName,startStationName,arrivalCityName,arrivalStationName,departureTime
                ,arrivalTime,travelTime,numberOfFreeSeats,priseOfTicket);

        if(!errorMap.isEmpty()){
            request.setAttribute("trainNumber",trainNumber);
            request.setAttribute("startCityName",startCityName);
            request.setAttribute("startStationName",startStationName);
            request.setAttribute("arrivalCityName",arrivalCityName);
            request.setAttribute("arrivalStationName",arrivalStationName);
            passToErrorPage(request,response,errorMap);
            return;
        }
        response.sendRedirect("command?action=allRoutsForAdmin&page=1");
    }

    public void passToErrorPage(HttpServletRequest request, HttpServletResponse response, Map<String,String> errorMap){
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
