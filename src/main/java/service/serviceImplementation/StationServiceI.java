package service.serviceImplementation;

import DAO.DAOFactory;
import DAO.daoRealize.StationDAOImpl;
import entity.Station;
import service.ServiceFactory;
import service.serviceInterfaces.CityService;
import service.serviceInterfaces.StationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String,String> addStation(String stationName, Long cityID) {
        Map<String,String> errorMap = new HashMap<>();
        if(stationName.equals("")){
            errorMap.put("addingStationError","Ім'я не можк бути пустим");
        }

        if(findStationByCityIDAndStationName(cityID, stationName)){
            errorMap.put("addingStationError","Станція з заданим ім'ям вже існує");
        }
        if(!errorMap.isEmpty()){
            return errorMap;
        }
        CityService service = ServiceFactory.getInstance().getCityService();
        Station station = new Station();
        station.setStationName(stationName);
        station.setCity(service.findCityByID(cityID));
        stationDAO.addStation(station);
        return errorMap;
    }

    @Override
    public void updateStation(Station station) {
        stationDAO.updateStation(station);
    }

    @Override
    public List<Station> findAllStations(String cityName, int offset, int noOfRecords) {
        return stationDAO.findAllStations(cityName, offset, noOfRecords);
    }

    @Override
    public int allStationsCount(String cityName) {
        return stationDAO.allStationsCount(cityName);
    }

    @Override
    public boolean findStationByCityIDAndStationName(Long cityID, String stationName) {
        return stationDAO.findStationByCityIDAndStationName(cityID, stationName);
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
    public void setStationRelevant(Long id) {
        stationDAO.setStationRelevant(id);
    }

    @Override
    public void deleteStation(Long id) {
        stationDAO.deleteStation(id);
    }
}
