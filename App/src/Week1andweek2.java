public class Week1andweek2 {


    static class Transaction {
        int amount;
        String merchant;
        String account;
        long time;

        Transaction(int amount, String merchant, String account) {
            this.amount = amount;
            this.merchant = merchant;
            this.account = account;
            this.time = System.currentTimeMillis();
        }
    }


    public static void twoSum(int[] arr, int target) {

        java.util.HashMap<Integer, Integer> map = new java.util.HashMap<>();

        for (int num : arr) {

            int complement = target - num;

            if (map.containsKey(complement)) {
                System.out.println("Pair found: " + num + " + " + complement + " = " + target);
                return;
            }

            map.put(num, 1);
        }

        System.out.println("No pair found");
    }

    // Two-Sum within time window (1 hour)
    public static void twoSumTimeWindow(java.util.List<Transaction> list, int target) {

        java.util.HashMap<Integer, Transaction> map = new java.util.HashMap<>();

        for (Transaction t : list) {

            int complement = target - t.amount;

            if (map.containsKey(complement)) {

                Transaction prev = map.get(complement);

                if (Math.abs(t.time - prev.time) <= 3600000) {
                    System.out.println("Suspicious pair detected: " + t.amount + " + " + prev.amount);
                }
            }

            map.put(t.amount, t);
        }
    }

    // K-Sum (recursive approach)
    public static boolean kSum(int[] arr, int start, int k, int target) {

        if (k == 2) {
            java.util.HashSet<Integer> set = new java.util.HashSet<>();

            for (int i = start; i < arr.length; i++) {

                if (set.contains(target - arr[i])) {
                    return true;
                }

                set.add(arr[i]);
            }

            return false;
        }

        for (int i = start; i < arr.length; i++) {

            if (kSum(arr, i + 1, k - 1, target - arr[i])) {
                return true;
            }
        }

        return false;
    }


    public static void detectDuplicates(java.util.List<Transaction> list) {

        java.util.HashSet<String> set = new java.util.HashSet<>();

        for (Transaction t : list) {

            String key = t.amount + "-" + t.merchant;

            if (set.contains(key)) {
                System.out.println("Duplicate payment detected: " + key);
            } else {
                set.add(key);
            }
        }
    }

    public static void main(String[] args) {

        int[] transactions = {100, 200, 300, 400, 500};

        System.out.println("Classic Two-Sum:");
        twoSum(transactions, 700);

        java.util.List<Transaction> list = new java.util.ArrayList<>();

        list.add(new Transaction(500, "Amazon", "A1"));
        list.add(new Transaction(200, "Amazon", "A2"));
        list.add(new Transaction(300, "Flipkart", "A3"));
        list.add(new Transaction(500, "Amazon", "A4"));

        System.out.println("\nTwo-Sum with Time Window:");
        twoSumTimeWindow(list, 700);

        System.out.println("\nK-Sum result (3 numbers sum to 1000): " +
                kSum(transactions, 0, 3, 1000));

        System.out.println("\nDuplicate Detection:");
        detectDuplicates(list);
    }
}