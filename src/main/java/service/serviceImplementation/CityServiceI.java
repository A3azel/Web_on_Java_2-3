package service.serviceImplementation;

import DAO.DAOFactory;
import DAO.daoRealize.CityDAOImpl;
import entity.City;
import entity.Station;
import service.ServiceFactory;
import service.serviceInterfaces.CityService;
import service.serviceInterfaces.StationService;
import validation.Validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityServiceI implements CityService {

    private static CityServiceI instance;
    private final CityDAOImpl cityDAO;

    public CityServiceI() {
        cityDAO = DAOFactory.getInstance().getCityDAO();
    }

    public static synchronized CityServiceI getInstance(){
        if(instance==null){
            return new CityServiceI();
        }
        return instance;
    }

    @Override
    public Map<String, String> addCity(String cityName) {
        Map<String,String> errorMap = new HashMap<>();
        if(!Validator.isCityValid(cityName)){
            errorMap.put("cityError", "Назва повинна складатися з кириличних символів");
            return errorMap;
        }
        if(findCityByCityName(cityName)!=null){
            errorMap.put("cityError", "Місто вже занесено в базу");
            return errorMap;
        }
        City city = new City();
        city.setCityName(cityName);
        cityDAO.addCity(city);
        return errorMap;
    }

    @Override
    public void updateCity(City city) {
        cityDAO.updateCity(city);
    }

    @Override
    public List<City> findAllCites(int offset, int noOfRecords) {
        return cityDAO.findAllCites(offset, noOfRecords);
    }

    @Override
    public int allCitesCount() {
        return cityDAO.allCitesCount();
    }

    @Override
    public City findCityByCityName(String cityName) {
        return cityDAO.findCityByCityName(cityName);
    }

    @Override
    public City findCityByID(Long id) {;
        return cityDAO.findCityByID(id);
    }

    @Override
    public void setCityRelevant(Long id) {
        StationService stationService = ServiceFactory.getInstance().getStationService();
        List<Station> stationList = stationService.findAllStationsForAdmin(id);
        for(Station station: stationList){
            stationService.setStationRelevantWithBlockedCity(station.getID(),id);
        }
        cityDAO.setCityRelevant(id);
    }

    @Override
    public void deleteCityByID(Long id) {
        cityDAO.deleteCityByID(id);
    }
}
