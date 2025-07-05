package model;

public class Mobile extends NonExpirableProduct{

    public Mobile(String name, double price, int quantity) {
        super(name, price, quantity);
        System.out.println(this);
    }
}
