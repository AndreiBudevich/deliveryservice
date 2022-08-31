package by.deliveryservice.dto;

import by.deliveryservice.model.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Value
public class OrderDto extends BaseEntity {

    LocalDateTime registered;

    Long totalCost;

    String deliveryAddress;

    boolean shipped;

    public OrderDto(Integer id, LocalDateTime registered, Long totalCost, String deliveryAddress, boolean shipped) {
        super(id);
        this.registered = registered;
        this.totalCost = totalCost;
        this.deliveryAddress = deliveryAddress;
        this.shipped = shipped;
    }
}


