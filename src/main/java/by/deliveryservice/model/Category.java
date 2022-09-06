package by.deliveryservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "category", uniqueConstraints =
        {@UniqueConstraint(columnNames = {"name"},
                name = "category_name_idx")})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Category extends NamedEntity {

    @ManyToMany
    @JsonBackReference(value = "category-product")
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();

    public Category(String name) {
        super(null, name);
    }

    public Category(Integer id, String name) {
        super(id, name);
    }
}
