package com.system.movie_reservation_system.controller;

import com.system.movie_reservation_system.model.User;
import com.system.movie_reservation_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/users")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/{userId}/promote")
    public ResponseEntity<Object> promoteUser( @PathVariable Long userId ){
        var user= userRepository.findById(userId);
        if(!user.isPresent()){
            return ResponseEntity.notFound().build();
        }
        user.get().setRole(User.Role.ADMIN);
        userRepository.save(user.get());
        return ResponseEntity.status(200).body("user Id :"+user.get().getId()+" "+user.get().getEmail()+" is promoted to admin 👍");
    }
}
