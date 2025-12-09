package com.obs.banking_app.mapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.obs.banking_app.dto.TransactionDto;
import com.obs.banking_app.entity.Transaction;

@Mapper(componentModel = "spring")
public interface TransactionConversion {
    
    @Mapping(target = "transactionId", source = "transactionId")
    @Mapping(target = "transactionType", source = "transactionType")
    @Mapping(target = "timestamp", source = "timestamp", qualifiedByName = "localDateTimeToTimestamp")
    @Mapping(target = "status", source = "status")
    Transaction toTransaction(TransactionDto transactionDto);
    
    @Mapping(target = "transactionId", source = "transactionId")
    @Mapping(target = "transactionType", source = "transactionType")
    @Mapping(target = "timestamp", source = "timestamp", qualifiedByName = "timestampToLocalDateTime")
    @Mapping(target = "status", source = "status")
    TransactionDto toTransactionDto(Transaction transaction);

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
