package st.sergey.minsky.shop2doordelivers.mapper;

import org.springframework.stereotype.Component;
import st.sergey.minsky.shop2doordelivers.dto.UserDto;
import st.sergey.minsky.shop2doordelivers.model.User;

@Component
public class UserMapper {
    public User createUserDtoToUser(UserDto dto) {
        return User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }
}
