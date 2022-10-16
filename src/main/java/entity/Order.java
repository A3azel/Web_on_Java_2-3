package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Order extends BasedEntity implements Serializable {

    private BigDecimal orderPrise;
    private int countOfPurchasedTickets;
    private boolean orderStatus;
    private User user;
    private Route route;

    public BigDecimal getOrderPrise() {
        return orderPrise;
    }

    public void setOrderPrise(BigDecimal orderPrise) {
        this.orderPrise = orderPrise;
    }

    public int getCountOfPurchasedTickets() {
        return countOfPurchasedTickets;
    }

    public void setCountOfPurchasedTickets(int countOfPurchasedTickets) {
        this.countOfPurchasedTickets = countOfPurchasedTickets;
    }

    public boolean isOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return countOfPurchasedTickets == order.countOfPurchasedTickets && orderStatus == order.orderStatus && Objects.equals(orderPrise, order.orderPrise) && Objects.equals(user, order.user) && Objects.equals(route, order.route);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderPrise, countOfPurchasedTickets, orderStatus, user, route);
    }

    @Override
    public String toString() {
        return "Order{" + super.toString() +
                ", orderPrise=" + orderPrise +
                ", countOfPurchasedTickets=" + countOfPurchasedTickets +
                ", orderStatus=" + orderStatus +
                ", user=" + user +
                ", route=" + route +
                '}';
    }
}
