package model;

import exception.OutOfStockException;

public abstract class Product {
    private final String name;
    private final double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
    public void reduceQuantity(int amount) throws Exception{
        if(amount > quantity)
            throw new OutOfStockException(name + " is Out of stock. Available: " + quantity);
        else
            quantity -=amount;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity
                ;
    }
}
