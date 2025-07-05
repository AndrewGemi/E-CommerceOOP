# E-Commerce System

A Java-based e-commerce system for managing shopping cart and checkout process with support for expirable/non-expirable products, shipping calculations, and comprehensive error handling.

## Assumptions

- Java 8 or higher is installed
- Each customer created has a cart created automatically
- for each 10kg a fee of 10$ is added

## Test Scenarios

### Test 1: Successful Checkout

**Purpose:** Verify checkout with shippable and non-shippable items.

**Code:**

```java
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

            customer.getCart().addProduct(cheese, 2);
            customer.getCart().addProduct(tv, 1);
            customer.getCart().addProduct(mobile, 5);

            System.out.println();
            System.out.println();
            // Checkout
            ShippingService shippingService = new ShippingService();

            customer.checkout(shippingService);
```

**Output:**

```
Test 1: Successful Checkout
Current Stock:
name='Cheddar', price=10.0, quantity=5 ,expiryDate=2025-08-01 ,weight=0.5
name='Smart TV', price=500.0, quantity=2 ,weight=10.0
name='Scratch Card', price=20.0, quantity=100


Added 2 unit(s) of Cheddar to cart
Added 1 unit(s) of Smart TV to cart
Added 5 unit(s) of Scratch Card to cart


Shipping the following items:
2x Cheddar, Weight: 1.0kg
1x Smart TV, Weight: 10.0kg

--Checkout receipt--
2x Cheddar   20.0
5x Scratch Card   100.0
1x Smart TV   500.0
Subtotal    620.0
Shipping    21.0
Amount      641.0

Alice's balance before payment: 1000.0

Alice's balance after payment: 359.0

```

### Test 2: Empty Cart

**Purpose:** Test checkout with an empty cart.

**Code:**

```java
Customer customer = new Customer("Bob", 1000.0);
ShippingService shippingService = new ShippingService();
customer.checkout(shippingService);
```

**Output:**

```
Test 2: Empty Cart
Error: Cart is Empty.
```

### Test 3: Insufficient Balance

**Purpose:** Test checkout with insufficient balance.

**Code:**

```java
Cheese cheese = new Cheese("Gouda", 15.0, 5, LocalDate.of(2025, 8, 1), 0.3);
TV tv = new TV("LED TV", 600.0, 2, 12.0);
Customer customer = new Customer("Charlie", 50.0);
customer.getCart().addProduct(cheese, 2);
customer.getCart().addProduct(tv, 1);
ShippingService shippingService = new ShippingService();
customer.checkout(shippingService);
```

**Output:**

```
Test 3: Insufficient Balance
Current Stock:
name='Gouda', price=15.0, quantity=5 ,expiryDate=2025-08-01 ,weight=0.3
name='LED TV', price=600.0, quantity=2 ,weight=12.0
Added 2 unit(s) of Gouda to cart
Added 1 unit(s) of LED TV to cart
Shipping the following items:
1x LED TV, Weight: 12.0kg
2x Gouda, Weight: 0.6kg

--Checkout receipt--
1x LED TV   600.0
2x Gouda   30.0
Subtotal    630.0
Shipping    24.6
Amount      654.6

Charlie's balance before payment: 50.0
Error: Insufficient balance: 50.0 ,Required: 654.6
```

### Test 4: Out of Stock

**Purpose:** Test adding more items than available.

**Code:**

```java
Biscuits biscuits = new Biscuits("Digestive", 5.0, 3, LocalDate.of(2025, 12, 1), 0.2);
Customer customer = new Customer("Dave", 1000.0);
customer.getCart().addProduct(biscuits, 5);
```

**Output:**

```
Test 4: Out of Stock
Current Stock:
name='Digestive', price=5.0, quantity=3 ,expiryDate=2025-12-01 ,weight=0.2
Error: Digestive insufficient stock. Available: 3 Required: 5
```

### Test 5: Expired Product

**Purpose:** Test checkout with an expired product.

**Code:**

```java
Cheese cheese = new Cheese("Brie", 12.0, 5, LocalDate.of(2025, 1, 1), 0.4);
Customer customer = new Customer("Eve", 1000.0);
customer.getCart().addProduct(cheese, 2);
ShippingService shippingService = new ShippingService();
customer.checkout(shippingService);
```

**Output:**

```
Test 5: Expired Product
Current Stock:
name='Brie', price=12.0, quantity=5 ,expiryDate=2025-01-01 ,weight=0.4
Added 2 unit(s) of Brie to cart
Error: Brie is Expired
```

### Test 6: Mixed Shippable and Non-Shippable Items

**Purpose:** Verify checkout with shippable and non-shippable items.

**Code:**

```java
Biscuits biscuits = new Biscuits("Oatmeal", 6.0, 10, LocalDate.of(2025, 12, 1), 0.25);
Mobile mobile = new Mobile("Scratch Card", 15.0, 50);
Customer customer = new Customer("Frank", 500.0);
customer.getCart().addProduct(biscuits, 3);
customer.getCart().addProduct(mobile, 10);
ShippingService shippingService = new ShippingService();
customer.checkout(shippingService);
```

**Output:**

```
Test 6: Mixed Shippable and Non-Shippable Items
Current Stock:
name='Oatmeal', price=6.0, quantity=10 ,expiryDate=2025-12-01 ,weight=0.25
name='Scratch Card', price=15.0, quantity=50
Added 3 unit(s) of Oatmeal to cart
Added 10 unit(s) of Scratch Card to cart
Shipping the following items:
3x Oatmeal, Weight: 0.75kg

--Checkout receipt--
3x Oatmeal   18.0
10x Scratch Card   150.0
Subtotal    168.0
Shipping    2.5
Amount      170.5

Frank's balance before payment: 500.0

Frank's balance after payment: 329.5
```

### Test 7: No Shippable Items

**Purpose:** Verify checkout with only non-shippable items.

**Code:**

```java
Mobile mobile = new Mobile("Gift Card", 25.0, 100);
Customer customer = new Customer("Hannah", 1000.0);
customer.getCart().addProduct(mobile, 5);
ShippingService shippingService = new ShippingService();
customer.checkout(shippingService);
```

**Output:**

```
Test 7: No Shippable Items
Current Stock:
name='Gift Card', price=25.0, quantity=100
Added 5 unit(s) of Gift Card to cart
Shipping the following items:

--Checkout receipt--
5x Gift Card   125.0
Subtotal    125.0
Shipping    0.0
Amount      125.0

Hannah's balance before payment: 1000.0

Hannah's balance after payment: 875.0
```
