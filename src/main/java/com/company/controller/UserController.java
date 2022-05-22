package com.company.controller;

import com.company.dto.ChangePasswordDTO;
import com.company.entity.User;
import com.company.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3006")
@Secured({"ROLE_SUPER_ADMIN", "ROLE_ADMIN"})
public class UserController {

    UserService userService;

    @GetMapping("get-all")
    @RolesAllowed({"ROLE_SUPER_ADMIN"})
    public ResponseEntity<Page<User>> getAllUser(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "3") int size) {
        Pageable paging = PageRequest.of(page, size);
        return ResponseEntity.ok().body(userService.getAllUser(paging));
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(userService.findUserById(id));
    }

    @PutMapping("/update-profile/{id}")
    public ResponseEntity<User> updateProfileUser(@RequestBody User user, @PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.updateProfileUser(user, id));
    }

    @PutMapping("/change-password/{id}")
    public ResponseEntity<User> changePasswordUser(@RequestBody ChangePasswordDTO changePasswordDTO,
                                                   @PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.changePassword(changePasswordDTO, id));
    }

    @GetMapping("/find-by-email/{email}")
    public ResponseEntity<User> findByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(userService.findUserByEmail(email));
    }
}
