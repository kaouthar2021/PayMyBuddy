package com.project.model;

import jakarta.persistence.*;

@Entity
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_count_bank")
    private Long idCountBank;

    @Column(name = "iban")
    private String iban;
    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public BankAccount() {
    }


    public BankAccount( String iban,String description) {
        this.iban = iban;
        this.description = description;
    }


    public BankAccount(Long idCountBank, String iban,String description ) {
        this.idCountBank = idCountBank;
        this.iban = iban;
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getIdCountBank() {
        return idCountBank;
    }

    public void setIdCountBank(Long idCountBank) {
        this.idCountBank = idCountBank;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getDescription() {
        return this.description ;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
