package st.sergey.minsky.shop2doordelivers.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import st.sergey.minsky.shop2doordelivers.exception.StoreNotFoundException;
import st.sergey.minsky.shop2doordelivers.exception.UserExistException;
import st.sergey.minsky.shop2doordelivers.exception.UserNotFoundException;
import st.sergey.minsky.shop2doordelivers.model.Store;
import st.sergey.minsky.shop2doordelivers.model.User;
import st.sergey.minsky.shop2doordelivers.model.enums.Role;
import st.sergey.minsky.shop2doordelivers.model.enums.StoreStatus;
import st.sergey.minsky.shop2doordelivers.model.enums.UserStatus;
import st.sergey.minsky.shop2doordelivers.repository.UserRepository;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public Object create(User user) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        user.setCreatedDate(currentDateTime);
        user.setRoles(Set.of(Role.USER));
        user.setStatus(UserStatus.CREATED);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        try {
            LOG.info("Saving User {}", user.getEmail());
            return userRepository.save(user);
        } catch (UserExistException e) {
            LOG.error("Error during registration. {}", e.getMessage());
            throw new UserExistException("The user " + user.getUsername() + " already exist. Please check credentials");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        /*return byUsername.orElseThrow(()-> new UserNotFoundException("Username not found with username " + username));
    }*/
        return userRepository.findByUsername(username).orElseThrow(() ->
                new UserNotFoundException("Username not found with username " + username));
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }


    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAllBy(pageable);
    }

    public User getUserByName(String name) {
        Optional<User> byName = userRepository.findByUsername(name);
        return byName.orElseThrow(UserNotFoundException::new);
    }


   /* public User setDeleteStatusById(Long id) {
*//*        Optional<User> user = userRepository.findUserByIdAndStatus(id,UserStatus.DELETED);
        user.ifPresent(userRepository::delete);
            LOG.info("Deleting user: " + id);
            user.ifPresentOrElse(user1 -> user);

        return userRepository.save(user);*//*
    }*/



}
