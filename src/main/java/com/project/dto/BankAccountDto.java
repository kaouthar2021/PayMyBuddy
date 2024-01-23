package com.project.dto;

public class BankAccountDto {
    private String iban;

    private String description;

    private int userId;

    public BankAccountDto(String iban, String description, int userId) {
        this.iban = iban;
        this.description = description;
        this.userId = userId;
    }

    public BankAccountDto() {

    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
