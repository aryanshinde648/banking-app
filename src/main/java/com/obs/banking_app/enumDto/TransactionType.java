package com.obs.banking_app.enumDto;

public enum TransactionType
{
    DEPOSIT("DEPOSIT"),
    WITHDRAWAL("WITHDRAWAL"),
    TRANSFER("TRANSFER");

    private final String displayType;

    TransactionType(String displayType) {
        this.displayType = displayType;
    }

    public String getDisplayType() {
        return displayType;
    }

    @Override
    public String toString() {
        return displayType;
    }
}
