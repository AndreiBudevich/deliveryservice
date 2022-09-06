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
    @NotBlank
    @Size(min = 2, max = 50)
    @NoHtml(groups = {View.Web.class})
    private String surname;

    @NotBlank
    @Size(min = 2, max = 50)
    @NoHtml(groups = {View.Web.class})
    @Column(name = "middlename", nullable = false)
    private String middleName;

    @NotBlank
    @Size(min = 2, max = 250)
    @NoHtml(groups = {View.Web.class})
    @Column(name = "residential_address", nullable = false)
    private String residentialAddress;

    @Column(name = "birthday", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    private LocalDate birthday;

    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    private LocalDateTime registered = getCurrentDateTime();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    @JsonBackReference(value = "order-client")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Order> orders = new ArrayList<>();

    public Client(String name, String surname, String middleName, String residentialAddress, LocalDate birthday) {
        super(null, name);
        this.surname = surname;
        this.middleName = middleName;
        this.residentialAddress = residentialAddress;
        this.birthday = birthday;
        this.registered = getCurrentDateTime();
    }

    public Client(Integer id, String name, String surname, String middleName, String residentialAddress, LocalDate birthday) {
        super(id, name);
        this.surname = surname;
        this.middleName = middleName;
        this.residentialAddress = residentialAddress;
        this.birthday = birthday;
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
