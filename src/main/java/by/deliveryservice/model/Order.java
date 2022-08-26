package by.deliveryservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

import static by.deliveryservice.util.DateTimeUtil.getCurrentDateTime;

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
    private Client client;

    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    private LocalDateTime registered;

    @Column(name = "total_cost", nullable = false)
    private Long totalCost = 0L;

    @Column(name = "delivery_address", nullable = false)
    private String deliveryAddress;

    @Column(name = "shipped", nullable = false, columnDefinition = "bool default false")
    private boolean shipped;

    public Order(Client client, String deliveryAddress) {
        this.client = client;
        this.registered = getCurrentDateTime();
        this.deliveryAddress = deliveryAddress;
    }

    @Override
    public String toString() {
        return "Order " +
                "id=" + id +
                ", client=" + client +
                ", registered=" + registered +
                ", totalCost=" + totalCost +
                ", deliveryAddress='" + deliveryAddress;
    }
}

