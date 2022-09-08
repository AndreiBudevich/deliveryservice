package by.deliveryservice.util;

import by.deliveryservice.dto.OrderDto;
import by.deliveryservice.dto.OrderWithClientDto;
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

    public static List<OrderWithClientDto> getWithClientDtos(Collection<Order> orders) {
        return orders.stream()
                .map(OrderUtil::createWithClientDto)
                .toList();
    }

    public static OrderWithClientDto createWithClientDto(Order order) {
        return new OrderWithClientDto(order.getId(), order.getRegistered(), order.getTotalCost(), order.getDeliveryAddress(), order.isShipped(), order.getClient());
    }
}
