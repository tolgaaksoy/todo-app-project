package com.tolgaaksoy.feleceToDoApp.security.services;

import com.tolgaaksoy.feleceToDoApp.models.ERole;
import com.tolgaaksoy.feleceToDoApp.models.Role;
import com.tolgaaksoy.feleceToDoApp.models.User;
import com.tolgaaksoy.feleceToDoApp.repository.RoleRepository;
import com.tolgaaksoy.feleceToDoApp.repository.UserRepository;
import com.tolgaaksoy.feleceToDoApp.security.jwt.JwtUtils;
import com.tolgaaksoy.feleceToDoApp.security.payload.request.LoginRequest;
import com.tolgaaksoy.feleceToDoApp.security.payload.request.SignupRequest;
import com.tolgaaksoy.feleceToDoApp.security.payload.response.JwtResponse;
import com.tolgaaksoy.feleceToDoApp.security.payload.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Validated
@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    public JwtResponse signIn(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return new JwtResponse(jwt,userDetails.getId(),userDetails.getUsername(), userDetails.getEmail(),roles);
    }

    public MessageResponse signUp(SignupRequest signUpRequest){
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return (new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new MessageResponse("Error: Email is already in use!");
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),signUpRequest.getName(),signUpRequest.getLastName());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return (new MessageResponse("User registered successfully!"));
    }
}
