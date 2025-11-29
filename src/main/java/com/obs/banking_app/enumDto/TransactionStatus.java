package com.obs.banking_app.enumDto;

public enum TransactionStatus {
    PENDING("PENDING"),
    COMPLETED("COMPLETED"),
    FAILED("FAILED");

    private final String displayStatus;

    TransactionStatus(String displayStatus) {
        this.displayStatus = displayStatus;
    }

    public String getStatus() {
        return displayStatus;
    }

    public String getDisplayStatus() {
        return displayStatus;
    }

    @Override
    public String toString() {
        return displayStatus;
    }
}

