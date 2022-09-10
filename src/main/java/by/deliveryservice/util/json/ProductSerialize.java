package by.deliveryservice.util.json;

import by.deliveryservice.model.Product;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.io.Serial;

public class ProductSerialize extends StdSerializer<Product> {

    @Serial
    private static final long serialVersionUID = 1L;

    public ProductSerialize() {
        this(Product.class);
    }

    protected ProductSerialize(Class<Product> t) {
        super(t);
    }

    @Override
    public void serialize(Product product, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", product.getId());
        jsonGenerator.writeStringField("name", product.getName());
        jsonGenerator.writeStringField("description", product.getDescription());
        jsonGenerator.writeFieldName("shop");
        jsonGenerator.writeObject(product.getShop());
        jsonGenerator.writeNumberField("price", product.getPrice());
        jsonGenerator.writeNumberField("discount", product.getDiscount());
        jsonGenerator.writeFieldName("categories");
        jsonGenerator.writeObject(product.getCategories());
        jsonGenerator.writeEndObject();
    }
}
