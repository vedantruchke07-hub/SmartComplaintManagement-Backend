package com.vedant.smartcomplaintmanagement.service;

import com.vedant.smartcomplaintmanagement.model.Admin;
import com.vedant.smartcomplaintmanagement.repository.AdminRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin registerAdmin(Admin admin) {

        if (adminRepository.existsByEmail(admin.getEmail())) {
            throw new RuntimeException(
                    "Administrator with this email already exists"
            );
        }

        String hashedPassword =
                passwordEncoder.encode(admin.getPassword());

        admin.setPassword(hashedPassword);

        return adminRepository.save(admin);
    }

    public Admin loginAdmin(String email, String password) {

        Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Invalid email or password"
                        )
                );

        if (!passwordEncoder.matches(
                password,
                admin.getPassword()
        )) {
            throw new RuntimeException(
                    "Invalid email or password"
            );
        }

        return admin;
    }
}