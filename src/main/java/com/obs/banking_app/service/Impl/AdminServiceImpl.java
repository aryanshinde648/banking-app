package com.obs.banking_app.service.Impl;

import java.lang.foreign.Linker.Option;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.obs.banking_app.dto.AdminDto;
import com.obs.banking_app.entity.Admin;
import com.obs.banking_app.entity.Customer;
import com.obs.banking_app.mapper.AdminConversion;
import com.obs.banking_app.mapper.CustomerConversion;
import com.obs.banking_app.repository.AdminRepository;
import com.obs.banking_app.service.AdminService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AdminConversion adminConversion;

    public AdminServiceImpl(AdminRepository adminRepository,
            AdminConversion adminConversion) {
        this.adminRepository = adminRepository;
        this.adminConversion = adminConversion;
    }

    @Override
    public ResponseEntity<AdminDto> getAdminById(Long id) {
        log.info("Getting Admin By Id = ",id);
        Admin admin = adminRepository.findById(id).orElseThrow(() -> {
            log.error("Admin Not Found with Id = ",id);
            return new RuntimeException("Admin Not Found with Id = " + id);
        });
        return ResponseEntity.ok(adminConversion.toAdminDto(admin));
    }

    @Override
    public Map<String, Object> createAdmin(AdminDto adminDto) {
        Map<String, Object> response = new HashMap<>();

        try {

            // Validate unique username
            if (adminRepository.findByUsername(adminDto.getUsername()).isPresent()) {
                response.put("success", false);
                response.put("error", "Username already exists");
                return response;
            }

            // Validate unique email
            if (adminRepository.findByEmail(adminDto.getEmail()).isPresent()) {
                response.put("success", false);
                response.put("error", "Email already exists");
                return response;
            }

            // Save the new admin
            Admin admin = adminConversion.toAdmin(adminDto);
            Admin savedAdmin = adminRepository.save(admin);
            response.put("success", true);
            response.put("admin", adminConversion.toAdminDto(savedAdmin));
            
        } catch (Exception e) {
            log.error("Error creating Admin: ", e);
            response.put("success", false);
            response.put("error", "An error occurred while creating the admin");
            
        }

        return response;
    }

    @Override
    public Map<String, Object> updateAdmin(Long id, AdminDto adminDto) {
        Map<String, Object> response = new HashMap<>();

        try {

            log.info("Updating Admin with Id = ",id);
            Admin admin = adminRepository.findById(id).orElseThrow(() -> {
                log.error("Admin Not Found with Id = ",id);
                return new RuntimeException("Admin Not Found with Id = " + id);
            });

            if (adminDto.getUsername() != null && !adminDto.getUsername().isEmpty()) {
                
                Optional<Admin> existingAdmin = adminRepository.findByUsername(adminDto.getUsername());
                if (existingAdmin.isPresent() && !existingAdmin.get().getId().equals(id)) {
                    response.put("success", false);
                    response.put("error", "Username already taken");
                    return response;
                }
                admin.setUsername(adminDto.getUsername());
            }

            if (adminDto.getEmail() != null && !adminDto.getEmail().isEmpty()) {
                
                Optional<Admin> existingAdmin = adminRepository.findByEmail(adminDto.getEmail());
                if (existingAdmin.isPresent() && !existingAdmin.get().getId().equals(id)) {
                    response.put("success", false);
                    response.put("error", "Email already taken");
                    return response;
                }
                admin.setEmail(adminDto.getEmail());
            }

            if (adminDto.getPassword() != null && adminDto.getPassword().isEmpty()) {

                admin.setPassword(adminDto.getPassword());
                
            }

            Admin updatedAdmin = adminRepository.save(admin);
            response.put("success", true);
            response.put("message", "Profile updated successfully");
            response.put("admin", adminConversion.toAdminDto(updatedAdmin));
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", "Failed to update profile: " + e.getMessage());
            log.error("Update error: ", e);
        }
        return response;
    }

    @Override
    public Map<String, Object> deleteAdmin(Long id) {
        Map<String, Object> response = new HashMap<>();

        try {

            log.info("Deleting Admin with Id = ",id);
            Admin admin = adminRepository.findById(id).orElseThrow(() -> {
                log.error("Admin Not Found with Id = ",id);
                return new RuntimeException("Admin Not Found with Id = " + id);
            });

            adminRepository.delete(admin);
            response.put("success", true);
            response.put("message", "Admin deleted successfully");
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", "Failed to delete admin: " + e.getMessage());
            log.error("Delete error: ", e);
        }
        return response;
    }
    

}
