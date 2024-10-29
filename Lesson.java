public class Lesson extends Offering{
    private Instructor instructor;

    public Lesson() {}

    public Lesson(Instructor instructor, Location location, Schedule schedule, String type, LessonMode mode, int capacity) {
        super(location, schedule, type, mode, capacity);
        this.instructor = instructor;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
    
    

}
