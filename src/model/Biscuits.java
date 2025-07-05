package model;

import services.Shippable;

import java.time.LocalDate;

public class Biscuits extends ExpirableProduct implements Shippable {
    private final double weight;

    public Biscuits(String name, double price, int quantity, LocalDate expiryDate , double weight) {
        super(name, price, quantity, expiryDate);
        this.weight = weight;
        System.out.println(this);
    }

    @Override
    public String toString() {
        return super.toString()+" ,weight=" + weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
