package command.customeCommand;

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
    private int page = 1;
    private static final int RECORDS_PER_PAGE = 10;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String departureCity = request.getParameter("cityOfDeparture");
        String arrivalCity = request.getParameter("cityOfArrival");
        String stringData = request.getParameter("selectedDates");
        String stringTime = request.getParameter("selectedTime");
        LocalDate departureData = LocalDate.parse(stringData);
        LocalTime departureTime = LocalTime.parse(stringTime);
//
        Map<String,String> errorAttribute = new HashMap<>();

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
//
        if(request.getParameter("page") != null){
            page = Integer.parseInt(request.getParameter("page"));
        }
        List<Route> routeList = routeService.findAllBetweenTwoCites(departureCity,arrivalCity,departureData,departureTime,(page-1)*RECORDS_PER_PAGE, RECORDS_PER_PAGE);
        if(routeList.size()==0){
            errorAttribute.put("routeErrors","Route not found");
        }
        if(!errorAttribute.isEmpty()){
            passToErrorPage(request,response,errorAttribute);
            return;
        }

        int noOfRecords = routeService.allBetweenTwoCitesCount(departureCity,arrivalCity,departureData,departureTime);
        int countOfPages = (int) Math.ceil(noOfRecords * 1.0 / RECORDS_PER_PAGE);

        request.setAttribute("departureCity",departureCity);
        request.setAttribute("arrivalCity",arrivalCity);
        request.setAttribute("departureData",stringData);
        request.setAttribute("departureTime",stringTime);

        request.setAttribute("countOfPages", countOfPages);
        request.setAttribute("currentPage", page);
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
