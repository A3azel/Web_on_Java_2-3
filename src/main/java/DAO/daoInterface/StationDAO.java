package DAO.daoInterface;

import entity.Station;

import java.util.List;

public interface StationDAO {
    void addStation(Station station);
    void updateStation(Station station);
    List<Station> findAllStations();
    Station findStationByStationName(String stationName);
    Station findStationByID(Long id);
    void setStationRelevant(String stationName);

}
