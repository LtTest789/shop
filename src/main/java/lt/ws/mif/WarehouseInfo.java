package lt.ws.mif;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Romas on 5/14/2017.
 */
public class WarehouseInfo {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    private String city;
    private String street;
    private String number;

    public WarehouseInfo() {
    }

    public WarehouseInfo(Long id, String city, String street, String number) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.number = number;
    }


    public WarehouseInfo(WarehouseForm wf) {
        this.id = wf.getId();
        this.city = wf.getCity();
        this.street = wf.getStreet();
        this.number = wf.getNumber();
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
