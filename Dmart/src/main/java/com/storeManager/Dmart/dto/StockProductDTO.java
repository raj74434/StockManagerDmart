package com.storeManager.Dmart.dto;

import lombok.Data;

//  It help in update stock by taking input from user
@Data
public class StockProductDTO {

    private Integer storeId;

    private Integer productId;

    private Integer quantity;



}
