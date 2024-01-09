package com.project.dto;

public class TransactionDto {
    private String connection;
    private String description;
    private Double amount;
    public TransactionDto() {

    }

    public TransactionDto(String connection, String description,  Double amount) {
        this.connection = connection;
        this.description = description;
        this.amount = amount;
    }


    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
