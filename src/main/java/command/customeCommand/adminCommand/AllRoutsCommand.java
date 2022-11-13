package command.customeCommand.adminCommand;

import command.Command;
import entity.Route;
import entity.User;
import service.ServiceFactory;
import service.serviceInterfaces.RouteService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllRoutsCommand implements Command {
    private int page = 1;
    private static final int RECORDS_PER_PAGE = 10;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user==null || !user.getUserRole().equals("ADMIN")){
            request.getRequestDispatcher("login.jsp").forward(request,response);
            return;
        }
        RouteService routeService = ServiceFactory.getInstance().getRouteService();

        if(request.getParameter("page") != null){
            page = Integer.parseInt(request.getParameter("page"));
        }
        List<Route> routeListForAdmin = routeService.findAllRouts((page-1)*RECORDS_PER_PAGE, RECORDS_PER_PAGE);

        int noOfRecords = routeService.allRoutsCount();
        int countOfPages = (int) Math.ceil(noOfRecords * 1.0 / RECORDS_PER_PAGE);

        if(request.getParameter("routeID")!=null){
            long routeID = Long.parseLong(request.getParameter("routeID"));
            switch (request.getParameter("requestType")){
                case "setStatus":
                    routeService.setRouteRelevant(routeID);
                    break;
                case "delete":
                    routeService.deleteRoute(routeID);
                    break;
            }
            response.sendRedirect("controller?action=allRoutsForAdmin");
            return;
        }

        request.setAttribute("countOfPages", countOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("routeListForAdmin",routeListForAdmin);

        request.getRequestDispatcher("allRoutsForAdmin.jsp").forward(request,response);
    }
}
