package com.storeManager.Dmart.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class StockProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stock_product_id;

    @JsonIgnore
    @ManyToOne
    private Store store;

    @JsonIgnore
    @OneToOne
    private Product product;

    private Integer quantity;

    private LocalDateTime lastUpdatedDateTime;

    private LocalDate date_of_enter;




    public Integer getStock_product_id() {
        return stock_product_id;
    }

    public void setStock_product_id(Integer stock_product_id) {
        this.stock_product_id = stock_product_id;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getLastUpdatedDateTime() {
        return lastUpdatedDateTime;
    }

    public void setLastUpdatedDateTime(LocalDateTime lastUpdatedDateTime) {
        this.lastUpdatedDateTime = lastUpdatedDateTime;
    }

    public LocalDate getDate_of_enter() {
        return date_of_enter;
    }

    public void setDate_of_enter(LocalDate date_of_enter) {
        this.date_of_enter = date_of_enter;
    }
}
