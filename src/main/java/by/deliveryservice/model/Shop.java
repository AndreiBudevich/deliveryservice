package by.deliveryservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static by.deliveryservice.util.StringUtil.stringBuilderCollection;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Shop extends NamedEntity {

    @JsonProperty
    private String address;

    @JsonProperty
    private String description;

    @JsonProperty
    private String contact;

    @JsonProperty
    private List<Product> products = new ArrayList<>();

    public Shop(String name, String address, String description, String contact) {
        super(null, name);
        this.address = address;
        this.description = description;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Shop " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", contact='" + contact + '\'' +
                ", products:" + stringBuilderCollection(products);
    }
}
