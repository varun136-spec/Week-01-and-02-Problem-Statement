public class Week1andweek2 {

    // Maximum requests per hour
    static final int MAX_REQUESTS = 1000;

    // Store client buckets
    static java.util.HashMap<String, TokenBucket> clients = new java.util.HashMap<>();

    // Token bucket class
    static class TokenBucket {

        int tokens;
        long lastRefillTime;

        TokenBucket() {
            tokens = MAX_REQUESTS;
            lastRefillTime = System.currentTimeMillis();
        }

        boolean allowRequest() {

            long currentTime = System.currentTimeMillis();

            // Refill tokens every hour
            if (currentTime - lastRefillTime >= 3600000) {
                tokens = MAX_REQUESTS;
                lastRefillTime = currentTime;
            }

            if (tokens > 0) {
                tokens--;
                return true;
            }

            return false;
        }
    }

    // Check request for a client
    public static boolean checkRateLimit(String clientId) {

        if (!clients.containsKey(clientId)) {
            clients.put(clientId, new TokenBucket());
        }

        TokenBucket bucket = clients.get(clientId);

        return bucket.allowRequest();
    }

    public static void main(String[] args) {

        java.util.Scanner sc = new java.util.Scanner(System.in);

        System.out.print("Enter client API key: ");
        String client = sc.nextLine();

        boolean allowed = checkRateLimit(client);

        if (allowed) {
            System.out.println("Request Allowed");
        } else {
            System.out.println("Rate limit exceeded. Try again later.");
        }

        sc.close();
    }
}