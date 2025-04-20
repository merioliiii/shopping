import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCart {
    private String customerName;
    private String currentDate;
    private ArrayList<ItemToPurchase> cartItems;

    public ShoppingCart() {
        this.customerName = "none";
        this.currentDate = "January 1, 2016";
        this.cartItems = new ArrayList<>();
    }

    public ShoppingCart(String name, String date) {
        this.customerName = name;
        this.currentDate = date;
        this.cartItems = new ArrayList<>();
    }

    public String getCustomerName() { return customerName; }
    public String getDate() { return currentDate; }

    public void addItem(ItemToPurchase item) {
        cartItems.add(item);
    }

    public void removeItem(String itemName) {
        boolean removed = cartItems.removeIf(item -> item.getName().equalsIgnoreCase(itemName));
        if (!removed) {
            System.out.println("Item not found in cart. Nothing removed.");
        }
    }

    public void modifyItem(ItemToPurchase updatedItem) {
        for (ItemToPurchase item : cartItems) {
            if (item.getName().equalsIgnoreCase(updatedItem.getName())) {
                if (updatedItem.getQuantity() != 0) {
                    item.setQuantity(updatedItem.getQuantity());
                }
                return;
            }
        }
        System.out.println("Item not found in cart. Nothing modified.");
    }

    public int getNumItemsInCart() {
        int total = 0;
        for (ItemToPurchase item : cartItems) {
            total += item.getQuantity();
        }
        return total;
    }

    public int getCostOfCart() {
        int total = 0;
        for (ItemToPurchase item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    public void printTotal() {
        System.out.println(customerName + "'s Shopping Cart - " + currentDate);
        System.out.println("Number of Items: " + getNumItemsInCart());

        if (cartItems.isEmpty()) {
            System.out.println("SHOPPING CART IS EMPTY");
        } else {
            for (ItemToPurchase item : cartItems) {
                item.printItemCost();
            }
        }
        System.out.println("Total: $" + getCostOfCart());
    }

    public void printDescriptions() {
        System.out.println(customerName + "'s Shopping Cart - " + currentDate);
        System.out.println("\nItem Descriptions");
        for (ItemToPurchase item : cartItems) {
            item.printItemDescription();
        }
    }

    public void saveCartToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (ItemToPurchase item : cartItems) {
                writer.println(item.getName() + "," +
                               item.getDescription() + "," +
                               item.getPrice() + "," +
                               item.getQuantity());
            }
            System.out.println("Cart saved successfully to " + filename);
        } catch (IOException e) {
            System.out.println("Error: Could not save cart to file.");
        }
    }

    public void loadCartFromFile(String filename) {
        cartItems.clear();
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String name = parts[0];
                    String desc = parts[1];
                    int price = Integer.parseInt(parts[2]);
                    int quantity = Integer.parseInt(parts[3]);
                    cartItems.add(new ItemToPurchase(name, desc, price, quantity));
                }
            }
            System.out.println("Cart loaded successfully from " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        } catch (Exception e) {
            System.out.println("Error: Failed to load cart. File format may be incorrect.");
        }
    }

    public ArrayList<ItemToPurchase> getCartItems() {
        return cartItems;
    }
}