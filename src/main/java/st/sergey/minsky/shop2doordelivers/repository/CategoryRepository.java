package st.sergey.minsky.shop2doordelivers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import st.sergey.minsky.shop2doordelivers.model.Category;
import st.sergey.minsky.shop2doordelivers.repository.viev.CategoryView;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<CategoryView> findAllByOrderByNameAsc();

}
