package code;
import java.util.ArrayList;
import java.util.List;

public class Client extends User {
    private Guardian guardian;
    private List<Booking> bookings;

    public Client(String name, long phone, int age, String user, String pass, Guardian guardian) {
        super(name, phone, UserType.CLIENT, age, user, pass);
        this.guardian = guardian;
        this.bookings = new ArrayList<>();
    }

    public Guardian getGuardian() {
        return guardian;
    }

    public void setGuardian(Guardian guardian) {
        this.guardian = guardian;
    }

    // View all bookings for the client
    public List<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }

    // Book a lesson for the client
    public boolean bookLesson(Lesson lesson) {
        if (bookings.stream().noneMatch(b -> b.getLesson().equals(lesson))) { // Avoid duplicate bookings
            bookings.add(new Booking(lesson, this));
            return true;
        }
        return false; // Lesson is already booked by the client
    }

    // Cancel a booking for the client
    public boolean cancelBooking(Lesson lesson) {
        return bookings.removeIf(b -> b.getLesson().equals(lesson)); // Removes the booking if found
    }
}
