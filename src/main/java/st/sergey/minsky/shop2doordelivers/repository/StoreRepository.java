package st.sergey.minsky.shop2doordelivers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import st.sergey.minsky.shop2doordelivers.model.Product;
import st.sergey.minsky.shop2doordelivers.model.Store;
import st.sergey.minsky.shop2doordelivers.model.enums.StoreStatus;
import st.sergey.minsky.shop2doordelivers.repository.view.StoreView;

import java.util.List;
import java.util.Optional;


@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {
    List<StoreView> findAllByStatus(StoreStatus status);

    Optional<Store> findByName(String name);
}
