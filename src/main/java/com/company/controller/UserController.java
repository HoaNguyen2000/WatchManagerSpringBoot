package com.company.controller;

import com.company.dto.ChangePasswordDTO;
import com.company.entity.User;
import com.company.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {

    UserService userService;

    @GetMapping("get-all")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_SUPER_ADMIN"})
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok().body(userService.getAllUser());
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(userService.findUserById(id));
    }

    @PostMapping("/update-profile/{id}")
    public ResponseEntity<User> updateProfileUser(@RequestBody User user, @PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.updateProfileUser(user, id));
    }

    @PostMapping("/change-password/{id}")
    public ResponseEntity<User> changePasswordUser(@RequestBody ChangePasswordDTO changePasswordDTO,
                                                   @PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.changePassword(changePasswordDTO, id));
    }

    @GetMapping("/find-by-email/{email}")
    public ResponseEntity<User> findByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(userService.findUserByEmail(email));
    }
}
