package lt.ws.mif;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Romas on 2/11/2017.
 */
public class ItemForm implements Serializable {

    @Size(min = 1)
    private String itemName;

    private String description;

    private String countryOfManufactor;

    private String dateOfManufactor;

    private Long price;

    public ItemForm() {
    }

    public ItemForm(ItemEntity item) {
        this.itemName = item.getItemName();
        this.description = item.getDescription();
        this.countryOfManufactor = item.getCountryOfManufacturing();
        this.dateOfManufactor = item.getDateOfManufacturing().toString();
        this.price = item.getPrice();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountryOfManufactor() {
        return countryOfManufactor;
    }

    public void setCountryOfManufactor(String countryOfManufactor) {
        this.countryOfManufactor = countryOfManufactor;
    }

    public String getDateOfManufactor() {
        return dateOfManufactor;
    }

    public void setDateOfManufactor(String dateOfManufactor) {
        this.dateOfManufactor = dateOfManufactor;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ItemForm{" +
                "itemName='" + itemName + '\'' +
                ", description='" + description + '\'' +
                ", countryOfManufactor='" + countryOfManufactor + '\'' +
                ", dateOfManufactor='" + dateOfManufactor + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
