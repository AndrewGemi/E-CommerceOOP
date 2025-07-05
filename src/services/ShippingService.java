package services;

import java.util.List;
import java.util.Map;

public class ShippingService {
    public void ship(Map<Shippable,Integer> items) {
        System.out.println("Shipping the following items:");
        for (Map.Entry<Shippable,Integer> item : items.entrySet()) {
            System.out.println(item.getValue()+"x " + item.getKey().getName() + ", Weight: " + item.getKey().getWeight() * item.getValue() + "kg");
        }
    }

    public double calculateShippingFee(Map<Shippable,Integer> items) {
        double totalWeight = 0;
        double shippingfee = 0;
        for (Shippable i : items.keySet()){
            totalWeight += i.getWeight();
        }
        if(totalWeight / 10 < 1) {
            shippingfee = totalWeight * 10;
            System.out.println("Shipping    " + shippingfee);
            return shippingfee;
        }
        else{
            shippingfee = totalWeight * Math.ceil(totalWeight / 10);
            System.out.println("Shipping    " + shippingfee);
            return shippingfee;
        }
    }
}
