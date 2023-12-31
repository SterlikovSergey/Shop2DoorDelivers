package st.sergey.minsky.shop2doordelivers.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import st.sergey.minsky.shop2doordelivers.model.User;
import st.sergey.minsky.shop2doordelivers.model.enums.UserStatus;
import st.sergey.minsky.shop2doordelivers.repository.view.UserView;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByIdAndStatus(Long id, UserStatus status);

    Page<User> findAllBy(Pageable pageable);

}
