package com.obs.banking_app.enumDto;

public enum AdminRole {
    MANAGER("MANAGER"),
    CASHIER("CASHIER"),
    ADMIN("ADMIN");

    private final String displayRole;

    AdminRole(String displayRole) {
        this.displayRole = displayRole;
    }

    public String getRole() {
        return displayRole;
    }

    public String getDisplayRole() {
        return displayRole;
    }

    @Override
    public String toString() {
        return displayRole;
    }
}