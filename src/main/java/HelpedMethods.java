import entity.*;

import java.util.List;

public class HelpedMethods {
    public static void viewAllCites(List<City> cityList){
        System.out.println("\n~~~~~~~~~All cites~~~~~~~~~\n");
        for (City city: cityList){
            System.out.println(city);
        }
    }

    public static void viewAllOrders(List<Order> orderList){
        System.out.println("\n~~~~~~~~~All orders~~~~~~~~~\n");
        for (Order order: orderList){
            System.out.println(order);
        }
    }

    public static void viewAllRoutes(List<Route> routeList){
        System.out.println("\n~~~~~~~~~All routes~~~~~~~~~\n");
        for (Route route: routeList){
            System.out.println(route);
        }
    }

    public static void viewAllStations(List<Station> stationList){
        System.out.println("\n~~~~~~~~~All stations~~~~~~~~~\n");
        for (Station station: stationList){
            System.out.println(station);
        }
    }

    public static void viewAllTrains(List<Train> trainList){
        System.out.println("\n~~~~~~~~~All trains~~~~~~~~~\n");
        for (Train train: trainList){
            System.out.println(train);
        }
    }

    public static void viewAllUsers(List<User> userList){
        System.out.println("\n~~~~~~~~~All users~~~~~~~~~\n");
        for (User user: userList){
            System.out.println(user);
        }
    }
}
