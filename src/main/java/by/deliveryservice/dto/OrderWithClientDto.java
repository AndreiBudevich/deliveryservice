package by.deliveryservice.dto;

import by.deliveryservice.model.BaseEntity;
import by.deliveryservice.model.Client;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Value
public class OrderWithClientDto extends BaseEntity {

    Client client;

    LocalDateTime registered;

    Long totalCost;

    String deliveryAddress;

    boolean shipped;

    public OrderWithClientDto(Integer id, LocalDateTime registered, Long totalCost, String deliveryAddress, boolean shipped, Client client) {
        super(id);
        this.registered = registered;
        this.totalCost = totalCost;
        this.deliveryAddress = deliveryAddress;
        this.shipped = shipped;
        this.client = client;
    }
}
