package by.deliveryservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product", uniqueConstraints =
        {@UniqueConstraint(columnNames = {"name", "description"},
                name = "name_description_idx")})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product extends NamedEntity {

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Shop shop;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "discount", nullable = false)
    private Integer discount;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "order_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private Set<Order> orders = new HashSet<>();

    public Product(String name, String description, Shop shop, Long price, Integer discount) {
        super(null, name);
        this.description = description;
        this.shop = shop;
        this.price = price;
        this.discount = discount;
    }

    public Product get() {
        return this;
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
