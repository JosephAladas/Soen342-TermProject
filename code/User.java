package code;

public abstract class User {
    private String name;
    private long phone; 
    private UserType type;
    private int age;
    private String username;
    private String password;

    public User (){}
    public User(String name, long phone, UserType type, int age, String user, String pass) {
        this.name = name;
        this.phone = phone;
        this.type = type;
        this.age = age;
        this.username = user;
        this.password = pass;
    }

    public String getName() {
        return name;
    }

    public UserType getType() {
        return type;
    }

    public long getPhone() {
        return phone;
    }

    public int getAge(){
        return age;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setType(UserType type) {
        this.type = type;
    }
    public void setPhone(long phone) {
        this.phone = phone;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
