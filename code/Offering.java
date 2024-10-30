package code;
public class Offering {
    private Location location;
    private Schedule schedule;
    private String type; // could become an enum
    private LessonMode mode; // "private" or "non-private"
    private int capacity;

    public Offering(){}

    public Offering(Location location, Schedule schedule, String type, LessonMode mode, int capacity) {
        this.location = location;
        this.schedule = schedule;
        this.type = type;
        this.mode = mode;
        this.capacity = capacity;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public Location getLocation() {
        return location;
    }

    public void setScheudle(Schedule schedule){
        this.schedule = schedule;
    }

    public void setLocation(Location location){
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LessonMode getMode() {
        return mode;
    }

    public void setMode(LessonMode mode) {
        this.mode = mode;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
