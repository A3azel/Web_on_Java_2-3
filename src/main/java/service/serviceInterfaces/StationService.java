package service.serviceInterfaces;

import entity.Station;

import java.util.List;

public interface StationService {
    void addStation(Station station);
    void updateStation(Station station);
    List<Station> findAllStations();
    Station findStationByStationName(String stationName);
    Station findStationByID(Long id);
    void setStationRelevant(String stationName);
}
