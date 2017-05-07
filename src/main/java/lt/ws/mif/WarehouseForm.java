package lt.ws.mif;

import java.io.Serializable;

/**
 * Created by Romas on 5/6/2017.
 */
public class WarehouseForm implements Serializable{

    private Long id;
    private String itemName;
    private String city;
    private String street;
    private String number;
    private int quantity;

    public WarehouseForm() {
    }

    public WarehouseForm(Long id, String itemName, String city, String street, String number, int quantity) {
        this.id = id;
        this.itemName = itemName;
        this.city = city;
        this.street = street;
        this.number = number;
        this.quantity = quantity;
    }


    public WarehouseForm(ItemEntity entity, String city, String street, String number) {
        this.itemName = entity.getItemName();
        this.city = city;
        this.street = street;
        this.number = number;
        this.quantity = 1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "WarehouseItemForm{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
