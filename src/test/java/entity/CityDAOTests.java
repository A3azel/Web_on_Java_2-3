package entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ServiceFactory;
import service.serviceInterfaces.CityService;

public class CityDAOTests {

    private static final String ADDED_CITY = "TestCity";
    private static final String DEFAULT_CITY = "Київ";

    private CityService service;

    @BeforeEach
    public void init(){
        service = ServiceFactory.getInstance().getCityService();
    }

    @Test
    public void findCity(){
        Assertions.assertEquals(service.findCityByCityName(DEFAULT_CITY).getCityName(),DEFAULT_CITY);
    }

    @Test
    public void addCity(){
        int numberCitiesBeforeAdding = service.allCitesCount();
        service.addCity(ADDED_CITY);
        int numberCitiesAfterAdding = service.allCitesCount();
        Assertions.assertEquals(numberCitiesBeforeAdding+1,numberCitiesAfterAdding);
    }

    @Test
    public void blockCity(){
        City city = service.findCityByCityName(ADDED_CITY);
        service.setCityRelevant(city.getID());
        Assertions.assertFalse(service.findCityByCityName(ADDED_CITY).isRelevant());
    }

    @Test
    public void deleteCity(){
        int numberCitiesBeforeAdding = service.allCitesCount();
        City city = service.findCityByCityName(ADDED_CITY);
        service.deleteCityByID(city.getID());
        int numberCitiesAfterAdding = service.allCitesCount();
        Assertions.assertEquals(numberCitiesBeforeAdding-1,numberCitiesAfterAdding);
    }

}
