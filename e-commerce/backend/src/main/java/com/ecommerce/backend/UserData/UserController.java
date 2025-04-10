package com.ecommerce.backend.UserData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.backend.JWTData.JwtHelper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser( @Valid  @RequestBody UserEntity user) {

        if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email is already taken!");
        }

        userService.createUser(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

        // Generate JWT token
        String token = jwtHelper.generateToken(userDetails);

        return ResponseEntity.ok(token);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserEntity> getUserByUserName(@PathVariable String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UserEntity user = (UserEntity) userDetails;
        return ResponseEntity.ok(user);
    }

   @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();

        List<Map<String, Object>> simplifiedUsers = users.stream().map(user -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", user.getId());
            map.put("name", user.getUsername());
            map.put("email", user.getEmail());
            return map;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(simplifiedUsers);
    }

}
