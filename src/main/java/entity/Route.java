package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class Route extends BasedEntity implements Serializable {

    private String departureCity;
    private String arrivalCity;
    private String startStation;
    private LocalDateTime departureTime;
    private LocalTime travelTime;
    private String arrivalStation;
    private LocalDateTime arrivalTime;
    private int numberOfFreeSeats;
    private BigDecimal priseOfTicket;
    private String train;
    private boolean relevant;

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public boolean isRelevant() {
        return relevant;
    }

    public void setRelevant(boolean relevant) {
        this.relevant = relevant;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(LocalTime travelTime) {
        this.travelTime = travelTime;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getNumberOfFreeSeats() {
        return numberOfFreeSeats;
    }

    public void setNumberOfFreeSeats(int numberOfFreeSeats) {
        this.numberOfFreeSeats = numberOfFreeSeats;
    }

    public BigDecimal getPriseOfTicket() {
        return priseOfTicket;
    }

    public void setPriseOfTicket(BigDecimal priseOfTicket) {
        this.priseOfTicket = priseOfTicket;
    }

    public String getTrain() {
        return train;
    }

    public void setTrain(String train) {
        this.train = train;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;
        Route route = (Route) o;
        return numberOfFreeSeats == route.numberOfFreeSeats && relevant == route.relevant && Objects.equals(departureCity, route.departureCity) && Objects.equals(arrivalCity, route.arrivalCity) && Objects.equals(startStation, route.startStation) && Objects.equals(departureTime, route.departureTime) && Objects.equals(travelTime, route.travelTime) && Objects.equals(arrivalStation, route.arrivalStation) && Objects.equals(arrivalTime, route.arrivalTime) && Objects.equals(priseOfTicket, route.priseOfTicket) && Objects.equals(train, route.train);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departureCity, arrivalCity, startStation, departureTime, travelTime, arrivalStation, arrivalTime, numberOfFreeSeats, priseOfTicket, train, relevant);
    }

    @Override
    public String toString() {
        return "Route{" + super.toString() +
                ", startStation='" + startStation + '\'' +
                ", departureTime=" + departureTime +
                ", travelTime=" + travelTime +
                ", arrivalStation='" + arrivalStation + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", numberOfFreeSeats=" + numberOfFreeSeats +
                ", priseOfTicket=" + priseOfTicket +
                ", train='" + train + '\'' +
                ", relevant=" + relevant +
                '}';
    }
}
