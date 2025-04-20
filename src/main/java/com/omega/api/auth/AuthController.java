package com.omega.api.auth;

import com.omega.api.auth.dtos.CreateUserDto;
import com.omega.api.auth.dtos.LoginUserDto;
import com.omega.api.auth.dtos.RecoveryJwtTokenDto;
import com.omega.api.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDto> authenticateUser(@RequestBody LoginUserDto loginUserDto) {
        RecoveryJwtTokenDto token = authService.authenticateUser(loginUserDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody CreateUserDto createUserDto) {
        authService.createUser(createUserDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/user")
    public ResponseEntity<Object> getUser(@AuthenticationPrincipal UserDetailsServiceImpl userDetails) {
        if (userDetails == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        var auth = authService.getAuthenticateUser(userDetails.getUsuario());
        return new ResponseEntity<>(auth, HttpStatus.OK);
    }


}