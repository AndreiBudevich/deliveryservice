package by.deliveryservice.util.json;

import by.deliveryservice.model.OrderDetail;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.io.Serial;

public class OrderDetailSerialize extends StdSerializer<OrderDetail> {

    @Serial
    private static final long serialVersionUID = 1L;

    public OrderDetailSerialize() {
        this(OrderDetail.class);
    }

    protected OrderDetailSerialize(Class<OrderDetail> t) {
        super(t);
    }

    @Override
    public void serialize(OrderDetail orderDetail, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", orderDetail.getId());
        jsonGenerator.writeFieldName("order");
        jsonGenerator.writeObject(orderDetail.getOrder());
        jsonGenerator.writeFieldName("product");
        jsonGenerator.writeObject(orderDetail.getProduct());
        jsonGenerator.writeNumberField("price", orderDetail.getPrice());
        jsonGenerator.writeNumberField("quantity", orderDetail.getQuantity());
        jsonGenerator.writeNumberField("amount", orderDetail.getAmount());
        jsonGenerator.writeEndObject();
    }
}
