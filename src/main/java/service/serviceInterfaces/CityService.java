package service.serviceInterfaces;

import entity.City;

import java.util.List;

public interface CityService {
    void addCity(City city);
    void updateCity(City city);
    List<City> findAllCites(int offset, int noOfRecords);
    int allCitesCount();
    City findCityByCityName(String cityName);
    City findCityByID(Long id);
    void setCityRelevant(Long id);
    void deleteCityByID(Long id);
}
