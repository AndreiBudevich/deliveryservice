package by.deliveryservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private Set<Category> categories = new HashSet<>();

    public Product(String name, String description, Shop shop, Long price, Integer discount) {
        super(null, name);
        this.description = description;
        this.shop = shop;
        this.price = price;
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Product " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", shop id=" + shop.getId() + "[" + shop.getName() + "]" +
                ", price=" + price +
                ", discount=" + discount +
                ", categories=" + categories;
    }
}
