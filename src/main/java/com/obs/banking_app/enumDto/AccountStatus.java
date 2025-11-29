package com.obs.banking_app.enumDto;

public enum AccountStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    CLOSED("CLOSED");

    private final String displayStatus;

    AccountStatus(String displayStatus) {
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
