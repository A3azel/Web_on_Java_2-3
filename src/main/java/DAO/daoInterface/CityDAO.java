package DAO.daoInterface;

import entity.City;

import java.util.List;

public interface CityDAO {
    void addCity(City city);
    void updateCity(City city);
    List<City> findAllCites();
    City findCityByCityName(String cityName);
    City findCityByID(int id);
    void setCityRelevant(String cityName);
}
