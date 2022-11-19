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
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class UpdateStationCommand implements Command {
    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user==null || !user.getUserRole().equals("ADMIN")){
            request.getRequestDispatcher("login.jsp").forward(request,response);
            return;
        }
        StationService stationService = ServiceFactory.getInstance().getStationService();
        String stationID = request.getParameter("stationID");
        String cityName = request.getParameter("cityName");
        if(request.getParameter("created")==null){
            Station station = stationService.findStationByID(Long.parseLong(stationID));
            request.setAttribute("stationID",stationID);
            request.setAttribute("cityName",cityName);
            request.setAttribute("created",station.getCreateTime().format(DateTimeFormatter.ofPattern(PATTERN)));
            request.setAttribute("updated",station.getUpdateTime().format(DateTimeFormatter.ofPattern(PATTERN)));
            request.setAttribute("stationName",station.getStationName());
            request.getRequestDispatcher("updateStation.jsp").forward(request,response);
            return;
        }
        String stationName = request.getParameter("stationName").trim();
        String created = request.getParameter("created");
        String updated = request.getParameter("updated");


        Map<String,String> errorMap = stationService.updateStation(stationName,Long.parseLong(stationID));
        if(!errorMap.isEmpty()){
            request.setAttribute("stationID",stationID);
            request.setAttribute("created",created);
            request.setAttribute("updated",updated);
            request.setAttribute("cityName",cityName);
            request.setAttribute("stationName",stationName);
            passToErrorPage(request,response,errorMap);
            return;
        }
        response.sendRedirect("controller?action=allStationsForAdmin&cityName="+ URLEncoder.encode(cityName, StandardCharsets.UTF_8));
    }

    private void passToErrorPage(HttpServletRequest request, HttpServletResponse response, Map<String,String> errorMap){
        for (HashMap.Entry<String, String> ent : errorMap.entrySet()){
            request.setAttribute(ent.getKey(), ent.getValue());
        }
        try {
            request.getRequestDispatcher("updateStation.jsp").forward(request,response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }
}
