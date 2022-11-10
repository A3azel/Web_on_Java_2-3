package service.serviceImplementation;

import DAO.DAOFactory;
import DAO.daoRealize.TrainDAOImpl;
import entity.Train;
import service.serviceInterfaces.TrainService;

import java.util.List;

public class TrainServiceI implements TrainService {
    private static TrainServiceI instance;
    private final TrainDAOImpl trainDAO;

    public TrainServiceI() {
        trainDAO = DAOFactory.getInstance().getTrainDAO();
    }
    public static synchronized TrainServiceI getInstance(){
        if(instance==null){
            return new TrainServiceI();
        }
        return instance;
    }

    @Override
    public void addTrain(Train train) {
        trainDAO.addTrain(train);
    }

    @Override
    public void updateTrain(Train train) {
        trainDAO.updateTrain(train);
    }

    @Override
    public List<Train> findAllTrains() {
        return trainDAO.findAllTrains();
    }

    @Override
    public Train findTrainByTrainNumber(String trainNumber) {
        return trainDAO.findTrainByTrainNumber(trainNumber);
    }

    @Override
    public Train findTrainByID(Long id) {
        return trainDAO.findTrainByID(id);
    }

    @Override
    public void setTrainRelevant(String trainNumber) {
        trainDAO.setTrainRelevant(trainNumber);
    }

    @Override
    public void deleteTrainByID(Long id) {
        trainDAO.deleteTrainByID(id);
    }
}
