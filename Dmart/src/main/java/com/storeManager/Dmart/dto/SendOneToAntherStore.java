package com.storeManager.Dmart.dto;

import lombok.Data;

// It help in send store product fron one store to another by taking input from user.
@Data
public class SendOneToAntherStore {

     private Integer senderId;
     private Integer reciverId;
     private Integer productId;
     private Integer productQuantity;


}
