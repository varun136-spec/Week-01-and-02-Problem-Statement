public class Week1andweek2 {

    // Cache capacity
    static final int MAX_CACHE_SIZE = 5;

    // Cache storage with LRU behavior
    static java.util.LinkedHashMap<String, CacheEntry> cache =
            new java.util.LinkedHashMap<String, CacheEntry>(16, 0.75f, true) {
                protected boolean removeEldestEntry(java.util.Map.Entry<String, CacheEntry> eldest) {
                    return size() > MAX_CACHE_SIZE;
                }
            };

    static int cacheHits = 0;
    static int cacheMisses = 0;

    // Cache entry class
    static class CacheEntry {
        String ip;
        long expiryTime;

        CacheEntry(String ip, int ttlSeconds) {
            this.ip = ip;
            this.expiryTime = System.currentTimeMillis() + (ttlSeconds * 1000);
        }

        boolean isExpired() {
            return System.currentTimeMillis() > expiryTime;
        }
    }

    // Simulated upstream DNS lookup
    public static String queryUpstreamDNS(String domain) {
        System.out.println("Querying upstream DNS for " + domain);
        return "192.168.1." + new java.util.Random().nextInt(255);
    }

    // Resolve domain
    public static String resolve(String domain, int ttl) {

        if (cache.containsKey(domain)) {

            CacheEntry entry = cache.get(domain);

            if (!entry.isExpired()) {
                cacheHits++;
                return entry.ip;
            } else {
                cache.remove(domain);
            }
        }

        cacheMisses++;

        String ip = queryUpstreamDNS(domain);

        cache.put(domain, new CacheEntry(ip, ttl));

        return ip;
    }

    // Show statistics
    public static void showStats() {

        int total = cacheHits + cacheMisses;

        System.out.println("\nCache Hits: " + cacheHits);
        System.out.println("Cache Misses: " + cacheMisses);

        if (total > 0) {
            double ratio = (cacheHits * 100.0) / total;
            System.out.println("Hit Ratio: " + ratio + "%");
        }
    }

    public static void main(String[] args) {

        java.util.Scanner sc = new java.util.Scanner(System.in);

        System.out.print("Enter domain name: ");
        String domain = sc.nextLine();

        String ip = resolve(domain, 10); // TTL = 10 seconds

        System.out.println("Resolved IP: " + ip);

        showStats();

        sc.close();
    }
}