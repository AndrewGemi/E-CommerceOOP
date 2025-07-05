import model.*;
import services.ShippingService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        testSuccessfulCheckout();
        testEmptyCart();
        testExpiredProduct();
        testInsufficientBalance();
        testOutOfStock();
        testMixedShippableNonShippable();
        testNoShippableItems();
    }
    private static void testSuccessfulCheckout() {
        System.out.println("Test 1: Successful Checkout");
        try {
            // Create products
            System.out.println("Current Stock: ");
            Cheese cheese = new Cheese("Cheddar", 10.0, 5, LocalDate.of(2025, 8, 1), 0.5);
            TV tv = new TV("Smart TV", 500.0, 2, 10.0);
            Mobile mobile = new Mobile("Scratch Card", 20.0, 100);


            // Create customer with sufficient balance
            Customer customer = new Customer("Alice", 1000.0);
            System.out.println();
            System.out.println();

            // Add products to cart

            customer.getCart().addProduct(cheese, 2); // $20
            customer.getCart().addProduct(tv, 1); // $500
            customer.getCart().addProduct(mobile, 5); // $100

            System.out.println();
            System.out.println();
            // Checkout
            ShippingService shippingService = new ShippingService();

            customer.checkout(shippingService);



        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("----------------------------------------\n");
    }

    private static void testEmptyCart() {
        System.out.println("Test 2: Empty Cart");
        try {
            // Create customer with empty cart
            Customer customer = new Customer("Bob", 1000.0);

            // Attempt checkout
            ShippingService shippingService = new ShippingService();
            customer.checkout(shippingService);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("----------------------------------------\n");
    }

    private static void testInsufficientBalance() {
        System.out.println("Test 3: Insufficient Balance");
        try {
            // Create products
            System.out.println("Current Stock: ");
            Cheese cheese = new Cheese("Gouda", 15.0, 5, LocalDate.of(2025, 8, 1), 0.3);
            TV tv = new TV("LED TV", 600.0, 2, 12.0);

            // Create customer with low balance
            Customer customer = new Customer("Charlie", 50.0);

            // Add products to cart
            customer.getCart().addProduct(cheese, 2); // $30
            customer.getCart().addProduct(tv, 1); // $600

            // Attempt checkout
            ShippingService shippingService = new ShippingService();
            customer.checkout(shippingService);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("----------------------------------------\n");
    }

    private static void testOutOfStock() {
        System.out.println("Test 4: Out of Stock");
        try {
            // Create product with limited stock
            System.out.println("Current Stock: ");
            Biscuits biscuits = new Biscuits("Digestive", 5.0, 3, LocalDate.of(2025, 12, 1), 0.2);

            // Create customer
            Customer customer = new Customer("Dave", 1000.0);

            // Attempt to add more than available
            customer.getCart().addProduct(biscuits, 5); // Exceeds stock
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("----------------------------------------\n");
    }

    private static void testExpiredProduct() {
        System.out.println("Test 5: Expired Product");
        try {
            // Create expired product
            System.out.println("Current Stock: ");
            Cheese cheese = new Cheese("Brie", 12.0, 5, LocalDate.of(2025, 1, 1), 0.4);

            // Create customer
            Customer customer = new Customer("Eve", 1000.0);

            // Add expired product to cart
            customer.getCart().addProduct(cheese, 2);

            // Attempt checkout
            ShippingService shippingService = new ShippingService();
            customer.checkout(shippingService);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("----------------------------------------\n");
    }

    private static void testMixedShippableNonShippable() {
        System.out.println("Test 6: Mixed Shippable and Non-Shippable Items");
        try {
            // Create products
            System.out.println("Current Stock: ");
            Biscuits biscuits = new Biscuits("Oatmeal", 6.0, 10, LocalDate.of(2025, 12, 1), 0.25);
            Mobile mobile = new Mobile("Scratch Card", 15.0, 50);

            // Create customer
            Customer customer = new Customer("Frank", 500.0);

            // Add shippable and non-shippable items
            customer.getCart().addProduct(biscuits, 3); // $18
            customer.getCart().addProduct(mobile, 10); // $150

            // Checkout
            ShippingService shippingService = new ShippingService();
            customer.checkout(shippingService);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("----------------------------------------\n");
    }

    private static void testNoShippableItems() {
        System.out.println("Test 7: No Shippable Items");
        try {
            // Create non-shippable product
            System.out.println("Current Stock: ");
            Mobile mobile = new Mobile("Gift Card", 25.0, 100);

            // Create customer
            Customer customer = new Customer("Hannah", 1000.0);

            // Add non-shippable item
            customer.getCart().addProduct(mobile, 5); // $125

            // Checkout
            ShippingService shippingService = new ShippingService();
            customer.checkout(shippingService);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("----------------------------------------\n");
    }
}