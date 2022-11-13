package command.customeCommand.adminCommand;

import command.Command;
import entity.City;
import entity.User;
import service.ServiceFactory;
import service.serviceInterfaces.CityService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllCitiesCommand implements Command {
    private int page = 1;
    private static final int RECORDS_PER_PAGE = 10;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user==null || !user.getUserRole().equals("ADMIN")){
            request.getRequestDispatcher("login.jsp").forward(request,response);
            return;
        }
        CityService cityService = ServiceFactory.getInstance().getCityService();

        if(request.getParameter("page") != null){
            page = Integer.parseInt(request.getParameter("page"));
        }
        List<City> cityList = cityService.findAllCites((page-1)*RECORDS_PER_PAGE, RECORDS_PER_PAGE);

        int noOfRecords = cityService.allCitesCount();
        int countOfPages = (int) Math.ceil(noOfRecords * 1.0 / RECORDS_PER_PAGE);

        if(request.getParameter("cityID")!=null){
            long cityID = Long.parseLong(request.getParameter("cityID"));
            switch (request.getParameter("requestType")){
                case "setStatus":
                    cityService.setCityRelevant(cityID);
                    response.sendRedirect("controller?action=allCitiesForAdmin");
                    break;
                case "delete":
                    cityService.deleteCityByID(cityID);
                    response.sendRedirect("controller?action=allCitiesForAdmin");
                    break;
            }
            return;
        }

        request.setAttribute("countOfPages", countOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("citiesListForAdmin",cityList);

        request.getRequestDispatcher("allCitiesForAdmin.jsp").forward(request,response);
    }
}
