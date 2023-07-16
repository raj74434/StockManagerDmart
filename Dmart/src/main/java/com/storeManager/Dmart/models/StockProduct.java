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

    @JsonIgnore
    @ManyToOne
    private Store store;

    @JsonIgnore
    @OneToOne
    private Product product;

    private Integer quantity;

    private LocalDateTime lastUpdatedDateTime;

    private LocalDate date_of_enter;




}
