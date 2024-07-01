package com.library.programmingexercise.service.impl;

import com.library.programmingexercise.dto.AdminDto;
import com.library.programmingexercise.entity.Admin;
import com.library.programmingexercise.mapper.AdminMapper;
import com.library.programmingexercise.repository.AdminRepository;
import com.library.programmingexercise.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.Optional;

@Service
@AllArgsConstructor
// This is the implementation of the AdminService interface
public class AdminServiceImpl implements AdminService {
    private AdminRepository adminRepository;

    public AdminDto createAdmin(AdminDto adminDto) {
        Admin admin = AdminMapper.maptoAdmin(adminDto);
        Admin savedAdmin = adminRepository.save(admin);

        return AdminMapper.maptoAdminDto(savedAdmin);
    }

    @Override
    public boolean confirmEmail(String email) {
        AdminDto adminDto = findByEmail(email);
        return adminDto != null;
    }

    @Override
    public void updatePassword(String email, String newPassword) {
        AdminDto admin = findByEmail(email);
        admin.setPassword(newPassword);
        adminRepository.save(AdminMapper.maptoAdmin(admin));
    }


    @Override
    public AdminDto findByEmail(String email) {
        Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Admin not found with email: " + email));

        // Assuming you have a method to map Admin entity to AdminDto
        return AdminMapper.maptoAdminDto(admin);
    }

    @Override
    public AdminDto authenticate(String email, String password) throws AuthenticationException {
        // Find admin by email
        Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(() -> new AuthenticationException("User not found with email: " + email));

        // Validate password (simple comparison)
        if (!password.equals(admin.getPassword())) {
            throw new AuthenticationException("Invalid password");
        }

        // Map Admin entity to AdminDto and return
        return AdminMapper.maptoAdminDto(admin);
    }

    public AdminDto saveImage(int adminID, byte[] imageData) {
        // Retrieve the book entity by ID
        Optional<Admin> adminOptional = adminRepository.findById(adminID);
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            admin.setImage(imageData);
            adminRepository.save(admin);

            return AdminMapper.maptoAdminDto(admin);
        } else {
            throw new RuntimeException("Admin not found with ID: " + adminID);
        }
    }
}
