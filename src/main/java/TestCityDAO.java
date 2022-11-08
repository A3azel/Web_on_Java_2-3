import DAO.DAOFactory;
import DAO.daoInterface.CityDAO;
import entity.City;

import java.time.LocalDateTime;
import java.util.List;

public class TestCityDAO {
    public static void testCityDAO(){
        CityDAO cityDAO = DAOFactory.getInstance().getCityDAO();
        List<City> cityList;

        cityList = cityDAO.findAllCites();
        HelpedMethods.viewAllCites(cityList);

        System.out.println("~~~~~~~~~Added new TestCity city~~~~~~~~~");
        City city = new City();
        city.setUpdateTime(LocalDateTime.now());
        city.setCreateTime(LocalDateTime.now());
        city.setCityName("TestCity");
        city.setRelevant(true);
        cityDAO.addCity(city);
        cityList = cityDAO.findAllCites();
        HelpedMethods.viewAllCites(cityList);

        System.out.println("~~~~~~~~~Find city where id = 3~~~~~~~~~");
        System.out.println(cityDAO.findCityByID(3L));

        System.out.println("~~~~~~~~~Removed new TestCity city~~~~~~~~~");
        cityDAO.deleteCityByCityName("TestCity");
        cityList = cityDAO.findAllCites();
        HelpedMethods.viewAllCites(cityList);
    }
}
