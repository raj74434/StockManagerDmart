package com.storeManager.Dmart.services;


import com.storeManager.Dmart.dto.StockProductDTO;
import com.storeManager.Dmart.dto.StoreProductQuantity;
import com.storeManager.Dmart.models.StockProduct;
import com.storeManager.Dmart.models.Transection;

import java.util.List;

public interface StockService {

    //These services availables in implementaion of this interface
    public List<StockProduct> add_new_product_in_store_stock(StockProductDTO stockProductDTO);

    public List<StockProduct> increase_stock_product_quantity(StockProductDTO stockProductDTO);

    public List<StockProduct> decrease_stock_product_quantity(StockProductDTO stockProductDTO);

    public List<StockProduct> replace_old_stock_product_quantity(StockProductDTO stockProductDTO);

    public List<StockProduct> delete_single_product_from_stock(Integer storeId,Integer productId);

    public Transection send_item_from_one_store_to_another
            (Integer senderId,Integer reciverId, Integer productId,Integer productQuantity);

    public List<StoreProductQuantity> all_stock_of_each_store();

}
