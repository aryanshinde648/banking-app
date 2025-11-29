package com.obs.banking_app.mapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.obs.banking_app.dto.AccountDto;
import com.obs.banking_app.entity.Account;

@Mapper(componentModel = "spring")
public interface AccountConversion {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "accountOpenedAt", source = "accountOpenedAt", qualifiedByName = "localDateTimeToTimestamp")
    Account toAccount(AccountDto accountDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "accountOpenedAt", source = "accountOpenedAt", qualifiedByName = "timestampToLocalDateTime")
    AccountDto toAccountDto(Account account);

    // Custom mapping methods for date conversion
    @org.mapstruct.Named("timestampToLocalDateTime")
    default LocalDateTime timestampToLocalDateTime(Timestamp timestamp) {
        return timestamp != null ? timestamp.toLocalDateTime() : null;
    }

    @org.mapstruct.Named("localDateTimeToTimestamp")
    default Timestamp localDateTimeToTimestamp(LocalDateTime localDateTime) {
        return localDateTime != null ? Timestamp.valueOf(localDateTime) : null;
    }
    
}
