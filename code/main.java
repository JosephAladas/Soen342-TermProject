package code;

import java.io.Console;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class main {

    private static Map<Pair<UserType, String>, User> userMap = new HashMap<>();
    private static List<Location> locations = new ArrayList<>();
    private static List<Offering> offerings = new ArrayList<>();
    private static List<Lesson> lessons = new ArrayList<>();

    public static void main(String[] args) {
        Admin admin = Admin.getInstance(); 
        userMap.put(new Pair<>(UserType.ADMIN, admin.getUsername()), admin); // Add admin to the map
        Scanner sc = new Scanner(System.in);

        Location marseille = new Location("marseille's", "Montreal");
        Location fitnessPlus = new Location("Fitness Plus", "Montreal");
        locations.add(marseille);
        locations.add(fitnessPlus);

        UserType loggedInUserType = null; // To track logged-in user type
        User loggedInUser = null;

        while (true) { 
            System.out.println();
            
            // Display correct menu based on login status
            if (loggedInUserType == null) {
                menu();
            } else if (loggedInUserType == UserType.ADMIN) {
                adminMenu();
            } else if (loggedInUserType == UserType.CLIENT) {
                clientMenu();
            } else if (loggedInUserType == UserType.INSTRUCTOR){
                instructorMenu();
            }
            
            String choice = sc.next();
            sc.nextLine();  // Consume the newline character

            switch (choice) {
                case "1":
                //
                // 
                // LOG IN - Normal
                // Add Offering - ADMIN
                // View Bookings - Client
                // View Lessons - Instructor
                //
                //
                    if (loggedInUserType == null) {
                        // LOG IN logic
                        System.out.print("Enter user type (INSTRUCTOR, CLIENT, or ADMIN): ");
                        UserType userType = UserType.valueOf(sc.next().toUpperCase());

                        System.out.print("Enter username: ");
                        String username = sc.next();

                        Pair<UserType, String> key = new Pair<>(userType, username);

                        if (userMap.containsKey(key)) {
                            User user = userMap.get(key);
                            System.out.println("User found: " + user.getUsername());

                            Console console = System.console();
                            if (console != null) {
                                char[] passwordArray = console.readPassword("Enter password: ");
                                String password = new String(passwordArray);

                                if (user.getPassword().equals(password)) {
                                    System.out.println("Login successful!");
                                    loggedInUser = user;
                                    if (userType == UserType.ADMIN) {
                                        loggedInUserType = UserType.ADMIN;
                                    } else if(userType == UserType.CLIENT) {
                                        loggedInUserType = UserType.CLIENT;
                                    } else if(userType == UserType.INSTRUCTOR) {
                                        loggedInUserType = UserType.INSTRUCTOR;
                                    } 
                                    System.out.println("Welcome" + user.getName());
                                } else {
                                    System.out.println("Incorrect password.");
                                }
                            } else {
                                System.out.println("Console not available. Run this in a terminal.");
                            }

                        } else {
                            System.out.println("User not found.");
                        }
                    } else if (loggedInUserType == UserType.ADMIN) {
                        addOffering(sc);
                    } else if (loggedInUserType == UserType.CLIENT) {
                        clientViewBookings(loggedInUser, sc);
                    } else if (loggedInUserType == UserType.INSTRUCTOR) {
                        Instructor loggedInInstructor = (Instructor)loggedInUser;
                        List<Lesson> lessons = loggedInInstructor.getLessons();
                        for(Lesson lesson : lessons){
                            System.out.println("Lessons you are teaching: ");
                            System.out.println(lesson.toString());
                        }
                    }
                    break;
                case "2":
                //
                //
                // SIGN UP - Normal
                // View Bookings - ADMIN
                // Book Lesson - CLIENT 
                // Vew Offerings - INSTRUCTOR
                //
                //
                    if (loggedInUserType == null) {
                        signUp(sc);
                    } else if (loggedInUserType == UserType.ADMIN) {
                        adminViewBookings(sc);
                    } else if (loggedInUserType == UserType.CLIENT) {
                        Client loggedInClient = (Client) loggedInUser;
                        clientBookLesson(loggedInClient, sc);
                    } else if (loggedInUserType == UserType.INSTRUCTOR) {
                        instructorViewOfferings(sc, loggedInUser);
                    }
                    break;

                //
                //
                // View Lessons - Normal
                // View Lessons - ADMIN
                // Exit - CLIENT & INSTRUCTOR
                //
                //
                case "3":
                    if (loggedInUserType == null) {
                        viewLessons();
                    } else if (loggedInUserType == UserType.ADMIN) {
                        viewLessons();
                    } else if (loggedInUserType == UserType.CLIENT){
                        // Terminate logic for client menu
                        System.out.println("Exiting client menu...");
                        loggedInUserType = null;  // Log out from client menu
                    } else if (loggedInUserType == UserType.INSTRUCTOR){
                        // Terminate logic for client menu
                        System.out.println("Exiting instructor menu...");
                        loggedInUserType = null;  // Log out from client menu
                    }
                    break;

                //
                //
                // Exit - Normal & ADMIN
                // 
                //
                case "4":
                    if (loggedInUserType == null) {
                        // Terminate logic for normal menu
                        System.out.println("Exiting...");
                        sc.close();
                        return;
                    } else if (loggedInUserType == UserType.ADMIN){
                        System.out.println("Exiting admin menu...");
                        loggedInUserType = null;  // Log out from admin menu
                    } else if (loggedInUserType == UserType.CLIENT){
                        System.out.println("Choice error.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void menu() {
        System.out.println("WELCOME");
        System.out.println("1. Log in");
        System.out.println("2. Sign up");
        System.out.println("3. View Lessons");
        System.out.println("4. Terminate");
        System.out.print("Choice: ");
    }

    private static void adminMenu() {
        System.out.println("ADMIN MENU");
        System.out.println("1. Add Offering");
        System.out.println("2. View Bookings");
        System.out.println("3. View Lessons");
        System.out.println("4. Log out");
        System.out.print("Choice: ");
    }
    private static void clientMenu() {
        System.out.println("CLIENT MENU");
        System.out.println("1. View my Bookings");
        System.out.println("2. View Lessons");
        System.out.println("3. Log out");
        System.out.print("Choice: ");
    }
    private static void instructorMenu() {
        System.out.println("INSTRUCTOR MENU");
        System.out.println("1. View my Lessons");
        System.out.println("2. View Offerings");
        System.out.println("3. Log out");
        System.out.print("Choice: ");
    }

    private static void signUp(Scanner sc) {
        System.out.print("Sign up as instructor or client? ");
        String choice = sc.next();

        switch (choice.toLowerCase()) {
            case "instructor":
                System.out.print("Specialization: ");
                String specialization = sc.next();
                sc.nextLine(); // Skip line
                System.out.print("Name: ");
                String name = sc.next();
                System.out.print("Phone Number: ");
                long phoneNumber = sc.nextLong();
                System.out.print("Age: ");
                int age = sc.nextInt();
                System.out.print("Username: ");
                String username = sc.next();
                sc.nextLine(); // Skip line
                System.out.print("Password: ");
                String password = sc.next();

                signUpInstructor(name, phoneNumber, age, username, password, specialization);
                break;
            case "client":
                System.out.print("Name: ");
                name = sc.next();
                System.out.print("Phone Number: ");
                phoneNumber = sc.nextLong();
                System.out.print("Age: ");
                age = sc.nextInt();
                System.out.print("Username: ");
                username = sc.next();
                sc.nextLine(); // Skip line
                System.out.print("Password: ");
                password = sc.next();

                signUpClient(name, phoneNumber, age, username, password);
                break;
            default:
                System.out.println("Invalid choice for sign up.");
        }
    }

    private static void addOffering(Scanner sc) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println("Enter the date and time for the schedule (format: yyyy-MM-dd HH:mm): ");
        String input = sc.nextLine();
        Schedule schedule = null;

        try {
            LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
            schedule = new Schedule(dateTime);
            System.out.println("Schedule created with date and time: " + schedule.getDateTime());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date and time format. Please use the format: yyyy-MM-dd HH:mm");
        }

        System.out.print("What type of lesson (eg: yoga, boxing, etc.): ");
        String type = sc.nextLine();

        System.out.print("Individual or group: ");
        LessonMode mode = LessonMode.valueOf(sc.next().toUpperCase());

        int capacity = 1;
        if (mode == LessonMode.GROUP) {
            System.out.print("Capacity: ");
            capacity = sc.nextInt();
        }

        System.out.print("What is the name of the location: ");
        String locationName = sc.next().toLowerCase();
        sc.nextLine();

        boolean locationExists = false;
        Location location = null;

        for (Location loc : locations) {
            if (loc.getName().equalsIgnoreCase(locationName)) {
                locationExists = true;
                location = loc;
                break;
            }
        }

        if (!locationExists) {
            System.out.print("Location doesn't exist. In which city is it located: ");
            String city = sc.next();
            location = new Location(locationName, city);
        }

        Offering offering = new Offering(location, schedule, type, mode, capacity);
        offerings.add(offering);
        System.out.println("Offering added: " + offering.toString());
    }

    private static void clientViewBookings(User client, Scanner sc) {
        System.out.println("Viewing bookings...");
        Client c = (Client) client;
        List<Booking> myBookings = c.getBookings();
        int count = 0;
        for(Object b: myBookings){
            System.out.println(count++ + ". \n " + b.toString());
        }
        System.out.println("Would you like to cancel a booking (press 0 for no): ");
        int choice = sc.nextInt();

        // Check if the choice is valid
        if (choice > 0 && choice <= myBookings.size()) {
            Booking selectedBooking = myBookings.get(choice - 1); // Get the lesson at the chosen position
            System.out.println("You selected: " + selectedBooking.getLesson().toString());
            
            //cancel booking
            c.cancelBooking(selectedBooking.getLesson());
            System.out.println("Lesson Canceled! ");

        } else {
            System.out.println("No lesson canceled.");
        }
    }
    
    private static void adminViewBookings(Scanner sc){
        System.out.println("Here is a list of all the clients. For which client would you like to view their bookings? (enter username or 0 to cancel)");
        for (Map.Entry<Pair<UserType, String>, User> entry : userMap.entrySet()){
            Pair<UserType, String> key = entry.getKey();
            User user = entry.getValue();
            
            // Check if the user type is CLIENT
            if (key.getKey() == UserType.CLIENT) {
                System.out.println("Client Name: " + user.getUsername());
            }
            System.out.print("Enter username: ");
            String clientUsername = sc.next();
            if(clientUsername.equals("0")){
                return;
            }

            Pair<UserType, String> clientkey = new Pair<>(UserType.CLIENT, clientUsername);

            User client = userMap.get(clientkey);
            Client c = (Client)client;
            System.out.println("Here is a list of client " +client.getUsername() + "'s bookings: " );
            List<Booking> bookings = c.getBookings();
            for(Booking b : bookings){
                System.out.println(b.getLesson().toString());
                System.out.println();
            }
        }

    }

    private static void instructorViewOfferings(Scanner sc, User user){
        System.out.println("Viewing available Offerings: ");
        int count = 0;
        Instructor instructor = (Instructor) user;
        for(Offering offering : offerings){
            System.out.println(count++ + ". \n" + offering.toString());
        }
        System.out.println("Which offering Would you like to take on? (press 0 for no): ");
        int choice = sc.nextInt();
        if (choice > 0 && choice <= offerings.size()) {
            Offering selectedOffering = offerings.get(choice - 1); // Get the lesson at the chosen position
            System.out.println("You selected: " + selectedOffering.toString());

            System.out.print("Confirm choice (1 for yes, 0 for no): ");
            choice = sc.nextInt();
            if(choice == 1){
                Lesson newLesson = new Lesson(selectedOffering, instructor);
                lessons.add(newLesson);
                System.out.println("Lesson added! \n" + newLesson.toString());
            }
        } else {
            System.out.println("No offering chosen.");
        }
    }

    private static void viewLessons() {
        System.out.println("Viewing lessons...");
        for(Lesson l: lessons){
            if(l.getCapacity()==0){
                continue;
            }
            System.out.println(l.toString());
        }
    }

    private static void clientBookLesson(Client currentClient, Scanner sc){
        System.out.println("Available Lessons: ");
        int count = 0;
        for(Lesson l: lessons){
            if(l.getCapacity()==0){
                continue;
            }
            System.out.println(count++ + ". \n" + l.toString());
        }

        System.out.print("Which lesson would you like to book (press 0 for none): ");
        int choice = sc.nextInt();

            // Check if the choice is valid
        if (choice > 0 && choice <= lessons.size()) {
            Lesson selectedLesson = lessons.get(choice - 1); // Get the lesson at the chosen position
            System.out.println("You selected: " + selectedLesson.toString());
            
            //create booking
            currentClient.bookLesson(selectedLesson);
            System.out.println("Lesson Booked! ");

        } else {
            System.out.println("No lesson booked.");
        }

    }

    public static boolean signUpClient(String name, long phone, int age, String user, String pass) {
        try {
            User tempClient = new Client(name, phone, age, user, pass, null);
            Pair<UserType, String> key = new Pair<>(UserType.CLIENT, tempClient.getUsername());
            if (!userMap.containsKey(key)) {
                userMap.put(key, tempClient);
                return true;
            } else {
                System.out.println("A client with this username already exists.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error in signing up client");
            return false;
        }
    }

    public static boolean signUpInstructor(String name, long phone, int age, String user, String pass, String specialization) {
        try {
            User tempInstructor = new Instructor(specialization, name, phone, age, user, pass);
            Pair<UserType, String> key = new Pair<>(UserType.INSTRUCTOR, tempInstructor.getUsername());
            if (!userMap.containsKey(key)) {
                userMap.put(key, tempInstructor);
                return true;
            } else {
                System.out.println("An instructor with this username already exists.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error in signing up instructor");
            return false;
        }
    }
}