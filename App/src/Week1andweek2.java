public class Week1andweek2 {

    static final int TOTAL_SPOTS = 500;

    static Vehicle[] parkingLot = new Vehicle[TOTAL_SPOTS];

    static int totalVehicles = 0;
    static long totalParkingTime = 0;

    static class Vehicle {

        String licensePlate;
        long entryTime;

        Vehicle(String plate) {
            licensePlate = plate;
            entryTime = System.currentTimeMillis();
        }
    }

    // Hash function
    public static int hash(String plate) {
        return Math.abs(plate.hashCode()) % TOTAL_SPOTS;
    }

    // Park vehicle using linear probing
    public static int parkVehicle(String plate) {

        int index = hash(plate);
        int start = index;

        while (parkingLot[index] != null) {
            index = (index + 1) % TOTAL_SPOTS;

            if (index == start) {
                System.out.println("Parking lot full");
                return -1;
            }
        }

        parkingLot[index] = new Vehicle(plate);
        totalVehicles++;

        System.out.println("Vehicle parked at spot: " + index);
        return index;
    }

    // Remove vehicle and calculate bill
    public static void exitVehicle(String plate) {

        int index = hash(plate);
        int start = index;

        while (parkingLot[index] != null) {

            if (parkingLot[index].licensePlate.equals(plate)) {

                long exitTime = System.currentTimeMillis();
                long duration = (exitTime - parkingLot[index].entryTime) / 1000;

                totalParkingTime += duration;

                parkingLot[index] = null;

                System.out.println("Vehicle exited from spot " + index);
                System.out.println("Parking duration: " + duration + " seconds");

                return;
            }

            index = (index + 1) % TOTAL_SPOTS;

            if (index == start) break;
        }

        System.out.println("Vehicle not found");
    }

    // Find nearest empty spot
    public static int nearestAvailableSpot() {

        for (int i = 0; i < TOTAL_SPOTS; i++) {
            if (parkingLot[i] == null) {
                return i;
            }
        }

        return -1;
    }

    // Show parking statistics
    public static void showStats() {

        int occupied = 0;

        for (int i = 0; i < TOTAL_SPOTS; i++) {
            if (parkingLot[i] != null) {
                occupied++;
            }
        }

        double occupancyRate = (occupied * 100.0) / TOTAL_SPOTS;

        System.out.println("Occupied spots: " + occupied);
        System.out.println("Occupancy rate: " + occupancyRate + "%");

        if (totalVehicles > 0) {
            System.out.println("Average parking time: " + (totalParkingTime / totalVehicles) + " seconds");
        }
    }

    public static void main(String[] args) {

        java.util.Scanner sc = new java.util.Scanner(System.in);

        System.out.println("1. Park Vehicle");
        System.out.println("2. Exit Vehicle");
        System.out.println("3. Nearest Spot");
        System.out.println("4. Show Stats");

        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {

            System.out.print("Enter license plate: ");
            String plate = sc.nextLine();

            parkVehicle(plate);
        }

        else if (choice == 2) {

            System.out.print("Enter license plate: ");
            String plate = sc.nextLine();

            exitVehicle(plate);
        }

        else if (choice == 3) {

            int spot = nearestAvailableSpot();

            if (spot != -1)
                System.out.println("Nearest available spot: " + spot);
            else
                System.out.println("Parking full");
        }

        else if (choice == 4) {
            showStats();
        }

        sc.close();
    }
}