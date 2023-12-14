package st.sergey.minsky.shop2doordelivers.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import st.sergey.minsky.shop2doordelivers.payload.request.LoginRequest;
import st.sergey.minsky.shop2doordelivers.payload.request.SignupRequest;
import st.sergey.minsky.shop2doordelivers.payload.response.JWTTokenSuccessResponse;
import st.sergey.minsky.shop2doordelivers.payload.response.MessageResponse;
import st.sergey.minsky.shop2doordelivers.security.JWTTokenProvider;
import st.sergey.minsky.shop2doordelivers.security.SecurityConstants;
import st.sergey.minsky.shop2doordelivers.service.UserService;
import st.sergey.minsky.shop2doordelivers.validations.ResponseErrorValidator;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/auth/")
@PreAuthorize("permitAll()")
@RequiredArgsConstructor

public class AuthController {
    private final UserService userService;
    private final ResponseErrorValidator responseErrorValidator;
    private final JWTTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<Object> authenticationUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
        ResponseEntity<Object> errors = responseErrorValidator.mapValidationServise(result);

        if(!ObjectUtils.isEmpty(errors)) return errors;

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTTokenSuccessResponse(true, jwt));
    }

@PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignupRequest signupRequest, BindingResult result){
        ResponseEntity<Object> errors = responseErrorValidator.mapValidationServise(result);
        if(!ObjectUtils.isEmpty(errors)) return errors;

        userService.createUser(signupRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }
}
