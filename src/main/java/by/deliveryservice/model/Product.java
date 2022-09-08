package by.deliveryservice.model;

import by.deliveryservice.util.validation.NoHtml;
import by.deliveryservice.web.View;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product", uniqueConstraints =
        {@UniqueConstraint(columnNames = {"name", "description", "shop_id"},
                name = "name_description_shop_id_idx")})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product extends NamedEntity {

    @NotBlank
    @Size(min = 2, max = 1000)
    @NoHtml(groups = {View.Web.class})
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference(value = "product-shop")
    private Shop shop;

    @Column(name = "price", nullable = false)
    @NotNull
    @Range(min = 1, max = 50000)
    private Long price;

    @NotNull
    @Range(min = 0, max = 100)
    @Column(name = "discount", nullable = false)
    private Integer discount;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonBackReference(value = "category-product")
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Category> categories = new HashSet<>();

    @JsonBackReference(value = "order-product")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "order_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Order> orders = new HashSet<>();

    public Product(String name, String description, Shop shop, Long price, Integer discount) {
        super(null, name);
        this.description = description;
        this.shop = shop;
        this.price = price;
        this.discount = discount;
    }

    public Product(Integer id, String name, String description, Shop shop, Long price, Integer discount) {
        super(id, name);
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
