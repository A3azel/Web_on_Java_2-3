package command.customeCommand.adminCommand;

import command.Command;
import entity.User;
import service.ServiceFactory;
import service.serviceInterfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserInfoCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user==null || !user.getUserRole().equals("ADMIN")){
            request.getRequestDispatcher("login.jsp").forward(request,response);
            return;
        }
        UserService userService = ServiceFactory.getInstance().getUserService();
        Long userID = Long.parseLong(request.getParameter("userID"));
        User selectedUser = userService.findUserByID(userID);

        request.setAttribute("selectedUser",selectedUser);

        request.getRequestDispatcher("userInfo.jsp").forward(request,response);
    }
}
