package command.customeCommand.adminCommand;

import command.Command;
import entity.User;
import service.ServiceFactory;
import service.serviceInterfaces.StationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class AddStationCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user==null || !user.getUserRole().equals("ADMIN")){
            request.getRequestDispatcher("login.jsp").forward(request,response);
            return;
        }
        Long cityID = Long.valueOf(request.getParameter("cityID"));
        String cityName = request.getParameter("cityName");
        request.setAttribute("cityID",cityID);
        request.setAttribute("cityName",cityName);
        String stationName = request.getParameter("stationName");
        if(stationName == null){
            request.getRequestDispatcher("addStation.jsp").forward(request,response);
        }

        StationService stationService = ServiceFactory.getInstance().getStationService();
        Map<String,String> errorMap = stationService.addStation(stationName,cityID);


        if(!errorMap.isEmpty()){
            for (HashMap.Entry<String, String> ent : errorMap.entrySet()){
                request.setAttribute(ent.getKey(), ent.getValue());
            }
            try {
                request.getRequestDispatcher("addStation.jsp").forward(request,response);
            } catch (IOException | ServletException e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("controller?action=allStationsForAdmin&cityName="+ URLEncoder.encode(cityName, StandardCharsets.UTF_8));
    }

}
