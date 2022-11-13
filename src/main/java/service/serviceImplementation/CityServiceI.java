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
        cityDAO.setCityRelevant(id);

    }

    @Override
    public void deleteCityByID(Long id) {
        cityDAO.deleteCityByID(id);

    }
}
