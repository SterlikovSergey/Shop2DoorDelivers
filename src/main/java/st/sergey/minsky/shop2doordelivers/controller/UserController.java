package st.sergey.minsky.shop2doordelivers.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import st.sergey.minsky.shop2doordelivers.configuration.JWTTokenProvider;
import st.sergey.minsky.shop2doordelivers.dto.AuthRequestDto;
import st.sergey.minsky.shop2doordelivers.dto.UserDto;
import st.sergey.minsky.shop2doordelivers.exception.UserExistException;
import st.sergey.minsky.shop2doordelivers.mapper.UserMapper;
import st.sergey.minsky.shop2doordelivers.model.User;
import st.sergey.minsky.shop2doordelivers.payload.response.JWTTokenSuccessResponse;
import st.sergey.minsky.shop2doordelivers.payload.response.MessageResponse;
import st.sergey.minsky.shop2doordelivers.service.UserService;
import st.sergey.minsky.shop2doordelivers.validations.ResponseErrorValidator;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/user")
@Tag(name = "User resource", description = "description from user resource")
public class UserController {

    private final JavaMailSender javaMailSender;

    private final UserService userService;
    private final UserMapper userMapper;
    private final JWTTokenProvider tokenProvider;
    private final ResponseErrorValidator responseErrorValidator;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/all")
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(required = false) String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userService.getAllUsers(pageable);
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> registration(@Valid @RequestBody UserDto dto, BindingResult result) {
        ResponseEntity<Object> errors = responseErrorValidator.mapValidationService(result);
        if (!ObjectUtils.isEmpty(errors)) {
            return errors;
        }
        return new ResponseEntity<>(userService.create(userMapper.createUserDtoToUser(dto)),
                HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody AuthRequestDto dto, BindingResult result) {
        ResponseEntity<Object> errors = responseErrorValidator.mapValidationService(result);
        if (!ObjectUtils.isEmpty(errors)) {
            return errors;
        }
        UserDetails userDetails = userService.loadUserByUsername(dto.getUsername());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(dto.getPassword(), userDetails.getPassword())) {
            Authentication auth = new UsernamePasswordAuthenticationToken(userDetails,
                    null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
            String token = tokenProvider.generateToken(userDetails.getUsername(),
                    userDetails.getAuthorities());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.badRequest().build();
    }

}
