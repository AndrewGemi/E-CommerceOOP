package model;

import exception.OutOfStockException;
import services.Shippable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private Map<Product,Integer> items ;

    public Cart() {
        items = new HashMap<>();
    }

    public Map<Product, Integer> getItems() {
        return items;
    }



    public Map<Shippable,Integer> getShippableItems() {
        Map<Shippable,Integer> shippableItems = new HashMap<>();
        for (Map.Entry<Product,Integer> i : items.entrySet()){
            Product product = i.getKey();
            if(product instanceof Shippable){
                shippableItems.put((Shippable) product , i.getValue());
            }
        }
        return shippableItems;
    }

    public void addProduct(Product product, int quantity) throws OutOfStockException{
        if(quantity > product.getQuantity())
            throw new OutOfStockException(product.getName() + " insufficient stock. Available: " + product.getQuantity() + " Required: " + quantity);
        items.put(product,items.getOrDefault(product,0) + quantity);
        System.out.println("Added " + quantity + " unit(s) of " + product.getName() + " to cart");
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }

    public double calculateSubTotal(){
        double total = 0.0;
        for (Map.Entry<Product,Integer> i : items.entrySet()){
            System.out.println(i.getValue()+"x "+i.getKey().getName() + "   " + i.getKey().getPrice() * i.getValue());
            total += i.getKey().getPrice() * i.getValue();
        }
        System.out.println("Subtotal    " + total);
        return total;
    }

}
