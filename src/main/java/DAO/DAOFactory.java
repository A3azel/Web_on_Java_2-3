package DAO;

import DAO.DAORealize.*;

public class DAOFactory {

    private static DAOFactory factory;

    private DAOFactory() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static synchronized DAOFactory getInstance(){
        if(factory == null){
            return new DAOFactory();
        }
        return factory;
    }

    public CityDAO getCityDAO(){
        return CityDAO.getInstance();
    }

    public OrderDAO getOrderDAO(){
        return OrderDAO.getInstance();
    }

    public RouteDAO getRouteDAO(){
        return RouteDAO.getInstance();
    }

    public StationDAO getStationDAO(){
        return StationDAO.getInstance();
    }

    public TrainDAO getTrainDAO(){
        return TrainDAO.getInstance();
    }

    public UserDAO getUserDAO(){
        return UserDAO.getInstance();
    }

    public RoleDAO getRoleDAO(){
        return RoleDAO.getInstance();
    }

}
