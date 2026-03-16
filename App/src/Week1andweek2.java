public class Week1andweek2 {

    // Product stock storage
    static java.util.HashMap<String, Integer> stock = new java.util.HashMap<>();

    // Waiting list for customers
    static java.util.Queue<String> waitingList = new java.util.LinkedList<>();

    // Check stock availability
    public static int checkStock(String product) {
        return stock.getOrDefault(product, 0);
    }

    // Purchase product (thread-safe)
    public synchronized static void purchase(String product, String customer) {

        int available = stock.getOrDefault(product, 0);

        if (available > 0) {
            stock.put(product, available - 1);
            System.out.println(customer + " successfully purchased " + product);
        }
        else {
            waitingList.add(customer);
            System.out.println(customer + " added to waiting list");
        }
    }

    public static void main(String[] args) {

        java.util.Scanner sc = new java.util.Scanner(System.in);

        // Flash sale product with limited stock
        stock.put("Laptop", 100);

        System.out.println("Flash Sale Product: Laptop");
        System.out.println("Current Stock: " + checkStock("Laptop"));

        System.out.print("Enter customer name: ");
        String customer = sc.nextLine();

        purchase("Laptop", customer);

        System.out.println("Remaining Stock: " + checkStock("Laptop"));

        System.out.println("\nWaiting List:");
        for(String c : waitingList){
            System.out.println(c);
        }

        sc.close();
    }
}