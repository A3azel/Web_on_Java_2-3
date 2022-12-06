package entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ServiceFactory;
import service.serviceInterfaces.StationService;

public class StationDAOTests {

    private static final String DEFAULT_STATION = "Київ центр";
    private static final String ADDED_STATION = "TestStation";
    private static final String CITY_NAME = "Київ";

    private StationService service;

    @BeforeEach
    public void init(){
        service = ServiceFactory.getInstance().getStationService();
    }

    @Test
    public void findStation(){
        Assertions.assertTrue(service.findStationByCityIDAndStationName(1L,DEFAULT_STATION));
    }

    @Test
    public void addStation(){
        int numberStationsBeforeAdding = service.allStationsCount(CITY_NAME);
        service.addStation(ADDED_STATION,1L);
        int numberStationsAfterAdding = service.allStationsCount(CITY_NAME);
        Assertions.assertEquals(numberStationsBeforeAdding+1,numberStationsAfterAdding);
    }

    @Test
    public void blockStation(){
        Station station = service.findStationByStationName(ADDED_STATION);
        service.setStationRelevant(station.getID());
        Assertions.assertFalse(service.findStationByID(station.getID()).isRelevant());
    }

    @Test
    public void deleteStation(){
        int numberStationsBeforeAdding = service.allStationsCount(CITY_NAME);
        Station station = service.findStationByStationName(ADDED_STATION);
        service.deleteStation(station.getID());
        int numberStationsAfterAdding = service.allStationsCount(CITY_NAME);
        Assertions.assertEquals(numberStationsBeforeAdding-1,numberStationsAfterAdding);
    }
}
