package by.deliveryservice.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true, exclude = "orders")
public class Client extends NamedEntity {

    private String surname;
    private String middleName;
    private String residentialAddress;
    private LocalDate dateOfBirth;
    private LocalDateTime registered;
    private List<Order> orders;
}
