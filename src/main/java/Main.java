import DAO.DAOFactory;
import DAO.DAORealize.TrainDAO;
import entity.Train;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TrainDAO trainDAO = DAOFactory.getInstance().getTrainDAO();
        List<Train> trainList = trainDAO.findAllTrains();
        for(Train t: trainList){
            System.out.println(t);
        }
        Train train = new Train();
        train.setTrainNumber("Test");
        train.setNumberOfSeats(200);
        train.setUpdateTime(LocalDateTime.now());
        train.setCreateTime(LocalDateTime.now());
        train.setRelevant(true);
        trainDAO.addTrain(train);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
        List<Train> trainList1 = trainDAO.findAllTrains();
        for(Train t: trainList1){
            System.out.println(t);
        }
    }
}
