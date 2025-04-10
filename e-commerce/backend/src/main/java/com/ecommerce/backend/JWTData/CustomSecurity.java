package com.ecommerce.backend.JWTData;

import java.util.logging.Logger;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component("customSecurity")
public class CustomSecurity {

    private static final Logger logger = Logger.getLogger(CustomSecurity.class.getName());
    
    public boolean hasRole(Authentication authentication,String role) {
        logger.info("Checking role: " + role);
        authentication.getAuthorities().forEach(authority -> logger.info("Authority: " + authority.getAuthority()));
        return authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .anyMatch(authority -> authority.equalsIgnoreCase("ROLE_"+ role) || authority.equalsIgnoreCase(role));
    }
}
