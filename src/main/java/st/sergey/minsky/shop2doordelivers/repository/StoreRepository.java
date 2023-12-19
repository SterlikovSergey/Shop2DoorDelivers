package st.sergey.minsky.shop2doordelivers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import st.sergey.minsky.shop2doordelivers.model.Product;
import st.sergey.minsky.shop2doordelivers.model.Store;


@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {

}
