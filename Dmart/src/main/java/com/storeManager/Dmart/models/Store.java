package com.storeManager.Dmart.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//  All details of store
@Data
@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer storeId;

    private String storeName;

    private String storeAddress;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "store")
    private List<StockProduct> stock=new ArrayList<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Transection> transection_between_store=new ArrayList<>();



    public void add_Transection_between_store(Transection transection_between_store) {
        this.transection_between_store.add(transection_between_store);
    }
}
