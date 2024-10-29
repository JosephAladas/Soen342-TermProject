import java.time.LocalDateTime;

public class main {
    public static void main(String[] args) {
        Location location = new Location("Room A", "Montreal");

        Schedule schedule1 = new Schedule(LocalDateTime.of(2024, 10, 30, 10, 0));
        Offering offering1 = new Offering(location, schedule1, "Yoga", LessonMode.INDIVIDUAL, 1);

        Schedule schedule2 = new Schedule(LocalDateTime.of(2024, 10, 30, 10, 0));
        Offering offering2 = new Offering(location, schedule2, "Pilates", LessonMode.INDIVIDUAL, 1);

        boolean added1 = location.addOffering(offering1);
        System.out.println("Offering 1 added: " + added1); // Should print true

        boolean added2 = location.addOffering(offering2);
        System.out.println("Offering 2 added: " + added2); // Should print false (conflict)
    }
}
