package com.company.services;

import com.company.dto.ChangePasswordDTO;
import com.company.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    Page<User> getAllUser(Pageable pageable);

    User findUserById(Long id);

    User updateProfileUser(User user, Long id);

    User changePassword(ChangePasswordDTO changePasswordDTO, Long id);

    User findUserByEmail(String email);
}
