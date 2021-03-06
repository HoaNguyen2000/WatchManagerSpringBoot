package com.company.services;

import com.company.dto.ChangePasswordDTO;
import com.company.entity.User;
import com.company.exception.BadRequestException;
import com.company.exception.ErrorParam;
import com.company.exception.Errors;
import com.company.exception.ResourceNotFoundExeption;
import com.company.exception.SysError;
import com.company.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<User> getAllUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundExeption("Cant not found the user")
        );
    }

    @Override
    @Transactional
    public User updateProfileUser(User user, Long id) {
        User userUpdate = findUserById(id);
        userUpdate.setEmail(user.getEmail());
        userUpdate.setName(user.getName());
        userUpdate.setPhone(user.getPhone());
        return userRepository.save(userUpdate);
    }

    @Override
    @Transactional
    public User changePassword(ChangePasswordDTO changePasswordDTO, Long id) {
        if(!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPassword())){
            throw new BadRequestException(
                    new SysError(Errors.PASSWORD_NOT_MATCH, new ErrorParam(Errors.PASSWORD)));
        }
        User user = findUserById(id);

        if(!passwordEncoder.matches(changePasswordDTO.getPassword(), user.getPassword())){
            throw new BadRequestException(
                    new SysError(Errors.PASSWORD_NOT_CORRECT, new ErrorParam(Errors.PASSWORD)));
        }
        user.setPassword(passwordEncoder.encode(changePasswordDTO.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        if(!userRepository.existsByEmail(email)){
            throw new BadRequestException(
                    new SysError(Errors.ERROR_EMAIL_NOT_FOUND, new ErrorParam(Errors.EMAIL)));
        }
        //TODO: return DTO ID USER
        return userRepository.findByEmail(email);
    }
}
