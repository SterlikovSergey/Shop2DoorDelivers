package st.sergey.minsky.shop2doordelivers.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import st.sergey.minsky.shop2doordelivers.exception.UserExistException;
import st.sergey.minsky.shop2doordelivers.exception.UserNotFoundException;
import st.sergey.minsky.shop2doordelivers.model.User;
import st.sergey.minsky.shop2doordelivers.model.enums.Role;
import st.sergey.minsky.shop2doordelivers.model.enums.UserStatus;
import st.sergey.minsky.shop2doordelivers.repository.UserRepository;
import st.sergey.minsky.shop2doordelivers.utils.StringUtil;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final StringUtil stringUtil;

    public Object create(User user) {
        String capitalizedUsername = stringUtil.capitalizeFirstLetter(user.getUsername());
        if (isUsernameUnique(capitalizedUsername) && isEmailUnique(user.getEmail())) {
            return saveUser(user, capitalizedUsername);
        } else {
            LOG.error("ELSE Error during registration. Username with name {} already exists.", user.getUsername());
            throw new UserExistException(" else The username with name " + user.getUsername() +
                    " or email " + user.getEmail() + " already exists. Please check credentials");
        }
    }

    private Object saveUser(User user, String capitalizedUsername) {
        try {
            LOG.info("Saving Username {} and email {} ", capitalizedUsername, user.getEmail());
            return userRepository.save(User
                    .builder()
                    .createdDate(LocalDateTime.now())
                    .username(capitalizedUsername)
                    .roles(Set.of(Role.USER))
                    .status(UserStatus.CREATED)
                    .password(new BCryptPasswordEncoder().encode(user.getPassword()))
                    .build());
        } catch (DataIntegrityViolationException e) {
            LOG.error("Error during registration. {}", e.getMessage());
            throw new UserExistException("Catch The username with name " + user.getUsername() +
                    " or email " + user.getEmail() + " already exists. Please check credentials");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    LOG.error("Error during login. Username with name {} not found.", username);
                    return new UsernameNotFoundException("The username with name " + username +
                            " not found. Please enter correct name");
                });
    }

    private UserDetails getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }


    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAllBy(pageable);
    }

    public User getUserByName(String name) {
        Optional<UserDetails> byName = userRepository.findByUsername(name);
        return (User) byName.orElseThrow(UserNotFoundException::new);
    }

    private boolean isUsernameUnique(String username) {
        return !userRepository.existsByUsername(username);
    }

    private boolean isEmailUnique(String email) {
        return !userRepository.existsByEmail(email);
    }
}
