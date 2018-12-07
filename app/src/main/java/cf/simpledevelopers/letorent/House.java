package cf.simpledevelopers.letorent;

/**
 * Created by Simple Dev on 11/24/2018.
 */

public class House {
    private String user_id,street_address,location,city,contact,description,dateposted;
    private int numOfRooms;

    public House() {
    }

    public House(String user_id, String street_address, String location, String city, String contact, String description, String dateposted, int numOfRooms) {
        this.user_id = user_id;
        this.street_address = street_address;
        this.location = location;
        this.city = city;
        this.contact = contact;
        this.description = description;
        this.dateposted = dateposted;
        this.numOfRooms = numOfRooms;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getStreet_address() {
        return street_address;
    }

    public String getLocation() {
        return location;
    }

    public String getCity() {
        return city;
    }

    public String getContact() {
        return contact;
    }

    public String getDescription() {
        return description;
    }

    public String getDateposted() {
        return dateposted;
    }

    public int getNumOfRooms() {
        return numOfRooms;
    }
}
