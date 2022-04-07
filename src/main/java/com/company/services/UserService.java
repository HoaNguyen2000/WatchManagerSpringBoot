package com.company.services;

import com.company.dto.ChangePasswordDTO;
import com.company.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();

    User findUserById(Long id);

    User updateProfileUser(User user, Long id);

    User changePassword(ChangePasswordDTO changePasswordDTO, Long id);

    User findUserByEmail(String email);
}
