package com.storeManager.Dmart.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer storeId;

    private String storeName;

    private String storeAddress;


    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "store")
    private List<StockProduct> stock=new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL)
    private List<Transection> transection_between_store=new ArrayList<>();



    public void add_Transection_between_store(Transection transection_between_store) {
        this.transection_between_store.add(transection_between_store);
    }



    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }



    public List<Transection> getTransection_between_store() {
        return transection_between_store;
    }

    public void setTransection_between_store(List<Transection> transection_between_store) {
        this.transection_between_store = transection_between_store;
    }

    public List<StockProduct> getStock() {
        return stock;
    }

    public void setStock(List<StockProduct> stock) {
        this.stock = stock;
    }
}
