public class Week1andweek2 {


    static java.util.HashMap<String, java.util.List<String>> ngramIndex = new java.util.HashMap<>();


    public static java.util.List<String> generateNGrams(String text, int n) {

        java.util.List<String> grams = new java.util.ArrayList<>();

        String[] words = text.split(" ");

        for (int i = 0; i <= words.length - n; i++) {

            String gram = "";

            for (int j = 0; j < n; j++) {
                gram += words[i + j] + " ";
            }

            grams.add(gram.trim());
        }

        return grams;
    }

    // Store document n-grams in hash table
    public static void addDocument(String docId, String text, int n) {

        java.util.List<String> grams = generateNGrams(text, n);

        for (String gram : grams) {

            if (!ngramIndex.containsKey(gram)) {
                ngramIndex.put(gram, new java.util.ArrayList<String>());
            }

            ngramIndex.get(gram).add(docId);
        }
    }

    // Calculate similarity between two documents
    public static double calculateSimilarity(String doc1, String doc2, int n) {

        java.util.List<String> grams1 = generateNGrams(doc1, n);
        java.util.List<String> grams2 = generateNGrams(doc2, n);

        int matchCount = 0;

        for (String g : grams1) {
            if (grams2.contains(g)) {
                matchCount++;
            }
        }

        double similarity = (matchCount * 100.0) / grams1.size();

        return similarity;
    }

    public static void main(String[] args) {

        java.util.Scanner sc = new java.util.Scanner(System.in);

        int n = 3; // 3-word n-grams

        String doc1 = "machine learning is very powerful";
        String doc2 = "machine learning is a powerful tool";

        addDocument("Doc1", doc1, n);
        addDocument("Doc2", doc2, n);

        double similarity = calculateSimilarity(doc1, doc2, n);

        System.out.println("Similarity: " + similarity + "%");

        sc.close();
    }
}