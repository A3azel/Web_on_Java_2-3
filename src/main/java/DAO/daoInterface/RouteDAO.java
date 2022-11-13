package DAO.daoInterface;

import entity.Route;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface RouteDAO {
    void addRoute(Route route);
    List<Route> findAllRouts(int offset, int noOfRecords);
    int allRoutsCount();
    Route findRouteByID(Long id);
    void setRouteRelevant(Long id);
    void deleteRouteByID(Long id);
    List<Route> findAllBetweenTwoStations(String startStation, String arrivalStation, LocalDate data, LocalTime localTime);
    List<Route> findAllBetweenTwoCites(String startCity, String arrivalCity, LocalDate data, LocalTime localTime, int offset, int noOfRecords);
    int allBetweenTwoCitesCount(String startCity, String arrivalCity, LocalDate data, LocalTime localTime);
}
