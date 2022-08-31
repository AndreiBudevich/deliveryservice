package by.deliveryservice.util;

import by.deliveryservice.dto.OrderDto;
import by.deliveryservice.model.Order;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.List;

@UtilityClass
public class OrderUtil {

    public static List<OrderDto> getDtos(Collection<Order> orders) {
        return orders.stream()
                .map(OrderUtil::createDto)
                .toList();
    }

    public static OrderDto createDto(Order order) {
        return new OrderDto(order.getId(), order.getRegistered(), order.getTotalCost(), order.getDeliveryAddress(), order.isShipped());
    }
}
