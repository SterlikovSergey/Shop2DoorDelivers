package st.sergey.minsky.shop2doordelivers.mapper;

import org.springframework.stereotype.Component;
import st.sergey.minsky.shop2doordelivers.dto.UserDto;
import st.sergey.minsky.shop2doordelivers.model.User;

@Component
public class UserMapper {
    public User createUserDtoToUser(UserDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }
}
