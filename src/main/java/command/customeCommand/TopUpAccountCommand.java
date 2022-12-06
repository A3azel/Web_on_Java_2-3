package command.customeCommand;

import command.Command;
import customExceptions.userExeptions.InvalidCountOfMoney;
import entity.User;
import service.ServiceFactory;
import service.serviceInterfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TopUpAccountCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        String money = request.getParameter("countOfMoney");
        User user = (User) request.getSession().getAttribute("user");

        UserService userService = ServiceFactory.getInstance().getUserService();
        try {
            userService.topUpAccount(money, user.getUsername());
        } catch (InvalidCountOfMoney e) {
            e.printStackTrace();
            request.setAttribute("moneyError",e.getMessage());
            request.getRequestDispatcher("personalOffice.jsp").forward(request,response);
        }
        request.getSession().setAttribute("user",userService.findUserByUsername(user.getUsername()));

        response.sendRedirect("personalOffice.jsp");
    }

}
