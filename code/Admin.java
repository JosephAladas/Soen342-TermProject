package code;

public class Admin extends User {
    // Singleton instance
    private static Admin instance;

    
    private Admin() {
        super("Admin", 555, UserType.ADMIN, 24, "super", "super");
    }

    // Method to get the single instance of Admin
    public static Admin getInstance() {
        if (instance == null) {
            instance = new Admin();
        }
        return instance;
    }
}
