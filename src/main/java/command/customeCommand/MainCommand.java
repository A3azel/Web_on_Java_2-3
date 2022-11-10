package command.customeCommand;

import DAO.DAOFactory;
import DAO.daoRealize.CityDAOImpl;
import DAO.daoRealize.RouteDAOImpl;
import command.Command;
import entity.Route;
import service.ServiceFactory;
import service.serviceInterfaces.CityService;
import service.serviceInterfaces.RouteService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String departureCity = request.getParameter("cityOfDeparture");
        String arrivalCity = request.getParameter("cityOfArrival");
        String stringData = request.getParameter("selectedDates");
        String stringTime = request.getParameter("selectedTime");
        LocalDate departureData = LocalDate.parse(stringData);
        LocalTime departureTime = LocalTime.parse(stringTime);

        Map<String,String> errorAttribute = new HashMap<>();

        //RouteDAOImpl routeDAO = DAOFactory.getInstance().getRouteDAO();
        //CityDAOImpl cityDAO = DAOFactory.getInstance().getCityDAO();

        CityService cityService = ServiceFactory.getInstance().getCityService();
        RouteService routeService = ServiceFactory.getInstance().getRouteService();

        LocalDateTime selectedLocalDateTime = LocalDateTime.of(departureData,departureTime);

        if(departureCity.equals("")){
            errorAttribute.put("departureCityError","this field cant be empty");
        }
        if(arrivalCity.equals("")){
            errorAttribute.put("arrivalCityError","this field cant be empty");
        }
        if(!errorAttribute.isEmpty()){
            passToErrorPage(request,response,errorAttribute);
            return;
        }

        if(selectedLocalDateTime.isBefore(LocalDateTime.now())){
            errorAttribute.put("routeErrors","the date has already passed");
        }
        if(cityService.findCityByCityName(departureCity) == null){
            errorAttribute.put("departureCityError","City with the specified name not found");
        }
        if(cityService.findCityByCityName(arrivalCity) == null){
            errorAttribute.put("arrivalCityError","City with the specified name not found");
        }

        if(!errorAttribute.isEmpty()){
            passToErrorPage(request,response,errorAttribute);
            return;
        }
        List<Route> routeList = routeService.findAllBetweenTwoCites(departureCity,arrivalCity,departureData,departureTime);
        if(routeList.size()==0){
            errorAttribute.put("routeErrors","Route not found");
        }
        if(!errorAttribute.isEmpty()){
            passToErrorPage(request,response,errorAttribute);
            return;
        }

        request.setAttribute("routeList",routeList);
        request.getRequestDispatcher("selectedRouts.jsp").forward(request,response);


    }

    public void passToErrorPage(HttpServletRequest request, HttpServletResponse response, Map<String,String> errorMap){
        for (HashMap.Entry<String, String> ent : errorMap.entrySet()){
            request.setAttribute(ent.getKey(), ent.getValue());
        }
        try {
            request.getRequestDispatcher("index.jsp").forward(request,response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }
}
