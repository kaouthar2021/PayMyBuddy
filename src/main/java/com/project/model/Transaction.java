package com.project.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id_sender")
    private User userSender;
    @ManyToOne
    @JoinColumn(name = "user_id_receiver")
    private User userReceiver;


    @Column(name = "transaction_date")
    private Date transaction_date;

    @Column(name = "amount")
    private Double amount;
    @Column(name = "description")
    private String description;
    public Transaction() {

    }

    public Transaction(int id, User userSender, User userReceiver, Date transaction_date, Double amount, String description) {
        this.id = id;
        this.userSender = userSender;
        this.userReceiver = userReceiver;
        this.transaction_date = transaction_date;
        this.amount = amount;
        this.description = description;
    }

    public Transaction(User userSender, User userReceiver, Date transaction_date, Double amount, String description) {
        this.userSender = userSender;
        this.userReceiver = userReceiver;
        this.transaction_date = transaction_date;
        this.amount = amount;
        this.description = description;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUserSender() {
        return userSender;
    }

    public void setUserSender(User userSender) {
        this.userSender = userSender;
    }

    public User getUserReceiver() {
        return userReceiver;
    }

    public void setUserReceiver(User userReceiver) {
        this.userReceiver = userReceiver;
    }
    public Date getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(Date transaction_date) {
        this.transaction_date = transaction_date;
    }


    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
