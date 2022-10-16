import DAO.DAOFactory;
import DAO.DAORealize.TrainDAO;
import entity.Train;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        TrainDAO trainDAO = DAOFactory.getInstance().getTrainDAO();
        List<Train> trainList = trainDAO.findAllTrains();
        for(Train t: trainList){
            System.out.println(t);
        }
    }
}
