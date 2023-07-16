package com.storeManager.Dmart.Services;

import com.storeManager.Dmart.dto.StockProductDTO;
import com.storeManager.Dmart.dto.StoreDTO;
import com.storeManager.Dmart.dto.StoreProductQuantity;
import com.storeManager.Dmart.exceptions.ProductException;
import com.storeManager.Dmart.exceptions.StoreException;
import com.storeManager.Dmart.models.Product;
import com.storeManager.Dmart.models.StockProduct;
import com.storeManager.Dmart.models.Store;
import com.storeManager.Dmart.models.Transection;
import com.storeManager.Dmart.repositories.ProductRepo;
import com.storeManager.Dmart.repositories.StockProductRepo;
import com.storeManager.Dmart.repositories.StoreRepo;
import com.storeManager.Dmart.repositories.TransectionRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StoreRepo storeRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StockProduct stockProduct;

    @Autowired
    private Transection transection;

    @Autowired
    private TransectionRepo transectionRepo;


    @Autowired
    private StockProductRepo stockProductRepo;


     @Override
     public List<StockProduct> add_new_product_in_store_stock(StockProductDTO stockProductDTO){
        Store store=check_store(stockProductDTO.getStoreId());
        Product product=check_product(stockProductDTO.getProductId());
        StockProduct stockProduct=  convert_stockProductDTO_into_stockProduct(stockProductDTO,product,store);
        List<StockProduct> allStock=store.getStock();
        for(int x=0;x<allStock.size();x++){
             if(allStock.get(x).getProduct().getProductId()==stockProductDTO.getProductId()){
                 throw  new ProductException("This product already in stock");
             }
         }

        allStock.add(stockProduct);
        store.setStock(allStock);
        storeRepo.save(store);
        return allStock;
     }


    @Override
     public List<StockProduct> increase_stock_product_quantity(StockProductDTO stockProductDTO){
         Store store=check_store(stockProductDTO.getStoreId()); //checking store is available with this id or not
         Product product=check_product(stockProductDTO.getProductId());//checking product is available with this id or not

         List<StockProduct> allStock=store.getStock();

         boolean isPresent=false;
         for(int x=0;x<allStock.size();x++){
             StockProduct stockProductTemp=allStock.get(x);
             if(stockProductTemp.getStock_product_id()==stockProductDTO.getProductId()){
                 stockProductTemp.setQuantity(stockProductTemp.getQuantity()+stockProductDTO.getQuantity());
                 isPresent=true;
                 break;
             }
         }
         if(isPresent==false) throw new StoreException("No product is in stock with "+stockProductDTO.getProductId()+" id");
         else{ store.setStock(allStock);
             storeRepo.save(store);}
         return store.getStock();

     }



    @Override
    public List<StockProduct> decrease_stock_product_quantity(StockProductDTO stockProductDTO){
        Store store=check_store(stockProductDTO.getStoreId());           //checking store is available with this id or not
        Product product=check_product(stockProductDTO.getProductId());   //checking product is available with this id or not

        List<StockProduct> allStock=store.getStock();

        boolean isPresent=false;
        for(int x=0;x<allStock.size();x++){
            StockProduct stockProductTemp=allStock.get(x);
            if(stockProductTemp.getProduct().getProductId()==stockProductDTO.getProductId()){
                if(stockProductTemp.getQuantity()>= stockProductDTO.getQuantity() ) {
                    stockProductTemp.setQuantity(stockProductTemp.getQuantity() - stockProductDTO.getQuantity());
                }
                else throw new ProductException("Product quantity is not available in your stock");

                isPresent=true;
                break;
            }
        }
        if(isPresent==false) throw new StoreException("No product is in stock with "+stockProductDTO.getProductId()+" id");
        else{ store.setStock(allStock);
            storeRepo.save(store);
        }
        return store.getStock();
    }



    @Override
    public List<StockProduct> replace_old_stock_product_quantity(StockProductDTO stockProductDTO){
        Store store=check_store(stockProductDTO.getStoreId()); //checking store is available with this id or not
        Product product=check_product(stockProductDTO.getProductId());//checking product is available with this id or not

        List<StockProduct> allStock=store.getStock();

        boolean isPresent=false;
        for(int x=0;x<allStock.size();x++){
            StockProduct stockProductTemp=allStock.get(x);
            if(stockProductTemp.getProduct().getProductId()==stockProductDTO.getProductId()){
                stockProductTemp.setQuantity(stockProductDTO.getQuantity());
                isPresent=true;
                break;
            }
        }
        if(isPresent==false) throw new StoreException("No product is in stock with "+stockProductDTO.getProductId()+" id");
        else {store.setStock(allStock);
        storeRepo.save(store);
        }
        return store.getStock();
    }


    @Override
    public List<StockProduct> delete_single_product_from_stock(Integer storeId,Integer productId){
        Store store=check_store(storeId);
        List<StockProduct> stock=store.getStock();
        boolean isPresent=false;
        for(int x=0;x<stock.size();x++){
            StockProduct stockProduct=stock.get(x);
            if(stockProduct.getProduct().getProductId()==productId){
               stock.remove(x);
                isPresent=true;
                break;
            }
        }
        if(isPresent==false) throw new StoreException("No product is in stock with "+productId+" id");

            store.setStock(stock);
             storeRepo.save(store);

        return store .getStock();
    }

    @Override
    @Transactional
    public Transection send_item_from_one_store_to_another
            (Integer senderId,Integer reciverId, Integer productId,Integer productQuantity){
        Product item=check_product(productId);  //we recive product object from this
        Store senderStore=check_store(senderId); //recive sender object from this
        Store reciverStore=check_store(reciverId); //recive reciver object from this

        boolean isProductPresent=check_product_available_in_stock(productId,senderStore.getStock());
        boolean isQuantityAvailable=check_product_quantity( productId, productQuantity,senderStore.getStock());

        if( isProductPresent){
            if(isQuantityAvailable){


                List<StockProduct> reciverUpdatedStock=  increase_or_insert_stockProduct_and_quantity(productId,productQuantity,reciverStore);
                List<StockProduct> senderUpdatedStock = drecrese_or_delete_stockProduct_and_quantity(productId,productQuantity,senderStore.getStock());


                for(int x=0;x<senderUpdatedStock.size();x++){
                    System.out.println(senderUpdatedStock.get(x).getQuantity());
                }
                reciverStore.setStock(reciverUpdatedStock);
                senderStore.setStock(senderUpdatedStock);

                storeRepo.save(reciverStore);
                System.out.println("reciver updated");
                System.out.println(senderUpdatedStock.size());
                storeRepo.save(senderStore);


                transection.setReciver_store(reciverStore);
                transection.setSender_store(senderStore);
                transection.setProducts_sending(item);
                transection.setDateTime_Of_sending(LocalDateTime.now());
                transection.setTotal_amount_of_products(productQuantity*item.getMarket_price());

                return transectionRepo.save(transection);

            }
            else throw new ProductException("Quantity is not sufficient in your stock");

        }
        else throw new ProductException("This product is not present in your stock. Please check productId.");

//
    }



    public List<StoreProductQuantity> all_stock_of_each_store(){
         List<StockProduct> stock=stockProductRepo.findAll();

        Map<Integer,Integer> map=new HashMap<>();

        for(int x=0;x<stock.size();x++){
            Integer storeId=stock.get(x).getStore().getStoreId();
            if(map.containsKey(storeId)){
                map.put(storeId,map.get(storeId)+stock.get(x).getQuantity());
            }
            else  map.put(storeId,stock.get(x).getQuantity());
        }

        List<Store> allStore=storeRepo.findAll();

        List<StoreProductQuantity> allStoreProductQuantities=new ArrayList<>();

        for(int x=0;x<allStore.size();x++){
            allStoreProductQuantities.add(new StoreProductQuantity(allStore.get(x).getStoreId(), map.get(allStore.get(x).getStoreId())));
        }
         return allStoreProductQuantities;
    }





//    All supportive fuction start from here---------------------------------------------------

     public Store check_store(Integer storeId) {
         return storeRepo.findById(storeId).orElseThrow(() -> new StoreException("No store found with "+storeId+" id") );

     }

     public Product check_product(Integer productId){
         return productRepo.findById(productId).orElseThrow(()->new ProductException("No store found with "+productId + "id"));

     }

     public StockProduct convert_stockProductDTO_into_stockProduct(StockProductDTO stockProductDTO,Product product,Store store){
         stockProduct.setProduct(product);
         stockProduct.setStore(store);
         stockProduct.setQuantity(stockProductDTO.getQuantity());
         stockProduct.setDate_of_enter(LocalDate.now());
         stockProduct.setLastUpdatedDateTime(LocalDateTime.now());
         return stockProduct;
     }


     public boolean check_product_available_in_stock(Integer productId,List<StockProduct> stockProduct){
         for(int x=0;x<stockProduct.size();x++){
             if(stockProduct.get(x).getProduct().getProductId()==productId){
                 return true;
             }
         }
        return false;
     }

     public boolean check_product_quantity(Integer productId,Integer quantity,List<StockProduct> stock){
         for(int x=0;x<stock.size();x++){
             if(stock.get(x).getProduct().getProductId()==productId
                     && stock.get(x).getQuantity()>=quantity){
                 return true;
             }
         }
         return false;
     }

     public List<StockProduct> increase_or_insert_stockProduct_and_quantity(Integer productId,Integer quantity,Store store){
         boolean isPresent=false;
         List<StockProduct> stock=store.getStock();
         for(int x=0;x<stock.size();x++){
             if(stock.get(x).getProduct().getProductId() == productId){
                 stock.get(x).setQuantity(stock.get(x).getQuantity()+quantity);
                 isPresent= true;
                 break;
             }
         }
         if(isPresent==false){
             stockProduct.setStore(store);
             stockProduct.setQuantity(quantity);
             stockProduct.setProduct(check_product(productId));
             stockProduct.setDate_of_enter(LocalDate.now());
             stock.add(stockProduct);
         }
         return stock;
     }


     public List<StockProduct> drecrese_or_delete_stockProduct_and_quantity(Integer productId,Integer quantity,List<StockProduct> stock){

         for(int x=0;x<stock.size();x++){
             if(stock.get(x).getProduct().getProductId()==productId && stock.get(x).getQuantity()>quantity){
                 stock.get(x).setQuantity(stock.get(x).getQuantity()-quantity);
                 return stock;
             }
             else if(stock.get(x).getProduct().getProductId()==productId && stock.get(x).getQuantity()==quantity){
                 System.out.println("yes");
                 stock.remove(x);
                 return stock;
             }

         }
           throw new StoreException("Indufficient stock quantity");
     }

}
