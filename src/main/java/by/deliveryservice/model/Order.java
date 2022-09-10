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
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotNull(groups = View.Persist.class)
    @JsonBackReference(value = "order-client")
    private Client client;

    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime registered = getCurrentDateTime();

    @Column(name = "total_cost", nullable = false)
    @NotNull(groups = View.Persist.class)
    private Long totalCost = 0L;

    @NotBlank
    @Size(min = 2, max = 250)
    @NoHtml(groups = {View.Web.class})
    @Column(name = "delivery_address", nullable = false)
    private String deliveryAddress;

    @Column(name = "shipped", nullable = false, columnDefinition = "bool default false")
    private boolean shipped;

    public Order(Client client, String deliveryAddress) {
        this.client = client;
        this.deliveryAddress = deliveryAddress;
    }

    public Order(Integer id, Client client, String deliveryAddress) {
        super(id);
        this.client = client;
        this.deliveryAddress = deliveryAddress;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", client id=" + client.getId() + "[" + client.getSurname() + "]" +
                ", registered=" + registered +
                ", totalCost=" + totalCost +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", shipped=" + shipped +
                '}';
    }
}