package command.customeCommand.adminCommand;

import command.Command;
import entity.Station;
import entity.User;
import service.ServiceFactory;
import service.serviceInterfaces.StationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class AllStationsCommand implements Command {
    private int page = 1;
    private static final int RECORDS_PER_PAGE = 10;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user==null || !user.getUserRole().equals("ADMIN")){
            request.getRequestDispatcher("login.jsp").forward(request,response);
            return;
        }
        StationService stationService = ServiceFactory.getInstance().getStationService();

        if(request.getParameter("page") != null){
            page = Integer.parseInt(request.getParameter("page"));
        }
        String cityName = request.getParameter("cityName");

        List<Station> stationList = stationService.findAllStations(cityName,(page-1)*RECORDS_PER_PAGE, RECORDS_PER_PAGE);

        int noOfRecords = stationService.allStationsCount(cityName);
        int countOfPages = (int) Math.ceil(noOfRecords * 1.0 / RECORDS_PER_PAGE);

        if(request.getParameter("stationID")!=null){
            long stationID = Long.parseLong(request.getParameter("stationID"));
            switch (request.getParameter("requestType")){
                case "setStatus":
                    stationService.setStationRelevant(stationID);
                    break;
                case "delete":
                    stationService.deleteStation(stationID);
                    break;
            }
            response.sendRedirect("controller?action=allStationsForAdmin&cityName=" + URLEncoder.encode(cityName, StandardCharsets.UTF_8));
            return;
        }

        request.setAttribute("countOfPages", countOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("stationsListForAdmin",stationList);
        request.setAttribute("cityName",cityName);

        request.getRequestDispatcher("allStationsForAdmin.jsp").forward(request,response);
    }
}
