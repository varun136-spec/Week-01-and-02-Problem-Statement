public class Week1andweek2 {

    static final int L1_SIZE = 10000;
    static final int L2_SIZE = 100000;


    static java.util.LinkedHashMap<String, String> L1 =
            new java.util.LinkedHashMap<String, String>(16, 0.75f, true) {
                protected boolean removeEldestEntry(java.util.Map.Entry<String,String> e) {
                    return size() > L1_SIZE;
                }
            };


    static java.util.LinkedHashMap<String, String> L2 =
            new java.util.LinkedHashMap<String, String>(16, 0.75f, true) {
                protected boolean removeEldestEntry(java.util.Map.Entry<String,String> e) {
                    return size() > L2_SIZE;
                }
            };


    static java.util.HashMap<String,String> L3 = new java.util.HashMap<>();

    static int L1Hits = 0;
    static int L2Hits = 0;
    static int L3Hits = 0;


    public static String getVideo(String id) {

        if (L1.containsKey(id)) {
            L1Hits++;
            return L1.get(id);
        }

        if (L2.containsKey(id)) {
            L2Hits++;
            String video = L2.get(id);


            L1.put(id, video);

            return video;
        }

        if (L3.containsKey(id)) {
            L3Hits++;

            String video = L3.get(id);


            L2.put(id, video);
            L1.put(id, video);

            return video;
        }

        return null;
    }


    public static void updateVideo(String id, String data) {

        L3.put(id, data);

        L1.remove(id);
        L2.remove(id);

        System.out.println("Video updated and cache invalidated");
    }


    public static void showStats() {

        int total = L1Hits + L2Hits + L3Hits;

        System.out.println("\nCache Statistics:");

        System.out.println("L1 Hits: " + L1Hits);
        System.out.println("L2 Hits: " + L2Hits);
        System.out.println("L3 Hits: " + L3Hits);

        if (total > 0) {
            System.out.println("L1 Hit Ratio: " + (L1Hits * 100.0 / total) + "%");
            System.out.println("L2 Hit Ratio: " + (L2Hits * 100.0 / total) + "%");
            System.out.println("L3 Hit Ratio: " + (L3Hits * 100.0 / total) + "%");
        }
    }

    public static void main(String[] args) {


        L3.put("video1", "Netflix Movie 1");
        L3.put("video2", "Netflix Movie 2");
        L3.put("video3", "Netflix Movie 3");

        System.out.println(getVideo("video1"));
        System.out.println(getVideo("video1"));
        System.out.println(getVideo("video2"));
        System.out.println(getVideo("video3"));
        System.out.println(getVideo("video1"));

        showStats();

        updateVideo("video1", "Updated Movie");

        System.out.println(getVideo("video1"));

        showStats();
    }
}