package com.obs.banking_app.enumDto;

public enum AccountType {
    SAVINGS("SAVINGS"),
    BUSINESS("BUSINESS");

    private final String displayType;

    AccountType(String displayType) {
        this.displayType = displayType;
    }

    public String getType() {
        return displayType;
    }

    public String getDisplayType() {
        return displayType;
    }

    @Override
    public String toString() {
        return displayType;
    }
}
