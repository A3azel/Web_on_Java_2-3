package service.serviceImplementation;

import DAO.DAOFactory;
import DAO.daoRealize.RouteDAOImpl;
import entity.Route;
import service.serviceInterfaces.RouteService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class RouteServiceI implements RouteService {

    private static RouteServiceI instance;
    private final RouteDAOImpl routeDAO;

    public RouteServiceI() {
        routeDAO = DAOFactory.getInstance().getRouteDAO();
    }

    public static synchronized RouteServiceI getInstance(){
        if(instance==null){
            return new RouteServiceI();
        }
        return instance;
    }

    @Override
    public void addRoute(Route route) {
        routeDAO.addRoute(route);
    }

    @Override
    public List<Route> findAllRouts() {
        return routeDAO.findAllRouts();
    }

    @Override
    public Route findRouteByID(Long id) {
        return routeDAO.findRouteByID(id);
    }

    @Override
    public void setRouteRelevant(Long id) {
        routeDAO.setRouteRelevant(id);
    }

    @Override
    public List<Route> findAllBetweenTwoStations(String startStation, String arrivalStation, LocalDate data, LocalTime localTime) {
        return routeDAO.findAllBetweenTwoStations(startStation, arrivalStation, data, localTime);
    }

    @Override
    public List<Route> findAllBetweenTwoCites(String startCity, String arrivalCity, LocalDate data, LocalTime localTime) {
        return routeDAO.findAllBetweenTwoCites(startCity, arrivalCity, data, localTime);
    }
}
