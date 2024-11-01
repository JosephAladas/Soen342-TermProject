package code;
import java.util.HashMap;
import java.util.Map;

public class UserFactory {
    private static Map<String, User> users = new HashMap<>();

    // Static block to initialize some sample users for testing purposes
    static {
        // Initialize an Admin instance (using singleton pattern)
        users.put("admin", Admin.getInstance());

        // Initialize a sample Client and Instructor
        Client client = new Client("Alice", 1234567890L, UserType.CLIENT, 28, "clientUser", "clientPass", null);
        Instructor instructor = new Instructor("Yoga", "Bob", 2345678901L, UserType.INSTRUCTOR, 35, "instructorUser", "instructorPass");

        // Add users to the factory
        users.put(client.getUsername(), client);
        users.put(instructor.getUsername(), instructor);
    }

    // Factory method to authenticate and return the appropriate user based on credentials
    public static User login(String username, String password) {
        User user = users.get(username);

        // Check credentials and return the user if valid, otherwise return null
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null; // Invalid credentials
    }

    // Method to add new users to the system (if needed)
    public static void addUser(User user) {
        users.put(user.getUsername(), user);
    }
}
