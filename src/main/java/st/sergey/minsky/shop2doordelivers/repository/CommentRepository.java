package st.sergey.minsky.shop2doordelivers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import st.sergey.minsky.shop2doordelivers.model.Comment;
import st.sergey.minsky.shop2doordelivers.model.Courier;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByCourierOrderByCreatedDateDesc(Courier courier);


}
