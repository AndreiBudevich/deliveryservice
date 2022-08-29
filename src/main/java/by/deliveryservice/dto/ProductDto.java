package by.deliveryservice.dto;

import by.deliveryservice.model.NamedEntity;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class ProductDto extends NamedEntity {

    String description;

    String nameShop;

    Long price;

    Integer discount;

    public ProductDto(Integer id, String name, String description, String nameShop, Long price, Integer discount) {
        super(id, name);
        this.description = description;
        this.nameShop = nameShop;
        this.price = price;
        this.discount = discount;
    }
}
