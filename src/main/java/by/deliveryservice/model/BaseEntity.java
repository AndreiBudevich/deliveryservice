package by.deliveryservice.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseEntity {

    protected Integer id;

    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !this.getClass().equals(o.getClass())) {
            return false;
        }
        BaseEntity that = (BaseEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " id:" + id;
    }
}
