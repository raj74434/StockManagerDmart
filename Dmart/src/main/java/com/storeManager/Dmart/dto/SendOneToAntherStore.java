package com.storeManager.Dmart.dto;

import lombok.Data;

@Data
public class SendOneToAntherStore {

     private Integer senderId;
     private Integer reciverId;
     private Integer productId;
     private Integer productQuantity;


}
