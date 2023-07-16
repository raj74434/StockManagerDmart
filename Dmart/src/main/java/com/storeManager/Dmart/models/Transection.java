package com.storeManager.Dmart.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Transection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transectionId;

    private LocalDateTime dateTime_Of_sending;

    @ManyToOne
    @JsonIgnore
    private Store sender_store;
    @ManyToOne
    @JsonIgnore
    private Store reciver_store;

    @OneToOne
    @JsonIgnore
    private Product products_sending;

    private Integer total_amount_of_products;

    public Integer getTransectionId() {
        return transectionId;
    }

    public void setTransectionId(Integer transectionId) {
        this.transectionId = transectionId;
    }

    public LocalDateTime getDateTime_Of_sending() {
        return dateTime_Of_sending;
    }

    public void setDateTime_Of_sending(LocalDateTime dateTime_Of_sending) {
        this.dateTime_Of_sending = dateTime_Of_sending;
    }

    public Store getSender_store() {
        return sender_store;
    }

    public void setSender_store(Store sender_store) {
        this.sender_store = sender_store;
    }

    public Store getReciver_store() {
        return reciver_store;
    }

    public void setReciver_store(Store reciver_store) {
        this.reciver_store = reciver_store;
    }

    public Product getProducts_sending() {
        return products_sending;
    }

    public void setProducts_sending(Product products_sending) {
        this.products_sending = products_sending;
    }

    public Integer getTotal_amount_of_products() {
        return total_amount_of_products;
    }

    public void setTotal_amount_of_products(Integer total_amount_of_products) {
        this.total_amount_of_products = total_amount_of_products;
    }
}
