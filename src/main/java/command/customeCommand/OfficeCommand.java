package command.customeCommand;

import command.Command;
import entity.User;
import service.ServiceFactory;
import service.serviceInterfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OfficeCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        if (username==null){
            request.getRequestDispatcher("login.jsp").forward(request,response);
            return;
        }

        if(request.getSession().getAttribute("user") == null){
            UserService userService = ServiceFactory.getInstance().getUserService();

            User user = userService.findUserByUsername(username);
            request.getSession().setAttribute("user",user);
        }

        request.getRequestDispatcher("personalOffice.jsp").forward(request,response);
    }
}
