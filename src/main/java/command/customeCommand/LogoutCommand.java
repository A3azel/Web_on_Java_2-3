package command.customeCommand;

import command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(request.getSession().getAttribute("user")==null){
            session.removeAttribute("user");
            session.invalidate();
            response.sendRedirect("index.jsp");
        }
    }
}
