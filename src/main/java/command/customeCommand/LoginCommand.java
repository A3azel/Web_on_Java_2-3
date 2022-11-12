package command.customeCommand;

import command.Command;
import service.ServiceFactory;
import service.serviceInterfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("username") != null) {
            response.sendRedirect("controller?action=user");
            return;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserService userService = ServiceFactory.getInstance().getUserService();

        boolean isUserExist = userService.isUserExist(username,password);

        Map<String,String> errorAttribute = new HashMap<>();

        if (!isUserExist){
            errorAttribute.put("loginError","incorrect password or login");
            passToErrorPage(request,response,errorAttribute);
            return;
        }
        System.out.println(username);
        boolean isAccountVerified = userService.findUserByUsername(username).isAccountVerified();

        if (!isAccountVerified){
            errorAttribute.put("loginError","account wab blocked");
            passToErrorPage(request,response,errorAttribute);
            return;
        }

        request.getSession().setAttribute("username", username);

        response.sendRedirect("controller?action=user");
    }

    public void passToErrorPage(HttpServletRequest request, HttpServletResponse response, Map<String,String> errorMap){
        for (HashMap.Entry<String, String> ent : errorMap.entrySet()){
            request.setAttribute(ent.getKey(), ent.getValue());
        }
        try {
            request.getRequestDispatcher("login.jsp").forward(request,response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }

    }
}
