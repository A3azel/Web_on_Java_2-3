package command.customeCommand.adminCommand;

import command.Command;
import entity.User;
import service.ServiceFactory;
import service.serviceInterfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllUsersCommand implements Command {
    private int page = 1;
    private static final int RECORDS_PER_PAGE = 10;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user==null || !user.getUserRole().equals("ADMIN")){
            request.getRequestDispatcher("login.jsp").forward(request,response);
            return;
        }
        UserService userService = ServiceFactory.getInstance().getUserService();

        if(request.getParameter("page") != null){
            page = Integer.parseInt(request.getParameter("page"));
        }
        List<User> userList = userService.findAllUsers((page-1)*RECORDS_PER_PAGE, RECORDS_PER_PAGE);

        int noOfRecords = userService.allUsersCount();
        int countOfPages = (int) Math.ceil(noOfRecords * 1.0 / RECORDS_PER_PAGE);

        request.setAttribute("countOfPages", countOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("userList",userList);

        if(request.getParameter("userID")!=null){
            long userID = Long.parseLong(request.getParameter("userID"));
            userService.setUserAccountVerified(userID);
            response.sendRedirect("controller?action=allUsers");
            return;
        }

        request.getRequestDispatcher("allUsersForAdmin.jsp").forward(request,response);
    }
}
