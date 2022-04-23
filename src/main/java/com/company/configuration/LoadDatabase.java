package com.company.configuration;

import com.company.common.enums.RoleEnum;
import com.company.entity.Brands;
import com.company.entity.Role;
import com.company.entity.User;
import com.company.repository.BrandRepository;
import com.company.repository.RoleRepository;
import com.company.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Log4j2
@Configuration
public class LoadDatabase {
    @Value("${support.database.preloading}")
    private Boolean reload;

    @Bean
    CommandLineRunner initDatabase(RoleRepository roleRepository,
                                   UserRepository userRepository,
                                   BrandRepository brandRepository,
                                   PasswordEncoder passwordEncoder) {
        return args -> {
            if (!reload) {
                return;
            }
            log.info("Preloading Role");
            Role roleUser = new Role(1, RoleEnum.ROLE_USER);
            Role roleAdmin = new Role(2, RoleEnum.ROLE_ADMIN);
            Role roleSuperAdmin = new Role(3, RoleEnum.ROLE_SUPER_ADMIN);
            List<Role> roles = Arrays.asList(
                    roleUser,
                    roleAdmin,
                    roleSuperAdmin
            );
            roleRepository.saveAll(roles);

            //**************************

            log.info("Preloading USER: SUPER_ADMIN");
            Optional<User> sAdminUser = userRepository.findByUsername("super_admin");
            User sAdminUserNew;
            if (sAdminUser.isEmpty()) {
                sAdminUserNew = User.builder()
                        .username("super_admin")
                        .email("superadmin_cdp@tma.com.vn")
                        .name("superadmin")
                        .phone("0123456789")
                        .build();
                // Role
                sAdminUserNew.setRoles(Set.of(roleSuperAdmin, roleAdmin));
                // Password
                sAdminUserNew.setPassword(passwordEncoder.encode("12345678x@X"));
                userRepository.save(sAdminUserNew);
            } else {
                sAdminUserNew = sAdminUser.get();
            }

            //**************************

            log.info("Preloading USER: ADMIN");
            Optional<User> adminDefault = userRepository.findByUsername("admin");
            User adminNew;
            if (adminDefault.isEmpty()) {
                adminNew = User.builder()
                        .username("admin")
                        .email("admin_cdp@tma.com.vn")
                        .name("admin")
                        .phone("0123456789")
                        .build();
                // Role
                adminNew.setRoles(Set.of(roleAdmin));
                // Password
                adminNew.setPassword(passwordEncoder.encode("12345678x@X"));
                userRepository.save(adminNew);
            } else {
                adminNew = adminDefault.get();
            }

            //**************************

            log.info("Preloading USER: USER");
            Optional<User> userDefault = userRepository.findByUsername("admin");
            User userNew;
            if (adminDefault.isEmpty()) {
                userNew = User.builder()
                        .username("user")
                        .email("user_cdp@tma.com.vn")
                        .name("user")
                        .phone("0123456789")
                        .build();
                // Role
                userNew.setRoles(Set.of(roleUser));
                // Password
                userNew.setPassword(passwordEncoder.encode("12345678x@X"));
                userRepository.save(userNew);
            } else {
                userNew = adminDefault.get();
            }
            //**************************

            log.info("Preloading BRAND: Samsung, Apple, Huawei, Oppo");
            Brands samsung = Brands.builder()
                    .name("Samsung")
                    .slug("samsung")
                    .build();
            Brands apple = Brands.builder()
                    .name("Apple")
                    .slug("apple")
                    .build();
            Brands huawei = Brands.builder()
                    .name("Huawei")
                    .slug("huawei")
                    .build();
            Brands oppo = Brands.builder()
                    .name("Oppo")
                    .slug("oppo")
                    .build();
            List<Brands> brands = Arrays.asList(
                    samsung,
                    apple,
                    huawei,
                    oppo);
            brandRepository.saveAll(brands);
        };
    }
}
