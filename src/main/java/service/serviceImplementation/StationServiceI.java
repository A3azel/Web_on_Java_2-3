package service.serviceImplementation;

import DAO.DAOFactory;
import DAO.daoRealize.StationDAOImpl;
import entity.Station;
import service.serviceInterfaces.StationService;

import java.util.List;

public class StationServiceI implements StationService {

    private static StationServiceI instance;
    private final StationDAOImpl stationDAO;


    public StationServiceI() {
        stationDAO = DAOFactory.getInstance().getStationDAO();
    }

    public static synchronized StationServiceI getInstance(){
        if(instance==null){
            return new StationServiceI();
        }
        return instance;
    }

    @Override
    public void addStation(Station station) {
        stationDAO.addStation(station);
    }

    @Override
    public void updateStation(Station station) {
        stationDAO.updateStation(station);
    }

    @Override
    public List<Station> findAllStations() {
        return stationDAO.findAllStations();
    }

    @Override
    public Station findStationByStationName(String stationName) {
        return stationDAO.findStationByStationName(stationName);
    }

    @Override
    public Station findStationByID(Long id) {
        return stationDAO.findStationByID(id);
    }

    @Override
    public void setStationRelevant(String stationName) {
        stationDAO.setStationRelevant(stationName);
    }
}
