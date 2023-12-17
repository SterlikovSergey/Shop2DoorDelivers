package st.sergey.minsky.shop2doordelivers.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import st.sergey.minsky.shop2doordelivers.dto.UserDto;
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
@PreAuthorize("permitAll()")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private  UserService userService;
    @Autowired
    private  ResponseErrorValidator responseErrorValidator;
    @Autowired
    private  JWTTokenProvider jwtTokenProvider;
    @Autowired
    private  AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<Object> authenticationUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
        System.out.println(loginRequest);
        System.out.println(result.toString());

        ResponseEntity<Object> errors = responseErrorValidator.mapValidationService(result);

        if(!ObjectUtils.isEmpty(errors)) return errors;

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTTokenSuccessResponse(true, jwt));
    }

@PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignupRequest signupRequest, BindingResult result){

    System.out.println(signupRequest.toString());

        ResponseEntity<Object> errors = responseErrorValidator.mapValidationService(result);
        if(!ObjectUtils.isEmpty(errors)) {
            return errors;
        }
        userService.createUser(signupRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }
}
