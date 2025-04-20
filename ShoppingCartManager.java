import java.util.Scanner;

public class ShoppingCartManager {
    public static void printMenu() {
        System.out.println("\nMENU");
        System.out.println("a - Add item to cart");
        System.out.println("d - Remove item from cart");
        System.out.println("c - Change item quantity");
        System.out.println("i - Output items' descriptions");
        System.out.println("o - Output shopping cart");
        System.out.println("s - Save cart to file");
        System.out.println("l - Load cart from file");
        System.out.println("q - Quit\n");
    }

    public static void executeMenu(char choice, ShoppingCart cart, Scanner sc) {
        sc.nextLine(); // Clear buffer

        switch (choice) {
            case 'a':
                System.out.println("ADD ITEM TO CART");
                System.out.print("Enter the item name: ");
                String name = sc.nextLine();
                System.out.print("Enter the item description: ");
                String desc = sc.nextLine();
                System.out.print("Enter the item price: ");
                int price = sc.nextInt();
                System.out.print("Enter the item quantity: ");
                int qty = sc.nextInt();
                cart.addItem(new ItemToPurchase(name, desc, price, qty));
                break;
            case 'd':
                System.out.println("REMOVE ITEM FROM CART");
                System.out.print("Enter name of item to remove: ");
                String removeName = sc.nextLine();
                cart.removeItem(removeName);
                break;
            case 'c':
                System.out.println("CHANGE ITEM QUANTITY");
                System.out.print("Enter the item name: ");
                String modName = sc.nextLine();
                System.out.print("Enter the new quantity: ");
                int modQty = sc.nextInt();
                cart.modifyItem(new ItemToPurchase(modName, "", 0, modQty));
                break;
            case 'i':
                System.out.println("OUTPUT ITEMS' DESCRIPTIONS");
                cart.printDescriptions();
                break;
            case 'o':
                System.out.println("OUTPUT SHOPPING CART");
                cart.printTotal();
                break;
            case 's':
                System.out.print("Enter filename to save cart: ");
                String saveFile = sc.nextLine();
                cart.saveCartToFile(saveFile);
                break;
            case 'l':
                System.out.print("Enter filename to load cart: ");
                String loadFile = sc.nextLine();
                cart.loadCartFromFile(loadFile);
                break;
            case 'q':
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter customer's name: ");
        String name = sc.nextLine();
        System.out.print("Enter today's date: ");
        String date = sc.nextLine();

        ShoppingCart cart = new ShoppingCart(name, date);
        System.out.println("\nCustomer name: " + cart.getCustomerName());
        System.out.println("Today's date: " + cart.getDate());

        char choice;
        do {
            printMenu();
            System.out.print("Choose an option: ");
            choice = sc.next().toLowerCase().charAt(0);
            executeMenu(choice, cart, sc);
        } while (choice != 'q');

        System.out.println("Goodbye!");
    }
}