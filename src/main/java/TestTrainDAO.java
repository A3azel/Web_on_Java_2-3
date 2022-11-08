import DAO.DAOFactory;
import DAO.daoRealize.TrainDAOImpl;
import entity.Train;

import java.time.LocalDateTime;
import java.util.List;

public class TestTrainDAO {
    public static void testTrainDAO(){
        TrainDAOImpl trainDAO = DAOFactory.getInstance().getTrainDAO();
        List<Train> trainList;

        // find all trains
        trainList = trainDAO.findAllTrains();
        HelpedMethods.viewAllTrains(trainList);

        // add new train
        System.out.println("~~~~~~~~~Added new Test train~~~~~~~~~");
        Train train = new Train();
        train.setTrainNumber("Test");
        train.setNumberOfSeats(200);
        train.setUpdateTime(LocalDateTime.now());
        train.setCreateTime(LocalDateTime.now());
        train.setRelevant(true);
        trainDAO.addTrain(train);
        trainList = trainDAO.findAllTrains();
        HelpedMethods.viewAllTrains(trainList);

        // remove new train
        System.out.println("~~~~~~~~~Removed new Test train~~~~~~~~~");
        trainDAO.deleteTrainByTrainNumber("Test");
        trainList = trainDAO.findAllTrains();
        HelpedMethods.viewAllTrains(trainList);
    }
}
