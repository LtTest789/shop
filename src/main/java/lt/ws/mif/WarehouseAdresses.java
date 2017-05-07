package lt.ws.mif;

/**
 * Created by Romas on 5/7/2017.
 */
public class WarehouseAdresses {

    private Long id;
    private String city;
    private String street;
    private String number;

    public WarehouseAdresses(Long id, String city, String street, String number) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
