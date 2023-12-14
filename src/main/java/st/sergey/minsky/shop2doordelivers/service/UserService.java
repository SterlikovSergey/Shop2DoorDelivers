package st.sergey.minsky.shop2doordelivers.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import st.sergey.minsky.shop2doordelivers.exceptions.UserExistException;
import st.sergey.minsky.shop2doordelivers.model.User;
import st.sergey.minsky.shop2doordelivers.model.enums.UserRole;
import st.sergey.minsky.shop2doordelivers.payload.request.SignupRequest;
import st.sergey.minsky.shop2doordelivers.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);


    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public User createUser(SignupRequest userIn){
        User user = new User();
        user.setEmail(userIn.getEmail());
        user.setName(userIn.getName());
        user.setPassword(bCryptPasswordEncoder.encode(userIn.getPassword()));
        user.getRole().add(UserRole.USER);
       try {
           LOG.info("Saving User " + user.getEmail());
           return userRepository.save(user);
       } catch (Exception e){
           LOG.error("Error during registration. {} ", e.getMessage());
           throw new UserExistException(" The User " + user.getEmail() + " already exist. Please check credentials" );
       }
    }
}
