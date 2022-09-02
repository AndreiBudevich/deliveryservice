package by.deliveryservice.repository.datajpa.crud;

import by.deliveryservice.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommonCrudRepository<T extends BaseEntity> extends JpaRepository<T, Integer> {
}
