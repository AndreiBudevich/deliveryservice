package by.deliveryservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static by.deliveryservice.util.DateTimeUtil.getCurrentDateTime;
import static by.deliveryservice.util.StringUtil.stringBuilderCollection;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "client", uniqueConstraints =
        {@UniqueConstraint(columnNames = {"name", "surname", "middleName", "residential_address"},
                name = "name_surname_middleName_residential_address_idx")})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Client extends NamedEntity {

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "middlename", nullable = false)
    private String middleName;

    @Column(name = "residential_address", nullable = false)
    private String residentialAddress;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    @NotNull
    private LocalDateTime registered;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    @JsonManagedReference
    private List<Order> orders = new ArrayList<>();

    public Client(String name, String surname, String middleName, String residentialAddress, LocalDate birthday) {
        super(null, name);
        this.surname = surname;
        this.middleName = middleName;
        this.residentialAddress = residentialAddress;
        this.birthday = birthday;
        this.registered = getCurrentDateTime();
    }

    @Override
    public String toString() {
        return "Client " +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name +
                ", middleName='" + middleName + '\'' +
                ", residentialAddress='" + residentialAddress + '\'' +
                ", birthday=" + birthday +
                ", registered=" + registered +
                ", orders:" + stringBuilderCollection(orders);
    }
}
