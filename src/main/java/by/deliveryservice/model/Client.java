package by.deliveryservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static by.deliveryservice.util.DateTimeUtil.getCurrentDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Client extends NamedEntity {

    @JsonProperty
    private String surname;

    @JsonProperty
    private String middleName;

    @JsonProperty
    private String residentialAddress;

    @JsonProperty
    private LocalDate dateOfBirth;

    @JsonProperty
    private LocalDateTime registered;

    @JsonProperty
    private List<Order> orders;

    public Client(String name, String surname, String middleName, String residentialAddress, LocalDate dateOfBirth) {
        super(null, name);
        this.surname = surname;
        this.middleName = middleName;
        this.residentialAddress = residentialAddress;
        this.dateOfBirth = dateOfBirth;
        this.registered = getCurrentDateTime();
    }

    @Override
    public String toString() {
        return "Client " +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", middleName='" + middleName + '\'' +
                ", residentialAddress='" + residentialAddress + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", registered=" + registered +
                ", orders=" + orders +
                ", name='" + name;
    }
}
