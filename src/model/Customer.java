package model;

import exception.EmptyCartException;
import exception.ExpiredProductException;
import exception.InsufficientBalanceException;
import exception.OutOfStockException;
import services.ShippingService;

import java.util.Map;

public class Customer {
    private final String name;
    private double balance;
    private Cart cart;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.cart = new Cart();
    }

    public Cart getCart() {
        return cart;
    }

    public double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public void checkout (ShippingService shippingService) throws EmptyCartException, ExpiredProductException, OutOfStockException, InsufficientBalanceException {
        // check if cart is empty
        if(cart.isEmpty()) throw new EmptyCartException("Cart is Empty.");

        // check if there is any product expired or out of stock
        for(Map.Entry<Product,Integer> i : cart.getItems().entrySet()) {
            if(i.getKey() instanceof ExpirableProduct && ((ExpirableProduct) i.getKey()).isExpired()){
                throw new ExpiredProductException(i.getKey().getName()+" is Expired");
            }
            if(i.getKey().getQuantity() < i.getValue())
                throw new OutOfStockException(i.getKey().getName() + " is out of stock.");
        }

        shippingService.ship(getCart().getShippableItems());
        // calculate total price = total prices of products + shipping fee
        System.out.println();
        System.out.println("--Checkout receipt--");
        double subtotal = cart.calculateSubTotal();
        double shippingfee = shippingService.calculateShippingFee(getCart().getShippableItems());
        double total = subtotal + shippingfee;
        System.out.println("Amount      " + total);

        System.out.println();
        System.out.println(this.getName()+"'s balance before payment: " + this.getBalance());
        // check if customer has sufficient balance compared to total price
        if(total > balance)
            throw new InsufficientBalanceException("Insufficient balance: " + balance + " ,Required: "+ total);
        balance -=total;
        System.out.println();
        System.out.println(this.getName()+"'s balance after payment: " + this.getBalance());
    }
}
