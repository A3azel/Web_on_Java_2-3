package service.serviceImplementation;

import DAO.DAOFactory;
import DAO.daoRealize.CityDAOImpl;
import entity.City;
import service.serviceInterfaces.CityService;

import java.util.List;

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
    public void addCity(City city) {
        cityDAO.addCity(city);
    }

    @Override
    public void updateCity(City city) {
        cityDAO.updateCity(city);
    }

    @Override
    public List<City> findAllCites() {
        return cityDAO.findAllCites();
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
    public void setCityRelevant(String cityName) {
        cityDAO.setCityRelevant(cityName);

    }

    @Override
    public void deleteCityByCityName(String cityName) {
        cityDAO.deleteCityByCityName(cityName);

    }
}
