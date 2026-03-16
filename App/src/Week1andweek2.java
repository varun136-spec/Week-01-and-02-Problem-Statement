public class Week1andweek2 {


    static java.util.HashSet<String> usernames = new java.util.HashSet<>();


    static java.util.HashMap<String, Integer> popularity = new java.util.HashMap<>();


    public static boolean checkAvailability(String username) {

        popularity.put(username, popularity.getOrDefault(username, 0) + 1);

        return !usernames.contains(username);
    }


    public static java.util.List<String> suggestUsernames(String username) {

        java.util.List<String> suggestions = new java.util.ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            String suggestion = username + i;
            if (!usernames.contains(suggestion)) {
                suggestions.add(suggestion);
            }
        }

        suggestions.add(username + "_official");
        suggestions.add(username + "_123");

        return suggestions;
    }

    public static void main(String[] args) {

        java.util.Scanner sc = new java.util.Scanner(System.in);

        usernames.add("varun");
        usernames.add("john");
        usernames.add("alex");

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        if (checkAvailability(username)) {
            System.out.println("Username available!");
            usernames.add(username);
        } else {
            System.out.println("Username already taken.");
            System.out.println("Suggested usernames: " + suggestUsernames(username));
        }

        System.out.println("\nUsername attempt popularity:");
        for (String name : popularity.keySet()) {
            System.out.println(name + " -> " + popularity.get(name));
        }

        sc.close();
    }
}