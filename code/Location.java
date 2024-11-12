package code;
import java.util.ArrayList;
import java.util.List;

public class Location {
    private String name;
    private String city;
    private List<Offering> offerings;

    public Location(){}

    public Location(String name, String city) {
        this.name = name.toLowerCase();
        this.city = city.toLowerCase();
        this.offerings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean addOffering(Offering offering) {
        for (Offering existingOffering : offerings) {
            if (existingOffering.getSchedule().equals(offering.getSchedule())) {
                return false; // Conflict found
            }
        }
        offerings.add(offering);
        return true; // Offering added successfully
    }
}
