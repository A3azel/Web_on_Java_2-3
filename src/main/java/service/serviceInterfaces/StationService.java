package service.serviceInterfaces;

import entity.Station;

import java.util.List;
import java.util.Map;

public interface StationService {
    Map<String,String> addStation(String stationName, Long cityID);
    void updateStation(Station station);
    List<Station> findAllStations(String cityName, int offset, int noOfRecords);
    int allStationsCount(String cityName);
    boolean findStationByCityIDAndStationName(Long cityID, String stationName);
    Station findStationByStationName(String stationName);
    Station findStationByID(Long id);
    void setStationRelevant(Long id);
    void deleteStation(Long id);
}
