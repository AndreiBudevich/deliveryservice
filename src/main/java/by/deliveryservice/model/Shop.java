package by.deliveryservice.model;

import by.deliveryservice.util.validation.NoHtml;
import by.deliveryservice.web.View;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "shop", uniqueConstraints =
        {@UniqueConstraint(columnNames = {"name"},
                name = "shop_name_idx")})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Shop extends NamedEntity {

    @Column(name = "address", nullable = false)
    @NotBlank
    @Size(min = 2, max = 250)
    @NoHtml(groups = {View.Web.class})
    private String address;

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 2, max = 1000)
    @NoHtml(groups = {View.Web.class})
    private String description;

    @Column(name = "contact", nullable = false)
    @NotBlank
    @Size(min = 2, max = 100)
    @NoHtml(groups = {View.Web.class})
    private String contact;

    public Shop(String name, String address, String description, String contact) {
        super(null, name);
        this.address = address;
        this.description = description;
        this.contact = contact;
    }

    public Shop(Integer id, String name, String address, String description, String contact) {
        super(id, name);
        this.address = address;
        this.description = description;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Shop " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", contact='" + contact;
    }
}
