package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order extends BasedEntity implements Serializable {

    private BigDecimal orderPrise;
    private int countOfPurchasedTickets;
    private User user;
    private Route route;


}
