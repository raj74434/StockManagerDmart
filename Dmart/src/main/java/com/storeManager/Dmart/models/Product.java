package com.storeManager.Dmart.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data  //Lombok
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;        //unique productId which generate automatically

    private String productName;       // Name of product
    private String productCompany;    // Name of product company

    private Integer market_price;     //MRP of product

    private Integer sales_price;      //Price on which dmart is selling





}
