package code;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;

public class main {
    static List<Offering> offerings = new ArrayList<>();
    static List<Lesson> lessons = new ArrayList<>();
    static User loggedInUser = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Main menu loop
        while (true) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1 -> viewLessons();
                case 2 -> viewOfferings();
                case 3 -> login(scanner);
                case 4 -> {
                    if (loggedInUser != null) {
                        accountMenu(scanner);
                    } else {
                        System.out.println("Please log in first.");
                    }
                }
                case 0 -> {
                    System.out.println("Exiting system.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option, please try again.");
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. View Available Lessons");
        System.out.println("2. View Offerings (Instructors Only)");
        System.out.println("3. Log In");
        if (loggedInUser != null) {
            System.out.println("4. Account");
        }
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    private static void viewLessons() {
        System.out.println("\nAvailable Lessons:");
        for (Lesson lesson : lessons) {
            System.out.println(lesson);  // Assuming Lesson class has a meaningful toString()
        }
    }

    private static void viewOfferings() {
        if (loggedInUser instanceof Instructor || loggedInUser instanceof Admin) {
            System.out.println("\nAvailable Offerings:");
            for (Offering offering : offerings) {
                System.out.println(offering);  // Assuming Offering class has a meaningful toString()
            }
        } else {
            System.out.println("Access denied. Offerings are only viewable by Instructors or Admin.");
        }
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
    
        loggedInUser = UserFactory.login(username, password);
    
        if (loggedInUser != null) {
            System.out.println("Login successful. Welcome, " + loggedInUser.getName() + "!");
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }
    

    private static void accountMenu(Scanner scanner) {
        if (loggedInUser instanceof Client) {
            clientAccountMenu(scanner, (Client) loggedInUser);
        } else if (loggedInUser instanceof Instructor) {
            instructorAccountMenu((Instructor) loggedInUser, scanner);
        } else if (loggedInUser instanceof Admin) {
            adminAccountMenu(scanner);
        }
    }

    private static void clientAccountMenu(Scanner scanner, Client client) {
        while (true) {
            System.out.println("\nClient Account Menu:");
            System.out.println("1. View My Bookings");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("\nYour Bookings:");
                for (Booking booking : client.getBookings()) {
                    System.out.println(booking);
                }
                System.out.print("Enter booking ID to cancel or 0 to go back: ");
                int bookingId = scanner.nextInt();
                if (bookingId != 0) {
                    client.cancelBooking(bookingId);
                    System.out.println("Booking cancelled.");
                }
            } else if (choice == 0) {
                return;
            }
        }
    }

    private static void instructorAccountMenu(Instructor instructor, Scanner scanner) {
        System.out.println("\nInstructor Account Menu:");
        System.out.println("Lessons You Are Giving:");
        for (Lesson lesson : instructor.getLessons()) {
            System.out.println(lesson);
        }

        System.out.print("Enter Offering ID to take on as a Lesson, or 0 to go back: ");
        int offeringId = scanner.nextInt();
        if (offeringId != 0 && offeringId <= offerings.size()) {
            Offering offering = offerings.get(offeringId - 1);
            Lesson lesson = new Lesson(instructor, offering.getLocation(), offering.getSchedule(), offering.getType(), offering.getMode(), offering.getCapacity());
            lessons.add(lesson);
            System.out.println("Offering added as a lesson.");
        }
    }

    private static void adminAccountMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nAdmin Account Menu:");
            System.out.println("1. Create Offering");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                createOffering(scanner);
            } else if (choice == 0) {
                return;
            }
        }
    }

    private static void createOffering(Scanner scanner) {
        System.out.print("Enter offering location: ");
        String locationName = scanner.nextLine();
        Location location = new Location(locationName, "City"); // Assuming location has a city

        System.out.print("Enter schedule date (yyyy-mm-dd HH:mm): ");
        String dateTimeString = scanner.nextLine();
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString.replace(" ", "T"));
        Schedule schedule = new Schedule(dateTime);

        System.out.print("Enter offering type: ");
        String type = scanner.nextLine();

        System.out.print("Enter mode (INDIVIDUAL/GROUP): ");
        LessonMode mode = LessonMode.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Enter capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();

        Offering newOffering = new Offering(location, schedule, type, mode, capacity);
        offerings.add(newOffering); 
        location.addOffering(newOffering);
        System.out.println("Offering created successfully!");
    }
}
