package project.config.jsonWebToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.areas.users.models.bidingModels.UserLoginBindingModel;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collections;

@RestController
public class UserJWTController {
    private final TokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserJWTController(TokenProvider tokenProvider, AuthenticationManager authenticationManager) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/authenticate")
    public ResponseEntity authorize(@Valid @RequestBody UserLoginBindingModel userLoginBindingModel, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userLoginBindingModel.getEmail(), userLoginBindingModel.getPass());

        try {
            Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            boolean rememberMe = (userLoginBindingModel.getRememberMe() == null) ? false : userLoginBindingModel.getRememberMe();

            String jwt = tokenProvider.createToken(authentication, rememberMe);

            response.addHeader(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);
            return ResponseEntity.ok(new JWTToken(jwt));
        } catch (AuthenticationException ae) {
//            log.trace("Authentication exception trace: {}", ae);
            return new ResponseEntity<>(Collections.singletonMap("AuthenticationException",
                    ae.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
        }
    }
}
