package com.storeManager.Dmart.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Transection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transectionId;

    private LocalDateTime dateTime_Of_sending;

    @ManyToOne
    private Store sender_store;      // from which store product is sending
    @ManyToOne
    private Store reciver_store;     // which store is reciving product

    @OneToOne
    private Product products_sending; //Product which is sending

    private Integer total_amount_of_products;   //total quantity which sending




}
