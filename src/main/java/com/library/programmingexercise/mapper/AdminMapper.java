package com.library.programmingexercise.mapper;

import com.library.programmingexercise.dto.AdminDto;
import com.library.programmingexercise.entity.Admin;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AdminMapper {
    public static AdminDto maptoAdminDto(Admin admin) {
        AdminDto adminDto = new AdminDto();
        adminDto.setId(admin.getId());
        adminDto.setFirstName(admin.getFirstName());
        adminDto.setLastName(admin.getLastName());
        adminDto.setPhoneNumber(admin.getPhoneNumber());
        adminDto.setAddress(admin.getAddress());
        adminDto.setEmail(admin.getEmail());
        adminDto.setUsername(admin.getUsername());
        adminDto.setPassword(admin.getPassword());
        adminDto.setGender(admin.getGender());

        // Convert byte array image to Base64
        byte[] imageBytes = admin.getImage();
        if (imageBytes != null) {
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            adminDto.setImage(base64Image);
        }

        return adminDto;
    }

    public static Admin maptoAdmin(AdminDto admindto) {
        return new Admin(
                admindto.getId(),
                admindto.getFirstName(),
                admindto.getLastName(),
                admindto.getPhoneNumber(),
                admindto.getAddress(),
                admindto.getEmail(),
                admindto.getUsername(),
                admindto.getPassword(),
                admindto.getGender(),
                Base64.getEncoder().encode(admindto.getImage().getBytes(StandardCharsets.UTF_8))
        );
    }
}
