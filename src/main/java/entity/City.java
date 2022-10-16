package entity;

import java.io.Serializable;

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
}
