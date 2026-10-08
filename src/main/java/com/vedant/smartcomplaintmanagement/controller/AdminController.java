package com.vedant.smartcomplaintmanagement.controller;

import com.vedant.smartcomplaintmanagement.model.Admin;
import com.vedant.smartcomplaintmanagement.service.AdminService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {
    "http://localhost:5173",
    "https://smartcomplaintmanagement-frontend.onrender.com"
})
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/register")
    public Admin registerAdmin(@RequestBody Admin admin) {
        return adminService.registerAdmin(admin);
    }

    @PostMapping("/login")
    public Admin loginAdmin(@RequestBody Admin admin) {

        return adminService.loginAdmin(
                admin.getEmail(),
                admin.getPassword()
        );
    }
}