package DAO.daoInterface;

import entity.Station;

import java.util.List;

public interface StationDAO {
    void addStation(Station station);
    void updateStation(Station station);
    List<Station> findAllStations(String cityName, int offset, int noOfRecords);
    List<Station> findAllStationsForAdmin(Long cityID);
    boolean findStationByCityIDAndStationName(Long cityID, String stationName);
    int allStationsCount(String cityName);
    Station findStationByStationName(String stationName);
    Station findStationByID(Long id);
    void setStationRelevant(Long id);
    void setStationRelevantWithBlockedCity(Long stationID, Long cityID);
    void deleteStation(Long id);

}
