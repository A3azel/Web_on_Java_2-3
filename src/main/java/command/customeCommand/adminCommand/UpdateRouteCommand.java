package command.customeCommand.adminCommand;

import command.Command;
import entity.Route;
import entity.User;
import service.ServiceFactory;
import service.serviceInterfaces.RouteService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class UpdateRouteCommand implements Command {
    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user==null || !user.getUserRole().equals("ADMIN")){
            request.getRequestDispatcher("login.jsp").forward(request,response);
            return;
        }
        String routeID = request.getParameter("routeID");
        RouteService routeService = ServiceFactory.getInstance().getRouteService();
        if(request.getParameter("created")==null){

            Route route = routeService.findRouteByID(Long.parseLong(routeID));
            request.setAttribute("routeID",routeID);
            request.setAttribute("created",route.getCreateTime().format(DateTimeFormatter.ofPattern(PATTERN)));
            request.setAttribute("updated",route.getUpdateTime().format(DateTimeFormatter.ofPattern(PATTERN)));
            request.setAttribute("routeID",routeID);
            request.setAttribute("trainNumber",route.getTrain());
            request.setAttribute("startCityName",route.getDepartureCity());
            request.setAttribute("startStationName",route.getStartStation());
            request.setAttribute("arrivalCityName",route.getArrivalCity());
            request.setAttribute("arrivalStationName",route.getArrivalStation());
            request.setAttribute("departureTime",route.getDepartureTime());
            request.setAttribute("arrivalTime",route.getArrivalTime());
            request.setAttribute("numberOfFreeSeats",route.getNumberOfFreeSeats());
            request.setAttribute("priseOfTicket",route.getPriseOfTicket());
            request.getRequestDispatcher("updateRoute.jsp").forward(request,response);
            return;
        }
        String created = request.getParameter("created");
        String updated = request.getParameter("updated");
        String trainNumber = request.getParameter("trainNumber").trim();
        String startCityName = request.getParameter("startCityName").trim();
        String startStationName = request.getParameter("startStationName").trim();
        String arrivalCityName = request.getParameter("arrivalCityName").trim();
        String arrivalStationName = request.getParameter("arrivalStationName").trim();
        String departureTime = request.getParameter("departureTime").trim();
        String arrivalTime = request.getParameter("arrivalTime").trim();
        String numberOfFreeSeats = request.getParameter("numberOfFreeSeats").trim();
        String priseOfTicket = request.getParameter("priseOfTicket").trim();


        Map<String,String> errorMap = routeService.updateRoute(routeID,trainNumber,startCityName,startStationName,arrivalCityName,arrivalStationName,departureTime
                ,arrivalTime,numberOfFreeSeats,priseOfTicket);

        if(!errorMap.isEmpty()){
            request.setAttribute("routeID",routeID);
            request.setAttribute("created",created);
            request.setAttribute("updated",updated );
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

    public void passToErrorPage(HttpServletRequest request, HttpServletResponse response, Map<String,String> errorMap){
        for (HashMap.Entry<String, String> ent : errorMap.entrySet()){
            request.setAttribute(ent.getKey(), ent.getValue());
        }
        try {
            request.getRequestDispatcher("updateRoute.jsp").forward(request,response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }
}
