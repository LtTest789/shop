package lt.ws.mif;

import java.io.Serializable;

/**
 * Created by Romas on 5/7/2017.
 */
public class ItemInWarehouses implements Serializable{
    private long itemId;
    private String city;
    private String street;
    private String number;

    public ItemInWarehouses() {
    }

    public ItemInWarehouses(long itemId, String city, String street, String number) {
        this.itemId = itemId;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
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
