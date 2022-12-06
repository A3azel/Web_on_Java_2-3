package entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ServiceFactory;
import service.serviceInterfaces.RouteService;

public class RouteDAOTests {

    private static final String TRAIN_NUMBER = "O-100";
    private static final String DEPARTURE_CITY = "Київ";
    private static final String DEPARTURE_STATION = "Київ схід";
    private static final String ARRIVAL_CITY = "Львів";
    private static final String ARRIVAL_STATION = "Львів захід";
    private static final String DEPARTURE_DATE = "2022-11-27T19:15:06";
    private static final String ARRIVAL_DATE = "2022-11-28T02:30:09";
    private static final String FREE_SEATS = "120";
    private static final String PRICE = "1000";

    private RouteService routeService;

    @BeforeEach
    public void init(){
        routeService = ServiceFactory.getInstance().getRouteService();
    }

    @Test
    public void addRoute(){
        int numberRoutsBeforeAdding = routeService.allRoutsCount();
        routeService.addRoute(TRAIN_NUMBER,DEPARTURE_CITY,DEPARTURE_STATION,ARRIVAL_CITY
                ,ARRIVAL_STATION,DEPARTURE_DATE,ARRIVAL_DATE,FREE_SEATS,PRICE);
        int numberRoutsAfterAdding = routeService.allRoutsCount();
        Assertions.assertEquals(numberRoutsBeforeAdding+1,numberRoutsAfterAdding);
    }

    @Test
    public void blockRoute(){
        routeService.setRouteRelevant(8L);
        Assertions.assertFalse(routeService.findRouteByID(8L).isRelevant());
    }

    @Test
    public void deleteRoute(){
        int numberRoutsBeforeAdding = routeService.allRoutsCount();
        routeService.deleteRoute(8L);
        int numberRoutsAfterAdding = routeService.allRoutsCount();
        Assertions.assertEquals(numberRoutsBeforeAdding-1,numberRoutsAfterAdding);
    }
}
