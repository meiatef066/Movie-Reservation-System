package com.system.movie_reservation_system.service;

import com.system.movie_reservation_system.dto.requests.AuthRequest;
import org.springframework.stereotype.Service;
import com.system.movie_reservation_system.dto.responses.HttpCustomResponse;
import com.system.movie_reservation_system.model.User;
import com.system.movie_reservation_system.repository.UserRepository;
import com.system.movie_reservation_system.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    public AuthService( UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    public HttpCustomResponse<Object> register( AuthRequest authRequest ) {
        try {
            var user = userRepository.findByEmail(authRequest.getEmail());

            if (user.isPresent()) {
                return new HttpCustomResponse<>(409, null, "Email already taken");
            }
            User newUser = new User();
            newUser.setEmail(authRequest.getEmail());
            newUser.setPassword(passwordEncoder.encode(authRequest.getPassword()));
            newUser.setRole(User.Role.USER);

            userRepository.save(newUser);
            var userToken = jwtUtil.generateToken(newUser.getEmail(), newUser.getRole().name());
            return new HttpCustomResponse<>(201, userToken, "register success");
        } catch (Exception e) {
            logger.error("Error in register", e);
            return new HttpCustomResponse<>(500, null, "Internal Server Error");
        }
    }

    public HttpCustomResponse<Object> login( AuthRequest authRequest ) {
        try {
            var user = userRepository.findByEmail(authRequest.getEmail());
            if (user.isEmpty()) {
                return new HttpCustomResponse<>(400, null, "this email not exist, please create account then login 👍");
            }
            if (!passwordEncoder.matches(authRequest.getPassword(), user.get().getPassword())) {
                return new HttpCustomResponse<>(409, null, "wrong password");
            }

            var userToken = jwtUtil.generateToken(user.get().getEmail(),user.get().getRole().name());
            return new HttpCustomResponse<>(200, userToken, "login success");
        } catch (Exception e) {
            System.out.println(e);
            return new HttpCustomResponse<>(500, null, "Internal Server Error");
        }
    }

    public HttpCustomResponse<Object> logout( AuthRequest authRequest ) {
        return null;
    }
    public HttpCustomResponse<Object> changePassword( AuthRequest authRequest ) {
        return null;
    }
    public HttpCustomResponse<Object> deleteAccount( AuthRequest authRequest ) {
        return null;
    }
}
