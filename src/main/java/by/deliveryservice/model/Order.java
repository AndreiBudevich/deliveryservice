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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static by.deliveryservice.util.DateTimeUtil.getCurrentDateTime;
import static by.deliveryservice.util.StringUtil.stringBuilderCollection;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "client_order")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Client client;

    @Column(name = "date_time", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    private LocalDateTime dateTime;

    @Column(name = "total_cost", nullable = false)
    private Long totalCost;

    @Column(name = "delivery_address", nullable = false)
    private String deliveryAddress;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();

    public Order(Client client) {
        this.client = client;
        this.dateTime = getCurrentDateTime();
        this.deliveryAddress = client.getResidentialAddress();
    }

    @Override
    public String toString() {
        return "Order " +
                "id=" + id +
                ", client id=" + client.getId() + "[" + client.getName() + "]" +
                ", dateTime=" + dateTime +
                ", totalCost=" + totalCost +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", products:" + stringBuilderCollection(products);
    }
}

