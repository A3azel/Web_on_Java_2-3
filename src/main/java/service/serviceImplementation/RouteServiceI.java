package service.serviceImplementation;

import DAO.DAOFactory;
import DAO.daoRealize.RouteDAOImpl;
import entity.Route;
import service.serviceInterfaces.RouteService;
import validation.RouteValidator;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

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
    public Map<String,String> addRoute(String trainNumber, String startCityName, String startStationName, String arrivalCityName
            , String arrivalStationName, String departureTime, String arrivalTime
            , String numberOfFreeSeats, String priseOfTicket) {

        Map<String,String> errorMap = RouteValidator.allRoutValidation(trainNumber, startCityName, startStationName, arrivalCityName
                , arrivalStationName, departureTime, arrivalTime
                , numberOfFreeSeats, priseOfTicket);

        if(!errorMap.isEmpty()){
            return errorMap;
        }

        Route route = createRoute(trainNumber, startCityName, startStationName, arrivalCityName
                , arrivalStationName, departureTime, arrivalTime
                , numberOfFreeSeats, priseOfTicket);
        routeDAO.addRoute(route);
        return errorMap;
    }

    @Override
    public Map<String, String> updateRoute(String id, String trainNumber, String startCityName, String startStationName
            , String arrivalCityName, String arrivalStationName, String departureTime, String arrivalTime
            , String numberOfFreeSeats, String priseOfTicket) {

        Map<String,String> errorMap = RouteValidator.allRoutValidation(trainNumber, startCityName, startStationName, arrivalCityName
                , arrivalStationName, departureTime, arrivalTime
                , numberOfFreeSeats, priseOfTicket);

        if(!errorMap.isEmpty()){
            return errorMap;
        }
        Route route = createRoute(trainNumber, startCityName, startStationName, arrivalCityName
                , arrivalStationName, departureTime, arrivalTime
                , numberOfFreeSeats, priseOfTicket);
        route.setID(Long.parseLong(id));
        if(route.equals(findRouteByID(Long.parseLong(id)))){
            errorMap.put("sameRoutesError","Не було внесено жодних змін");
            return errorMap;
        }
        routeDAO.updateRoute(route);
        return errorMap;
    }

    @Override
    public List<Route> findAllRouts(int offset, int noOfRecords) {
        return routeDAO.findAllRouts(offset, noOfRecords);
    }

    @Override
    public int allRoutsCount() {
        return routeDAO.allRoutsCount();
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
    public void deleteRoute(Long id) {
        routeDAO.deleteRouteByID(id);
    }

    @Override
    public List<Route> findAllBetweenTwoStations(String startStation, String arrivalStation, LocalDate data, LocalTime localTime) {
        return routeDAO.findAllBetweenTwoStations(startStation, arrivalStation, data, localTime);
    }

    @Override
    public List<Route> findAllBetweenTwoCites(String startCity, String arrivalCity, LocalDate data, LocalTime localTime, int offset, int noOfRecords) {
        return routeDAO.findAllBetweenTwoCites(startCity, arrivalCity, data, localTime, offset, noOfRecords);
    }

    @Override
    public int allBetweenTwoCitesCount(String startCity, String arrivalCity, LocalDate data, LocalTime localTime) {
        return routeDAO.allBetweenTwoCitesCount(startCity, arrivalCity, data, localTime);
    }

    public Route createRoute(String trainNumber, String startCityName, String startStationName, String arrivalCityName
            , String arrivalStationName, String departureTime, String arrivalTime
            , String numberOfFreeSeats, String priseOfTicket){

        LocalDateTime departureLocalDate = LocalDateTime.parse(departureTime);
        LocalDateTime arrivalLocalDate = LocalDateTime.parse(arrivalTime);
        int freeSeats = Integer.parseInt(numberOfFreeSeats);
        BigDecimal ticketPrise = BigDecimal.valueOf(Double.parseDouble(priseOfTicket));
        Duration duration = Duration.between(departureLocalDate,arrivalLocalDate);
        LocalTime travelTime = LocalTime.of(duration.toHoursPart(),duration.toMinutesPart());

        Route route = new Route();
        route.setTrain(trainNumber);
        route.setDepartureCity(startCityName);
        route.setArrivalCity(arrivalCityName);
        route.setStartStation(startStationName);
        route.setArrivalStation(arrivalStationName);
        route.setDepartureTime(departureLocalDate);
        route.setArrivalTime(arrivalLocalDate);
        route.setTravelTime(travelTime);
        route.setNumberOfFreeSeats(freeSeats);
        route.setPriseOfTicket(ticketPrise);
        route.setRelevant(true);
        return route;
    }
}
