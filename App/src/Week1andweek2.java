public class Week1andweek2 {


    static java.util.HashMap<String, Integer> queryFreq = new java.util.HashMap<>();


    public static void addQuery(String query) {

        queryFreq.put(query, queryFreq.getOrDefault(query, 0) + 1);
    }


    public static java.util.List<String> getSuggestions(String prefix) {

        java.util.List<java.util.Map.Entry<String, Integer>> matches =
                new java.util.ArrayList<>();


        for (java.util.Map.Entry<String, Integer> entry : queryFreq.entrySet()) {

            if (entry.getKey().startsWith(prefix)) {
                matches.add(entry);
            }
        }


        matches.sort((a, b) -> b.getValue() - a.getValue());


        java.util.List<String> suggestions = new java.util.ArrayList<>();

        int count = 0;
        for (java.util.Map.Entry<String, Integer> e : matches) {
            suggestions.add(e.getKey());
            count++;
            if (count == 10) break;
        }

        return suggestions;
    }

    public static void main(String[] args) {

        java.util.Scanner sc = new java.util.Scanner(System.in);

        // Example search data
        addQuery("machine learning tutorial");
        addQuery("machine learning course");
        addQuery("machine learning tutorial");
        addQuery("machine learning python");
        addQuery("machine learning projects");
        addQuery("machine learning tutorial");
        addQuery("machine learning examples");

        System.out.print("Enter search prefix: ");
        String prefix = sc.nextLine();

        java.util.List<String> suggestions = getSuggestions(prefix);

        System.out.println("Suggestions:");
        for (String s : suggestions) {
            System.out.println(s);
        }

        sc.close();
    }
}