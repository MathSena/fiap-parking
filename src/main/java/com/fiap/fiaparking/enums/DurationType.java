package com.fiap.fiaparking.enums;

public enum DurationType {
    FIXED("Fixed Period"),
    VARIABLE("Per Hour");

    private final String description;

    DurationType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
