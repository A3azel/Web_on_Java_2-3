package entity;

import java.io.Serializable;
import java.util.Objects;

public class City extends BasedEntity implements Serializable {

    private String cityName;
    private boolean relevant;

    public City() {
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return relevant == city.relevant && Objects.equals(cityName, city.cityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityName, relevant);
    }

    @Override
    public String toString() {
        return "City{" + super.toString() +
                ", cityName='" + cityName + '\'' +
                ", relevant=" + relevant +
                '}';
    }
}
