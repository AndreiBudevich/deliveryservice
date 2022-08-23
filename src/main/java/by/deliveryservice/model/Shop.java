package by.deliveryservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static by.deliveryservice.util.StringUtil.stringBuilderCollection;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "shop", uniqueConstraints =
        {@UniqueConstraint(columnNames = {"name"},
                name = "shop_name_idx")})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Shop extends NamedEntity {

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "contact", nullable = false)
    private String contact;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shop")
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
