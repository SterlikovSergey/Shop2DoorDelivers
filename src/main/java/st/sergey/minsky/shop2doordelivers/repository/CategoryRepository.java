package st.sergey.minsky.shop2doordelivers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import st.sergey.minsky.shop2doordelivers.model.Category;
import st.sergey.minsky.shop2doordelivers.repository.view.CategoryView;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Set<CategoryView> findAllByOrderByNameAsc();

    Optional<Category> findByName(String name);

    boolean existsByName(String name);
}
