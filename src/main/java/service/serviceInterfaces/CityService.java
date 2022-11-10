package service.serviceInterfaces;

import entity.City;

import java.util.List;

public interface CityService {
    void addCity(City city);
    void updateCity(City city);
    List<City> findAllCites();
    City findCityByCityName(String cityName);
    City findCityByID(Long id);
    void setCityRelevant(String cityName);
    void deleteCityByCityName(String cityName);
}
