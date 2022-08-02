package by.deliveryservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product extends NamedEntity {

    @JsonProperty
    private String description;

    @JsonProperty
    private Shop shop;

    @JsonProperty
    private Long price;

    @JsonProperty
    private Integer discount;

    @JsonProperty
    Set<Category> categories;

    public Product(String name, String description, Shop shop, Long price, Integer discount) {
        super(null, name);
        this.description = description;
        this.shop = shop;
        this.price = price;
        this.discount = discount;
    }
}
