package com.storeManager.Dmart.services;

import com.storeManager.Dmart.dto.StockProductDTO;
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


//    This method add a new product in stock of store and return list of stock which is available in stock of that store.
     @Override
     public List<StockProduct> add_new_product_in_store_stock(StockProductDTO stockProductDTO){
        Store store=check_store(stockProductDTO.getStoreId()); //checking is any store available with this storeid.
        Product product=check_product(stockProductDTO.getProductId());  //checking is any product available with this productId
        StockProduct stockProduct=  convert_stockProductDTO_into_stockProduct(stockProductDTO,product,store);  //converting from dto to aactual class
        List<StockProduct> allStock=store.getStock(); //fetching stock of that store

//      This loop check if this product already available in stock then throw a exception
        for(int x=0;x<allStock.size();x++){
             if(allStock.get(x).getProduct().getProductId()==stockProductDTO.getProductId()){
                 throw  new ProductException("This product already in stock");
             }
         }


//      If product is not available in stock then add it.
        allStock.add(stockProduct);
        store.setStock(allStock);
        storeRepo.save(store);
        return allStock;
     }


//    This method increase product quantity which is already available in stock.
//    If product is not available in stock then it will throw a exception
    @Override
     public List<StockProduct> increase_stock_product_quantity(StockProductDTO stockProductDTO){
         Store store=check_store(stockProductDTO.getStoreId()); //checking store is available with this id or not
         Product product=check_product(stockProductDTO.getProductId());//checking product is available with this id or not

         List<StockProduct> allStock=store.getStock();  // fetching existing stock

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


//    This method decrease product quantity which is already available in stock.
//    If product is not available in stock then it will throw a exception
//    If item quantity become zero then it will delete that item from stock
//    It return List od stock item available in stock
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


//    This method replace product quantity which is already available in stock.
//    If product is not available in stock then it will throw a exception
//    It return List od stock item available in stock
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


//    This method delete product and quantity which is already available in stock.
//    If product is not available in stock then it will throw a exception
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





//    This method decrease product quantity from sender and increase reciver stock product quantity which is already available in stock.
//    If product is not available in stock then it will throw a exception
//    If product quantity become zero then it will delete that item from stock
//    It also add a transection in transection table
//    In return we get transection which is recently done
    @Override
    @Transactional
    public Transection send_item_from_one_store_to_another
            (Integer senderId,Integer reciverId, Integer productId,Integer productQuantity){
        Product item=check_product(productId);  //we recive product object from this
        Store senderStore=check_store(senderId); //recive sender object from this
        Store reciverStore=check_store(reciverId); //recive reciver object from this

        boolean isProductPresent=check_product_available_in_stock(productId,senderStore.getStock());  //if product is present in sender stock then it will return true
        boolean isQuantityAvailable=check_product_quantity( productId, productQuantity,senderStore.getStock());  //If sufficient quantity available in sender stock then it return true

        if( isProductPresent){
            if(isQuantityAvailable){


                List<StockProduct> reciverUpdatedStock=  increase_or_insert_stockProduct_and_quantity(productId,productQuantity,reciverStore);
                List<StockProduct> senderUpdatedStock = drecrese_or_delete_stockProduct_and_quantity(productId,productQuantity,senderStore.getStock());


                for(int x=0;x<senderUpdatedStock.size();x++){
                    System.out.println(senderUpdatedStock.get(x).getQuantity());
                }
                reciverStore.setStock(reciverUpdatedStock);// replacing old reciver stock from new one
                senderStore.setStock(senderUpdatedStock);  // replacing old  sender stock from new one



//              created a transection and setting value
                transection.setReciver_store(reciverStore);
                transection.setSender_store(senderStore);
                transection.setProducts_sending(item);
                transection.setDateTime_Of_sending(LocalDateTime.now());
                transection.setTotal_amount_of_products(productQuantity*item.getMarket_price());

                senderStore.add_Transection_between_store(transection);  // storing transection object
                storeRepo.save(reciverStore);  //storing reciver updated stock

                storeRepo.save(senderStore);  //storing sender updated stock
                return transection;  // returnd recent transection

            }
            else throw new ProductException("Quantity is not sufficient in your stock");

        }
        else throw new ProductException("This product is not present in your stock. Please check productId.");

//
    }


//  This method return total quantity of product available in each store
    public List<StoreProductQuantity> all_stock_of_each_store(){
         List<StockProduct> stock=stockProductRepo.findAll();

        Map<Integer,Integer> map=new HashMap<>();

//        Iterating on stock of every store
        for(int x=0;x<stock.size();x++){
            Integer storeId=stock.get(x).getStore().getStoreId();
            if(map.containsKey(storeId)){
                map.put(storeId,map.get(storeId)+stock.get(x).getQuantity()); //if map contains store id then increase quantity by adding new quantity
            }
            else  map.put(storeId,stock.get(x).getQuantity()); // if map did not contain store id then it insert store id and insert quantity
        }

        List<Store> allStore=storeRepo.findAll(); // fetching all stores available in database

        List<StoreProductQuantity> allStoreProductQuantities=new ArrayList<>();  // this will be our result

        for(int x=0;x<allStore.size();x++){
            allStoreProductQuantities.add(new StoreProductQuantity(allStore.get(x).getStoreId(), map.get(allStore.get(x).getStoreId()))); //In list adding StoreProductQuantity which contain store id and total quantity
        }
         return allStoreProductQuantities;
    }





//    All supportive fuction start from here---------------------------------------------------

//    It check store available with this store id or not
     public Store check_store(Integer storeId) {
         return storeRepo.findById(storeId).orElseThrow(() -> new StoreException("No store found with "+storeId+" id") );

     }

//     It check product  is available in product table or not
     public Product check_product(Integer productId){
         return productRepo.findById(productId).orElseThrow(()->new ProductException("No store found with "+productId + "id"));

     }

//     It convert dto in stock product
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
