package by.deliveryservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

import static by.deliveryservice.util.DateTimeUtil.getCurrentDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order extends BaseEntity {

    @JsonProperty
    Client client;

    @JsonProperty
    LocalDateTime dateTime;

    @JsonProperty
    Shop shop;

    @JsonProperty
    Long totalCost;

    @JsonProperty
    String deliveryAddress;

    @JsonProperty
    List <Product> products = new ArrayList<>();

    public Order(Client client, Shop shop) {
        this.client = client;
        this.dateTime = getCurrentDateTime();
        this.shop = shop;
        this.totalCost = products.stream().flatMapToLong(product -> LongStream.of(product.getPrice())).sum();
        this.deliveryAddress = client.getResidentialAddress();
    }
}
