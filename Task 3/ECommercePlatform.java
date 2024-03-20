import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Product {
    private String name;
    private double price;
    private int quantity;

    // Constructor
    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}

class ShoppingCart {
    private List<Product> items;

    // Constructor
    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    // Method to add item to cart
    public void addItem(Product product) {
        items.add(product);
    }

    // Method to get items in cart
    public List<Product> getItems() {
        return items;
    }
}

class Order {
    private List<Product> items;
    private double total;

    // Constructor
    public Order(List<Product> items) {
        this.items = items;
        this.total = calculateTotal();
    }

    // Method to calculate total order amount
    private double calculateTotal() {
        double total = 0;
        for (Product item : items) {
            total += item.getPrice();
        }
        return total;
    }

    // Getter for total
    public double getTotal() {
        return total;
    }
}

class User {
    private String username;
    private String password;

    // Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Method to authenticate user
    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}


 class PaymentProcessor {
    // Method to check if the card has expired
    public boolean isCardExpired(String expiryDateStr) {
        // Define the date formatter for the input format (dd/MM/yyyy)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        // Parse the expiration date string to LocalDate
        LocalDate expirationDate = LocalDate.parse("01/"+expiryDateStr, formatter);

        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Check if the expiration date is before the current date
        return expirationDate.isBefore(currentDate);
    }

    // Method to process payment
    public boolean processPayment(String cardNumber, String cvv, String expiryDate) {
        ///Example implementation of card number and CVV length check
        if (cardNumber.length() != 16 || cvv.length() != 3) {
            System.out.println("Invalid card number or CVV length.");
            return false;
        }

        if (isCardExpired(expiryDate)) {
            System.out.println("Card has expired.");
            return false;
        }

        // Payment success
        System.out.println("Payment success.");
        return true;
    }
}
    


public class ECommercePlatform {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample product listing (only 10 products for demonstration)
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 999.99, 5));
        products.add(new Product("Smartphone", 599.99, 10));
        products.add(new Product("Headphones", 99.99, 20));
        products.add(new Product("Tablet", 299.99, 15));
        products.add(new Product("Smartwatch", 199.99, 25));
        products.add(new Product("Camera", 499.99, 8));
        products.add(new Product("Printer", 199.99, 12));
        products.add(new Product("Speaker", 149.99, 18));
        products.add(new Product("Gaming Console", 399.99, 7));
        products.add(new Product("Fitness Tracker", 79.99, 30));

        // User authentication
        System.out.println("WELCOME-USER");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User user = new User(username, password);

        // Secure payment processing
        PaymentProcessor paymentProcessor = new PaymentProcessor();

        if (user.authenticate(username, password)) {
            // Product listing
            System.out.println("\nProduct Listing:");
            int i = 1;
            for (Product product : products) {
                System.out.println(i + ". " + product.getName() + " - $" + product.getPrice());
                i++;
            }

            // Add items to shopping cart
            ShoppingCart cart = new ShoppingCart();
            boolean confirmOrder = false;
            while (!confirmOrder) {
                System.out.print("\nEnter product number to add to cart (1 to 10) and For exit press 0: ");
                int productNumber = scanner.nextInt();
                if (productNumber == 0) {
                    break;
                }
                if (productNumber < 1 || productNumber > products.size()) {
                    System.out.println("Invalid product number. Please try again.");
                    continue;
                }
                Product selectedProduct = products.get(productNumber - 1);
                cart.addItem(selectedProduct);
                System.out.println("You successfully added " + selectedProduct.getName() + " - $" + selectedProduct.getPrice());
                System.out.print("Do you want to continue adding more items to your cart? (yes/no): ");
                String continueAdding = scanner.next().toLowerCase();
                if (!continueAdding.equals("yes") && !continueAdding.equals("y")) {
                    confirmOrder = true;
                }
            }

            // Display items in cart
            System.out.println("\nItems in Your Cart:");
            for (Product item : cart.getItems()) {
                System.out.println("- " + item.getName() + " - $" + item.getPrice());
            }

            // Confirm order or make changes
            System.out.print("\nDo you want to confirm your order? (yes/no): ");
            String confirm = scanner.next().toLowerCase();
            if (confirm.equals("yes") || confirm.equals("y")) {
                // Process order
                System.out.println("\nOrder Summary:");
                Order order = new Order(cart.getItems());
                System.out.println("Total: $" + order.getTotal());

                // Secure payment processing
         System.out.print("Enter card number: ");
         String cardNumber = scanner.next();

         System.out.print("Enter CVV: ");
         String cvv = scanner.next();

         System.out.print("Enter expiry date (MM/yyyy): ");
         String expiryDate = scanner.next();
        
         paymentProcessor.isCardExpired(expiryDate);
       //  paymentProcessor.processPayment(cardNumber, cvv, expiryDate);    
            
            // Process payment
                if (paymentProcessor.processPayment(cardNumber, cvv, expiryDate)) {
                    System.out.println("Payment processed successfully. Order placed!");
                } else {
                    System.out.println("Payment processing failed. Please try again.");
                }
            } else {
                System.out.println("Order cancelled. You can continue shopping or make changes to your cart.");
            }
        } else {
            System.out.println("Invalid username or password. Authentication failed.");
        }

        scanner.close();
        System.out.println("Thanks for Using");
    }
}