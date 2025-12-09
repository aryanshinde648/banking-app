package com.obs.banking_app.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.obs.banking_app.dto.AdminDto;

public interface AdminService {

    ResponseEntity<AdminDto> getAdminById(Long id);
    Map<String, Object> createAdmin(AdminDto adminDto);
    Map<String, Object> updateAdmin(Long id, AdminDto adminDto);
    Map<String, Object> deleteAdmin(Long id);

}
