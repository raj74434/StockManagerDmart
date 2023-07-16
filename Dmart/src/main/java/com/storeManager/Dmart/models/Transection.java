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
    private Store sender_store;
    @ManyToOne
    private Store reciver_store;

    @OneToOne
    private Product products_sending;

    private Integer total_amount_of_products;




}
