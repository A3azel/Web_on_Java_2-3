package command.customeCommand.adminCommand;

import command.Command;
import entity.User;
import service.ServiceFactory;
import service.serviceInterfaces.CityService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddCityCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user==null || !user.getUserRole().equals("ADMIN")){
            request.getRequestDispatcher("login.jsp").forward(request,response);
            return;
        }
        CityService cityService = ServiceFactory.getInstance().getCityService();
        String cityName = request.getParameter("cityName").trim();
        Map<String,String> errorMap = cityService.addCity(cityName);
        if(!errorMap.isEmpty()){
            passToErrorPage(request,response,errorMap);
            return;
        }

        response.sendRedirect("controller?action=allCitiesForAdmin&page=1");
    }

    private void passToErrorPage(HttpServletRequest request, HttpServletResponse response, Map<String,String> errorMap){
        for (HashMap.Entry<String, String> ent : errorMap.entrySet()){
            request.setAttribute(ent.getKey(), ent.getValue());
        }
        try {
            request.getRequestDispatcher("addCity.jsp").forward(request,response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }
}
