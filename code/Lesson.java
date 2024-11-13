package code;
public class Lesson extends Offering{
    private Instructor instructor;

    public Lesson() {}

    public Lesson(Instructor instructor, Location location, Schedule schedule, String type, LessonMode mode, int capacity) {
        super(location, schedule, type, mode, capacity);
        this.instructor = instructor;
    }
    
    public Lesson(Offering offering, Instructor instructor) {
        super(offering.getLocation(), offering.getSchedule(), offering.getType(), offering.getMode(), offering.getCapacity());
        this.instructor = instructor;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
    
    @Override
    public String toString(){
        System.out.println(instructor.getName() + "Lesson given by:");
        super.toString(); 
        return " ";
    }
    

}
