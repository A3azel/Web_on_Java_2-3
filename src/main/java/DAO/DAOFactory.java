package DAO;

import DAO.daoRealize.*;


public class DAOFactory{

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

    public CityDAOImpl getCityDAO(){
        return CityDAOImpl.getInstance();
    }

    public OrderDAOImpl getOrderDAO(){
        return OrderDAOImpl.getInstance();
    }

    public RouteDAOImpl getRouteDAO(){
        return RouteDAOImpl.getInstance();
    }

    public StationDAOImpl getStationDAO(){
        return StationDAOImpl.getInstance();
    }

    public TrainDAOImpl getTrainDAO(){
        return TrainDAOImpl.getInstance();
    }

    public UserDAOImpl getUserDAO(){
        return UserDAOImpl.getInstance();
    }

    public RoleDAOImpl getRoleDAO(){
        return RoleDAOImpl.getInstance();
    }

}
