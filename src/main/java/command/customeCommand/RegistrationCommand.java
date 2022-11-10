package command.customeCommand;

import command.Command;
import entity.User;
import entity.UserRole;
import service.ServiceFactory;
import service.serviceInterfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegistrationCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("userEmail");
        String password = request.getParameter("password");
        String submitPassword = request.getParameter("secondPassword");

        User user = new User();
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserEmail(email);
        user.setPassword(password);
        user.setAccountVerified(true);
        user.setUserRole(UserRole.USER.toString());

        UserService userService = ServiceFactory.getInstance().getUserService();
        Map<String,String> errorMap = userService.addUser(user,submitPassword);

        if(!errorMap.isEmpty()){
            for (HashMap.Entry<String, String> ent : errorMap.entrySet()){
                request.setAttribute(ent.getKey(), ent.getValue());
            }
            request.getRequestDispatcher("registration.jsp").forward(request, response);
            return;
        }

        response.sendRedirect("login.jsp");
    }
}
