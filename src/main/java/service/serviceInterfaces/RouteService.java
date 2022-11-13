package service.serviceInterfaces;

import entity.Route;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public interface RouteService {
    Map<String,String> addRoute(String trainNumber,String startCityName,String startStationName,String arrivalCityName
            ,String arrivalStationName,String departureTime,String arrivalTime
            ,String numberOfFreeSeats,String priseOfTicket);
    Map<String,String> updateRoute(String id,String trainNumber,String startCityName,String startStationName,String arrivalCityName
            ,String arrivalStationName,String departureTime,String arrivalTime
            ,String numberOfFreeSeats,String priseOfTicket);
    List<Route> findAllRouts(int offset, int noOfRecords);
    int allRoutsCount();
    Route findRouteByID(Long id);
    void setRouteRelevant(Long id);
    void deleteRoute(Long id);
    List<Route> findAllBetweenTwoStations(String startStation, String arrivalStation, LocalDate data, LocalTime localTime);
    List<Route> findAllBetweenTwoCites(String startCity, String arrivalCity, LocalDate data, LocalTime localTime, int offset, int noOfRecords);
    int allBetweenTwoCitesCount(String startCity, String arrivalCity, LocalDate data, LocalTime localTime);
}
