package by.deliveryservice.util.json;

import by.deliveryservice.model.Order;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.io.Serial;

public class OrderSerialize extends StdSerializer<Order> {

    @Serial
    private static final long serialVersionUID = 1L;

    public OrderSerialize() {
        this(Order.class);
    }

    protected OrderSerialize(Class<Order> t) {
        super(t);
    }

    @Override
    public void serialize(Order order, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", order.getId());
        jsonGenerator.writeFieldName("client");
        jsonGenerator.writeObject(order.getClient());
        jsonGenerator.writeFieldName("registered");
        jsonGenerator.writeObject(order.getRegistered());
        jsonGenerator.writeNumberField("totalCost", order.getTotalCost());
        jsonGenerator.writeStringField("deliveryAddress", order.getDeliveryAddress());
        jsonGenerator.writeBooleanField("shipped", order.isShipped());
        jsonGenerator.writeEndObject();
    }
}
