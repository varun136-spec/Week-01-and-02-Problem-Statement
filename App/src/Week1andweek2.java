public class Week1andweek2 {


    static java.util.HashMap<String, Integer> pageViews = new java.util.HashMap<>();


    static java.util.HashMap<String, java.util.HashSet<String>> uniqueVisitors = new java.util.HashMap<>();


    static java.util.HashMap<String, Integer> trafficSources = new java.util.HashMap<>();


    public static void processEvent(String page, String user, String source) {


        pageViews.put(page, pageViews.getOrDefault(page, 0) + 1);


        if (!uniqueVisitors.containsKey(page)) {
            uniqueVisitors.put(page, new java.util.HashSet<String>());
        }
        uniqueVisitors.get(page).add(user);


        trafficSources.put(source, trafficSources.getOrDefault(source, 0) + 1);
    }


    public static void showDashboard() {

        System.out.println("\n===== REAL TIME ANALYTICS DASHBOARD =====");


        java.util.List<java.util.Map.Entry<String, Integer>> list =
                new java.util.ArrayList<>(pageViews.entrySet());

        list.sort((a, b) -> b.getValue() - a.getValue());

        System.out.println("\nTop Pages:");
        int count = 0;
        for (java.util.Map.Entry<String, Integer> e : list) {
            System.out.println(e.getKey() + " -> " + e.getValue() + " visits");
            count++;
            if (count == 10) break;
        }

        // Unique visitors
        System.out.println("\nUnique Visitors per Page:");
        for (String page : uniqueVisitors.keySet()) {
            System.out.println(page + " -> " + uniqueVisitors.get(page).size());
        }


        System.out.println("\nTraffic Sources:");
        for (String src : trafficSources.keySet()) {
            System.out.println(src + " -> " + trafficSources.get(src));
        }
    }

    public static void main(String[] args) throws Exception {


        processEvent("/home", "user1", "Google");
        processEvent("/sports", "user2", "Facebook");
        processEvent("/home", "user3", "Direct");
        processEvent("/politics", "user1", "Google");
        processEvent("/home", "user1", "Facebook");
        processEvent("/sports", "user4", "Google");

        // Dashboard refresh every 5 seconds
        while (true) {
            showDashboard();
            Thread.sleep(5000);
        }
    }
}