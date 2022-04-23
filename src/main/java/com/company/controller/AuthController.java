package com.company.controller;

import com.company.common.enums.RoleEnum;
import com.company.dto.ResponeJson;
import com.company.entity.Role;
import com.company.entity.User;
import com.company.exception.BadRequestException;
import com.company.exception.ErrorParam;
import com.company.exception.Errors;
import com.company.exception.SysError;
import com.company.dto.LoginRequest;
import com.company.dto.SignupRequest;
import com.company.dto.JwtResponse;
import com.company.repository.RoleRepository;
import com.company.repository.UserRepository;
import com.company.common.utils.JwtUtils;
import com.company.security.services.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    AuthenticationManager authenticationManager;

    UserRepository userRepository;

    RoleRepository roleRepository;

    PasswordEncoder encoder;

    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
                jwtToken,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponeJson<?>> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(
                    new ResponeJson<>(new ResponeJson<>(HttpStatus.BAD_REQUEST, "Username is already taken!"))
            );
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(
                    new ResponeJson<>(new ResponeJson<>(HttpStatus.BAD_REQUEST, "Email is already in use!"))
            );
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        user.setName(signUpRequest.getName());
        user.setPhone(signUpRequest.getPhone());
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
                    .orElseThrow(() -> new BadRequestException(
                            new SysError(Errors.ERROR_ROLE_NOT_FOUND, new ErrorParam(Errors.ROLE))));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN)
                                .orElseThrow(() -> new BadRequestException(
                                        new SysError(Errors.ERROR_ROLE_NOT_FOUND, new ErrorParam(Errors.ROLE))));
                        roles.add(adminRole);

                        break;
                    case "sadmin":
                        Role modRole = roleRepository.findByName(RoleEnum.ROLE_SUPER_ADMIN)
                                .orElseThrow(() -> new BadRequestException(
                                        new SysError(Errors.ERROR_ROLE_NOT_FOUND, new ErrorParam(Errors.ROLE))));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
                                .orElseThrow(() -> new BadRequestException(
                                        new SysError(Errors.ERROR_ROLE_NOT_FOUND, new ErrorParam(Errors.ROLE))));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new ResponeJson<>(HttpStatus.CREATED, "User registered successfully!"));
    }
}
