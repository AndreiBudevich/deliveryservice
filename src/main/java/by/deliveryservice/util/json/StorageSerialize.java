package by.deliveryservice.util.json;

import by.deliveryservice.model.Storage;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.io.Serial;

public class StorageSerialize extends StdSerializer<Storage> {

    @Serial
    private static final long serialVersionUID = 1L;

    public StorageSerialize() {
        this(Storage.class);
    }

    protected StorageSerialize(Class<Storage> t) {
        super(t);
    }

    @Override
    public void serialize(Storage storage, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", storage.getId());
        jsonGenerator.writeFieldName("shop");
        jsonGenerator.writeObject(storage.getShop());
        jsonGenerator.writeFieldName("product");
        jsonGenerator.writeObject(storage.getProduct());
        jsonGenerator.writeNumberField("quantity", storage.getQuantity());
        jsonGenerator.writeEndObject();
    }
}