package code;
public class Instructor extends User{
    private String specialization;

    public Instructor() {}

    public Instructor(String specialization, String name, long phone, UserType type, int age) {
        super(name, phone, type, age);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }


}
