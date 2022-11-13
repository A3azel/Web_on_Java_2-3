package DAO.daoRealize;

import DAO.AbstractDAO;
import DAO.daoInterface.TrainDAO;
import entity.Train;
import helpDAO.DAOHelperMethods;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TrainDAOImpl extends AbstractDAO implements TrainDAO {
    // table filed
    private static final String ID = "id";
    private static final String CREATE_TIME = "create_time";
    private static final String UPDATE_TIME = "update_time";
    private static final String TRAIN_NUMBER = "train_number";
    private static final String NUMBER_OF_SEATS = "number_of_seats";
    private static final String RELEVANT = "relevant";

    // SQL requests
    private static final String ADD_TRAIN = "INSERT INTO train_info(create_time,update_time,train_number,number_of_seats,relevant) VALUE(?,?,?,?,?)";
    private static final String UPDATE_TRAIN = "UPDATE train_info SET update_time= ?, train_number = ?,number_of_seats = ?, relevant = ? WHERE id = ?";
    private static final String SET_TRAIN_RELEVANT = "UPDATE train_info SET relevant = ? WHERE id = ?";
    private static final String FIND_TRAIN = "SELECT * FROM train_info WHERE train_number = ?";
    private static final String FIND_TRAIN_BY_ID = "SELECT * FROM train_info WHERE id = ?";
    private static final String FIND_ALL_TRAIN = "SELECT * FROM train_info";
    private static final String DELETE_TRAIN_BY_ID = "DELETE FROM train_info WHERE id = ?";
    private static final String DELETE_TRAIN_BY_TRAIN_NUMBER = "DELETE FROM train_info WHERE train_number = ?";

    private static TrainDAOImpl trainDAO;

    private TrainDAOImpl(){

    }

    public static synchronized TrainDAOImpl getInstance(){
        if(trainDAO == null){
            return new TrainDAOImpl();
        }
        return trainDAO;
    }

    @Override
    public void addTrain(Train train){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            preparedStatement = con.prepareStatement(ADD_TRAIN);
            Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
            preparedStatement.setTimestamp(1,timestamp);
            preparedStatement.setTimestamp(2,timestamp);
            preparedStatement.setString(3,train.getTrainNumber());
            preparedStatement.setInt(4,train.getNumberOfSeats());
            preparedStatement.setBoolean(5,true);
            preparedStatement.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            DAOHelperMethods.rollback(con);
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DAOHelperMethods.closeCon(preparedStatement);
            DAOHelperMethods.closeCon(con);
        }
    }

    @Override
    public void updateTrain(Train train){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            preparedStatement = con.prepareStatement(UPDATE_TRAIN);
            Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
            preparedStatement.setTimestamp(1,timestamp);
            preparedStatement.setString(2,train.getTrainNumber());
            preparedStatement.setInt(3,train.getNumberOfSeats());
            preparedStatement.setBoolean(4, train.isRelevant());
            preparedStatement.setLong(5,train.getID());
            preparedStatement.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            DAOHelperMethods.rollback(con);
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DAOHelperMethods.closeCon(preparedStatement);
            DAOHelperMethods.closeCon(con);
        }
    }

    @Override
    public List<Train> findAllTrains(){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        List<Train> trainList = new ArrayList<>();
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement = con.prepareStatement(FIND_ALL_TRAIN);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                trainList.add(helpToBuildTrain(rs));
            }
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            DAOHelperMethods.rollback(con);
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DAOHelperMethods.closeCon(preparedStatement);
            DAOHelperMethods.closeCon(con);
        }
        return trainList;
    }

    @Override
    public Train findTrainByTrainNumber(String trainNumber){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement = con.prepareStatement(FIND_TRAIN);
            preparedStatement.setString(1,trainNumber);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                con.commit();
                return helpToBuildTrain(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            DAOHelperMethods.rollback(con);
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DAOHelperMethods.closeCon(preparedStatement);
            DAOHelperMethods.closeCon(con);
        }
        return null;
    }

    @Override
    public Train findTrainByID(Long id){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement = con.prepareStatement(FIND_TRAIN_BY_ID);
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                con.commit();
                return helpToBuildTrain(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            DAOHelperMethods.rollback(con);
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DAOHelperMethods.closeCon(preparedStatement);
            DAOHelperMethods.closeCon(con);
        }
        return new Train();
    }

    @Override
    public void setTrainRelevant(String trainNumber){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            preparedStatement = con.prepareStatement(SET_TRAIN_RELEVANT);
            boolean relevant = findTrainByTrainNumber(trainNumber).isRelevant();
            preparedStatement.setBoolean(1,!relevant);
            preparedStatement.setString(2,trainNumber);
            preparedStatement.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            DAOHelperMethods.rollback(con);
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DAOHelperMethods.closeCon(preparedStatement);
            DAOHelperMethods.closeCon(con);
        }
    }

    public void deleteTrainByID(Long id){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            preparedStatement = con.prepareStatement(DELETE_TRAIN_BY_ID);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            DAOHelperMethods.rollback(con);
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DAOHelperMethods.closeCon(preparedStatement);
            DAOHelperMethods.closeCon(con);
        }
    }

    public void deleteTrainByTrainNumber(String trainNumber){
        Connection con = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            preparedStatement = con.prepareStatement(DELETE_TRAIN_BY_TRAIN_NUMBER);
            preparedStatement.setString(1,trainNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            DAOHelperMethods.rollback(con);
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DAOHelperMethods.closeCon(preparedStatement);
            DAOHelperMethods.closeCon(con);
        }
    }

    public Train helpToBuildTrain(ResultSet rs) throws SQLException {
        Train train = new Train();
        Long id = rs.getLong(ID);
        LocalDateTime createTime = rs.getTimestamp(CREATE_TIME).toLocalDateTime();
        LocalDateTime updateTime = rs.getTimestamp(UPDATE_TIME).toLocalDateTime();
        String trainNumber = rs.getString(TRAIN_NUMBER);
        int numberOfSeats = rs.getInt(NUMBER_OF_SEATS);
        boolean relevant = rs.getBoolean(RELEVANT);
        train.setID(id);
        train.setCreateTime(createTime);
        train.setUpdateTime(updateTime);
        train.setTrainNumber(trainNumber);
        train.setNumberOfSeats(numberOfSeats);
        train.setRelevant(relevant);
        return train;
    }

}
