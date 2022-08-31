package by.deliveryservice.dto;

import lombok.Value;

@Value
public class OrderDto {

    Long totalCost;

    public OrderDto(Long totalCost) {
        this.totalCost = totalCost;
    }
}
