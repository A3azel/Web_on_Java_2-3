package command.customeCommand;

import DAO.DAOFactory;
import DAO.UserDAOImpl;
import command.Command;
import entity.User;

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
            UserDAOImpl userDAO = DAOFactory.getInstance().getUserDAO();

            User user = userDAO.findUserByUsername(username);
            request.getSession().setAttribute("user",user);
        }

        request.getRequestDispatcher("personalOffice.jsp").forward(request,response);
    }
}
