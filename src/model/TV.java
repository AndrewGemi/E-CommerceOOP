package model;

import services.Shippable;

public class TV extends NonExpirableProduct implements Shippable {
    private final double weight;

    public TV(String name, double price, int quantity , double weight) {
        super(name, price, quantity);
        this.weight = weight;
        System.out.println(this);
    }

    @Override
    public String toString() {
        return super.toString()+" ,weight=" + weight ;
    }

    @Override
    public double getWeight() {
        return weight;
    }

}
