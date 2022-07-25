package by.deliveryservice.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Order extends BaseEntity {

    LocalDateTime dateTime;
    Shop shop;
    Long totalCost;
    String deliveryAddress;
    List <Product> products;
}
