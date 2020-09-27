package com.project.demo.service;

import com.project.demo.entity.AuthorityEnum;
import com.project.demo.security.payload.request.LoginRequest;
import com.project.demo.security.payload.request.SignupRequest;
import com.project.demo.security.payload.response.JwtResponse;
import com.project.demo.security.payload.response.MessageResponse;
import com.project.demo.security.jwt.JwtUtils;
import com.project.demo.security.service.UserDetailsImpl;
import com.project.demo.repository.AuthorityRepository;
import com.project.demo.repository.UserRepository;
import com.project.demo.entity.Authority;
import com.project.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUser(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User blockUser(long id) {
        User user = userRepository.findById(id).get();
        user.setEnabled(false);
        userRepository.save(user);
        return user;
    }

    @Override
    public User unblockUser(long id) {
        User user = userRepository.findById(id).get();
        user.setEnabled(true);
        userRepository.save(user);
        return user;
    }

    @Override
    public User makeAdmin(long id) {
        User user = userRepository.findById(id).get();
        Set<Authority> authorities = user.getAuthorities();
        for (Authority authority : authorities) {
            if (authority.getAuthority().equals(AuthorityEnum.ROLE_ADMIN)) {
                break;
            } else {
                authorities.add(authorityRepository.findByAuthority(AuthorityEnum.ROLE_ADMIN).get());
                user.setAuthorities(authorities);
            }
        }
        userRepository.save(user);
        return user;
    }

    @Override
    public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Username is already taken"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Email is already in use"));
        }

        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        Set<Authority> roles = setRoles();
        user.setAuthorities(roles);
        user.setEnabled(true);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }

    private Set<Authority> setRoles(){
        Set<Authority> roles = new HashSet<>();
        Authority userRole = authorityRepository.findByAuthority(AuthorityEnum.ROLE_USER).get();
        roles.add(userRole);
        return roles;
    }

    @Override
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {

        if (!userRepository.existsByUsername(loginRequest.getUsername())) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Username with such name does not exist"));
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles));
        } catch (DisabledException exception) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: User is blocked"));
        } catch (BadCredentialsException exception) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Incorrect password"));
        }
    }
}
