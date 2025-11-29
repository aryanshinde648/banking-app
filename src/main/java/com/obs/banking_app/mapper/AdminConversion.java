package com.obs.banking_app.mapper;

import org.mapstruct.Mapper;

import com.obs.banking_app.dto.AdminDto;
import com.obs.banking_app.entity.Admin;

@Mapper(componentModel = "spring")
public interface AdminConversion {
    Admin toAdmin(AdminDto adminDto);
    AdminDto toAdminDto(Admin admin);

    
}
