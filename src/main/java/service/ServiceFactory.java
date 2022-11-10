package service;

import service.serviceImplementation.*;
import service.serviceInterfaces.*;

public class ServiceFactory {

    private static ServiceFactory factory;

    public static synchronized ServiceFactory getInstance(){
        if(factory == null){
            return new ServiceFactory();
        }
        return factory;
    }

    public CityService getCityService(){
        return CityServiceI.getInstance();
    }

    public OrderService getOrderService(){
        return OrderServiceI.getInstance();
    }

    public RouteService getRouteService(){
        return RouteServiceI.getInstance();
    }

    public StationService getStationService(){
        return StationServiceI.getInstance();
    }

    public TrainService getTrainService(){
        return TrainServiceI.getInstance();
    }

    public UserService getUserService(){
        return UserServiceI.getInstance();
    }

    public RoleService getRoleService(){
        return RoleServiceI.getInstance();
    }


}
