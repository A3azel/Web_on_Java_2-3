package command.customeCommand;

import DAO.DAOFactory;
import DAO.UserDAOImpl;
import command.Command;

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
            response.sendRedirect("office");
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAOImpl userDAO = DAOFactory.getInstance().getUserDAO();

        boolean isUserExist = userDAO.isUserExist(username,password);

        Map<String,String> errorAttribute = new HashMap<>();

        if (!isUserExist){
            errorAttribute.put("loginError","incorrect password or login");
            passToErrorPage(request,response,errorAttribute);
            return;
        }
        boolean isAccountVerified = userDAO.findUserByUsername(username).isAccountVerified();

        if (!isAccountVerified){
            errorAttribute.put("loginError","account wab blocked");
            passToErrorPage(request,response,errorAttribute);
            return;
        }

        request.getSession().setAttribute("username", username);
        response.sendRedirect("office");
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