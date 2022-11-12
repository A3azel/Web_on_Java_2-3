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
        List<Station> userList = stationService.findAllStations((page-1)*RECORDS_PER_PAGE, RECORDS_PER_PAGE);

        int noOfRecords = stationService.allStationsCount();
        int countOfPages = (int) Math.ceil(noOfRecords * 1.0 / RECORDS_PER_PAGE);
    }
}
