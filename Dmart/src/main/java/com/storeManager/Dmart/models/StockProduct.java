package com.storeManager.Dmart.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class StockProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stock_product_id;

//    @JsonIgnore
    @ManyToOne
    private Store store;             //Store at which this product is present

    @JsonIgnore
    @OneToOne
    private Product product;        //about product

    private Integer quantity;      // at  which quantity this product is present

    private LocalDateTime lastUpdatedDateTime;   // at which time it is updated  last

    private LocalDate date_of_enter;    // when this product get  available or inserted  in stock




}
