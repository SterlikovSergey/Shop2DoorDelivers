package st.sergey.minsky.shop2doordelivers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import st.sergey.minsky.shop2doordelivers.model.Courier;

import java.util.List;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long> {
    boolean existsByName(String name);
    List<Object> findAllBy();
}
