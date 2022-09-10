package by.deliveryservice.model;

import by.deliveryservice.web.View;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "storage", uniqueConstraints =
        {@UniqueConstraint(columnNames = {"shop_id", "product_id"},
                name = "shop_id_product_id_idx")})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Storage extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    @JsonBackReference(value = "storage-shop")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(groups = View.Persist.class)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(groups = View.Persist.class)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public Storage(Shop shop, Product product, Integer quantity) {
        this.shop = shop;
        this.product = product;
        this.quantity = quantity;
    }

    public Storage(Integer id, Shop shop, Product product, Integer quantity) {
        super(id);
        this.shop = shop;
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", shop id=" + shop.getId() + "[" + shop.getName() + "]" +
                ", product id=" + product.getId() + "[" + product.getName() + "]" +
                ", quantity=" + quantity +
                '}';
    }
}
