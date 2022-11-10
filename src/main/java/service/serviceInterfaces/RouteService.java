package service.serviceInterfaces;

import entity.Route;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface RouteService {
    void addRoute(Route route);
    List<Route> findAllRouts();
    Route findRouteByID(Long id);
    void setRouteRelevant(Long id);
    List<Route> findAllBetweenTwoStations(String startStation, String arrivalStation, LocalDate data, LocalTime localTime);
    List<Route> findAllBetweenTwoCites(String startCity, String arrivalCity, LocalDate data, LocalTime localTime);
}
