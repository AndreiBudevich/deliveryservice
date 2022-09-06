package by.deliveryservice.repository.datajpa.crud;

import by.deliveryservice.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import static by.deliveryservice.util.validation.ValidationUtil.checkModification;

@NoRepositoryBean
public interface CommonCrudRepository<T extends BaseEntity> extends JpaRepository<T, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM #{#entityName} u WHERE u.id=:id")
    int delete(int id);

    default void deleteExisted(int id) {
        checkModification(delete(id), id);
    }
}
