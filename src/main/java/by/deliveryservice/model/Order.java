package by.deliveryservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static by.deliveryservice.util.DateTimeUtil.getCurrentDateTime;
import static by.deliveryservice.util.StringUtil.stringBuilderCollection;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order extends BaseEntity {

    @JsonProperty
    private Client client;

    @JsonProperty
    private LocalDateTime dateTime;

    @JsonProperty
    private Long totalCost;

    @JsonProperty
    private String deliveryAddress;

    @JsonProperty
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
