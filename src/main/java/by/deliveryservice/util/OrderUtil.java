package by.deliveryservice.util;

import by.deliveryservice.dto.OrderDto;
import by.deliveryservice.model.Order;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderUtil {

    public static OrderDto createDto(Order order) {
        return new OrderDto(order.getTotalCost());
    }
}
