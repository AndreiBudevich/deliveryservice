package by.deliveryservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public Storage(Shop shop, Product product, Integer quantity) {
        this.shop = shop;
        this.product = product;
        this.quantity = quantity;
    }
}
