package model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Product extends NamedEntity {

    private String description;
    private Shop shop;
    private Long price;
    private Integer discount;
}
