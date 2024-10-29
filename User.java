
public abstract class User {
    private String name;
    private UserType type;
    private long phone; 
    private int age;

    public User (){}
    public User(String name, long phone, UserType type, int age) {
        this.name = name;
        this.phone = phone;
        this.type = type;
        this.age = age;
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
    
    
}
