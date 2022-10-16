package entity;

import java.io.Serializable;
import java.util.Objects;

public class Station extends BasedEntity implements Serializable {

    private String stationName;
    private boolean relevant;
    private City city;

    public Station() {
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public boolean isRelevant() {
        return relevant;
    }

    public void setRelevant(boolean relevant) {
        this.relevant = relevant;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Station)) return false;
        Station station = (Station) o;
        return relevant == station.relevant && Objects.equals(stationName, station.stationName) && Objects.equals(city, station.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationName, relevant, city);
    }

    @Override
    public String toString() {
        return "Station{" + super.toString() +
                ", stationName='" + stationName + '\'' +
                ", relevant=" + relevant +
                ", city=" + city +
                '}';
    }
}
