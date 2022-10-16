package entity;

import java.io.Serializable;
import java.util.Objects;

public class Train extends BasedEntity implements Serializable {

    private String trainNumber;
    private int numberOfSeats;
    private boolean relevant;

    public Train() {
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public boolean isRelevant() {
        return relevant;
    }

    public void setRelevant(boolean relevant) {
        this.relevant = relevant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Train)) return false;
        Train train = (Train) o;
        return numberOfSeats == train.numberOfSeats && relevant == train.relevant && Objects.equals(trainNumber, train.trainNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainNumber, numberOfSeats, relevant);
    }

    @Override
    public String toString() {
        return "Train{" + super.toString() +
                ", trainNumber='" + trainNumber + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", relevant=" + relevant +
                '}';
    }
}
