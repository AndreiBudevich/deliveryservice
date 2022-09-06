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

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "order_details", uniqueConstraints =
        {@UniqueConstraint(columnNames = {"order_id", "product_id"},
                name = "order_id_product_id_idx")})
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDetail extends BaseEntity {

    @JsonBackReference(value = "order-details")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "amount", nullable = false)
    private Long amount;

    public OrderDetail(Order order, Product product, Long price, Integer quantity, Long amount) {
        this.order = order;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.amount = amount;
    }
}

