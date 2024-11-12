package code;
import java.util.ArrayList;
import java.util.List;

public class Instructor extends User {
    private String specialization;
    private final List<Lesson> lessons;

    public Instructor(String specialization, String name, long phone, int age, String user, String pass) {
        super(name, phone, UserType.INSTRUCTOR, age, user, pass);
        this.specialization = specialization;
        this.lessons = new ArrayList<>();
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    // Assigns a lesson to the instructor
    public void addLesson(Lesson lesson) {
        if (!lessons.contains(lesson)) { // Avoid duplicate lessons
            lessons.add(lesson);
            return;
        }
        System.out.println("Lesson already exists");
    }

    // Returns a list of lessons for this instructor
    public List<Lesson> getLessons() {
        return new ArrayList<>(lessons);
    }


}

