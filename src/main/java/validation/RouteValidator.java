package validation;

import service.ServiceFactory;
import service.serviceInterfaces.CityService;
import service.serviceInterfaces.StationService;
import service.serviceInterfaces.TrainService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class RouteValidator {
    public static Map<String,String> emptyFieldValidation(String trainNumber, String startCityName, String startStationName, String arrivalCityName
            , String arrivalStationName, String departureTime, String arrivalTime
            , String numberOfFreeSeats, String priseOfTicket){
        Map<String,String> errorMap = new HashMap<>();

        if(trainNumber.equals("")){
            errorMap.put("trainNumberError", "Данне поле не може бути пустим");
        }
        if(startCityName.equals("")){
            errorMap.put("startCityNameError", "Данне поле не може бути пустим");
        }
        if(startStationName.equals("")){
            errorMap.put("startStationNameError", "Данне поле не може бути пустим");
        }
        if(arrivalCityName.equals("")){
            errorMap.put("arrivalCityNameError", "Данне поле не може бути пустим");
        }
        if(arrivalStationName.equals("")){
            errorMap.put("arrivalStationNameError", "Данне поле не може бути пустим");
        }
        if(numberOfFreeSeats.equals("")){
            errorMap.put("numberOfFreeSeatsError", "Данне поле не може бути пустим");
        }
        if(departureTime.equals("")){
            errorMap.put("departureTimeError", "Данне поле не може бути пустим");
        }
        if(arrivalTime.equals("")){
            errorMap.put("arrivalTimeError", "Данне поле не може бути пустим");
        }
        return errorMap;
    }

    public static Map<String,String> anotherRouteValidation(String trainNumber, String startCityName, String arrivalCityName
            ,LocalDateTime departureLocalDate,LocalDateTime arrivalLocalDate){
        Map<String,String> errorMap = new HashMap<>();

        TrainService trainService = ServiceFactory.getInstance().getTrainService();
        CityService cityService = ServiceFactory.getInstance().getCityService();

        if(trainService.findTrainByTrainNumber(trainNumber)==null){
            errorMap.put("trainNumberError", "Потяг не знайнено");
        }
        if(cityService.findCityByCityName(startCityName)==null){
            errorMap.put("startCityNameError", "Місто не знайдено");
            errorMap.put("startStationNameError", "Станцію не знайдено");
        }
        if(cityService.findCityByCityName(arrivalCityName)==null){
            errorMap.put("arrivalCityNameError", "Місто не знайдено");
            errorMap.put("arrivalStationNameError", "Станцію не знайдено");
        }
        if(!departureLocalDate.isAfter(LocalDateTime.now())){
            errorMap.put("firstDate","Вибрана дата пройшла");
        }
        if(!arrivalLocalDate.isAfter(LocalDateTime.now())){
            errorMap.put("secondDate","Вибрана дата пройшла");
        }
        return errorMap;
    }

    public static Map<String,String> finalRouteValidation(String startCityName, String arrivalCityName, String startStationName, String arrivalStationName, String trainNumber, int numberOfFreeSeats){
        Map<String,String> errorMap = new HashMap<>();
        StationService stationService = ServiceFactory.getInstance().getStationService();
        CityService cityService = ServiceFactory.getInstance().getCityService();
        TrainService trainService = ServiceFactory.getInstance().getTrainService();
        Long startCityID = cityService.findCityByCityName(startCityName).getID();
        Long arrivalCityID = cityService.findCityByCityName(arrivalCityName).getID();
        if(!stationService.findStationByCityIDAndStationName(startCityID,startStationName)){
            errorMap.put("startStationNameError", "Станцію не знайдено");
        }
        if(!stationService.findStationByCityIDAndStationName(arrivalCityID,arrivalStationName)){
            errorMap.put("arrivalStationNameError", "Станцію не знайдено");
        }
        if(trainService.findTrainByTrainNumber(trainNumber).getNumberOfSeats()<numberOfFreeSeats){
            errorMap.put("freeSeatsError","Недостатньо місць, кількість доступних місць у потязі " +trainNumber+ ": " + trainService.findTrainByTrainNumber(trainNumber).getNumberOfSeats());
        }
        return errorMap;
    }

    public static Map<String,String> allRoutValidation(String trainNumber, String startCityName, String startStationName, String arrivalCityName
            , String arrivalStationName, String departureTime, String arrivalTime
            , String numberOfFreeSeats, String priseOfTicket){
        Map<String,String> errorMap = emptyFieldValidation(trainNumber, startCityName, startStationName, arrivalCityName
                , arrivalStationName, departureTime, arrivalTime
                , numberOfFreeSeats, priseOfTicket);

        if(!errorMap.isEmpty()){
            return errorMap;
        }
        LocalDateTime departureLocalDate = LocalDateTime.parse(departureTime);
        LocalDateTime arrivalLocalDate = LocalDateTime.parse(arrivalTime);
        int freeSeats = Integer.parseInt(numberOfFreeSeats);

        errorMap = anotherRouteValidation(trainNumber, startCityName, arrivalCityName
                ,departureLocalDate,arrivalLocalDate);
        if(!errorMap.isEmpty()){
            return errorMap;
        }

        errorMap = finalRouteValidation(startCityName,arrivalCityName,startStationName,arrivalStationName
                ,trainNumber, freeSeats);
        if(!errorMap.isEmpty()){
            return errorMap;
        }
        return errorMap;
    }
}
