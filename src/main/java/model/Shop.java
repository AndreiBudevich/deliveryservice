package model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Shop extends NamedEntity {

    private String address;
    private String description;
    private String contact;
    private List <Product> products;
}
