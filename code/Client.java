package code;
public class Client extends User{
    private Guardian guardian;

    public Client(){}

    public Client(String name, long phone, UserType type, int age, Guardian guardian) {
        super(name, phone, type, age);
        this.guardian = guardian;
    }

    public Guardian getGuardian() {
        return guardian;
    }

    public void setGuardian(Guardian guardian) {
        this.guardian = guardian;
    }
    
}
