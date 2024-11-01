package code;
public class Booking {
    private Lesson lesson;
    private Client client;

    public Booking(){}
        
    public Booking(Lesson lesson, Client client) {
        this.lesson = lesson;
        this.client = client;
    }
    public Lesson getLesson() {
        return lesson;
    }
    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    
    public void deleteBooking(Booking booking){
        booking = null;
    }
    
}
