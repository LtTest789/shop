package lt.ws.mif;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Romas on 5/6/2017.
 */
public class WarehouseForm implements Serializable{

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    private String city;
    private String street;
    private String number;
    private List<ItemInfo> itemFormList;

    public WarehouseForm() {
    }

    public WarehouseForm(Long id, String city, String street, String number) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public WarehouseForm(WarehouseInfo warehouseinfo) {
        this.city = warehouseinfo.getCity();
        this.street = warehouseinfo.getStreet();
        this.number = warehouseinfo.getNumber();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public List<ItemInfo> getItemFormList() {
        return itemFormList;
    }

    public void setItemFormList(List<ItemInfo> itemFormList) {
        this.itemFormList = itemFormList;
    }

    @Override
    public String toString() {
        return "WarehouseItemForm{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
