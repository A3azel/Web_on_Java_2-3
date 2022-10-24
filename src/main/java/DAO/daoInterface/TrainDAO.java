package DAO.daoInterface;

import entity.Train;

import java.util.List;

public interface TrainDAO {
    void addTrain(Train train);
    void updateTrain(Train train);
    List<Train> findAllTrains();
    Train findTrainByTrainNumber(String trainNumber);
    Train findTrainByID(Long id);
    void setTrainRelevant(String trainNumber);
    void deleteTrainByID(Long id);
}
