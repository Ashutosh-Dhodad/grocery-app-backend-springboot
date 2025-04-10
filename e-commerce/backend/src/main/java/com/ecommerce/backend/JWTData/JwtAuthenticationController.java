package com.ecommerce.backend.JWTData;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerce.backend.UserData.CustomUserDetailsService;
import com.ecommerce.backend.UserData.UserEntity;
import com.ecommerce.backend.UserData.UserRepo;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class JwtAuthenticationController {

    private final CustomUserDetailsService userDetailsService;
    private final AuthenticationManager manager;
    private final JwtHelper helper;
    private final UserRepo userRepo;
    

    public JwtAuthenticationController(CustomUserDetailsService userDetailsService, AuthenticationManager manager, JwtHelper helper, UserRepo userRepo) {
        this.userDetailsService = userDetailsService;
        this.manager = manager;
        this.helper = helper;
        this.userRepo = userRepo;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody JwtRequest request) {
        try {
            doAuthenticate(request.getUsername(), request.getPassword());
        } catch (Exception e) {
            System.out.println("---Invalid credentials");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = helper.generateToken(userDetails);
        String role = userRepo.findRoleByUsername(userDetails.getUsername());

        // Retrieve the UserEntity from the database
        UserEntity userEntity = userRepo.findByUsername(userDetails.getUsername())
        .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + userDetails.getUsername()));

        // Get the user ID from the retrieved UserEntity
        Long userId = userEntity.getId();
        System.out.println(userId+"--------------------------------------------");
        return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername(), role, userId));
    }

    private void doAuthenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        manager.authenticate(authenticationToken);
    }
}