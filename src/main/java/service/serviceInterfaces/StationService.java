package service.serviceInterfaces;

import entity.Station;

import java.util.List;
import java.util.Map;

public interface StationService {
    Map<String,String> addStation(String stationName, Long cityID);
    Map<String,String> updateStation(String stationName, Long stationID);
    List<Station> findAllStations(String cityName, int offset, int noOfRecords);
    List<Station> findAllStationsForAdmin(Long cityID);
    int allStationsCount(String cityName);
    boolean findStationByCityIDAndStationName(Long cityID, String stationName);
    Station findStationByStationName(String stationName);
    Station findStationByID(Long id);
    void setStationRelevant(Long id);
    void setStationRelevantWithBlockedCity(Long stationID, Long cityID);
    void deleteStation(Long id);
}
