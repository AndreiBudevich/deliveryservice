package by.deliveryservice.repository;

import by.deliveryservice.model.OrderDetail;

import java.util.List;

public interface OrderDetailRepository {

    List<OrderDetail> getByOrderId(int id);
}
