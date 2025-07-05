package model;

import java.time.LocalDate;

public abstract class ExpirableProduct extends Product{
    private LocalDate expiryDate;

    public ExpirableProduct(String name, double price, int quantity, LocalDate expiryDate) {
        super(name, price, quantity);
        this.expiryDate = expiryDate;
    }
    public boolean isExpired(){
        return LocalDate.now().isAfter(expiryDate);
    }

    @Override
    public String toString() {
        return super.toString() + " ,expiryDate=" + expiryDate ;
    }
}
