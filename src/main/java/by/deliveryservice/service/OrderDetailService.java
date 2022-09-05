package by.deliveryservice.service;

import by.deliveryservice.model.OrderDetail;

import java.util.List;

public interface OrderDetailService {

    List<OrderDetail> getAllByOrderIdWithProduct(int orderId);

    OrderDetail get(int id);

    void delete(int clientId, int orderId, int id);

    void update(OrderDetail orderDetail, int orderId, int productId, int clientId);

    void addProduct(int clientId, int orderId, int productId);

    void deleteProduct(int clientId, int orderId, int productId);
}
