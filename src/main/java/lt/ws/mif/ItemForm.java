package lt.ws.mif;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * Created by Romas on 2/11/2017.
 */
public class ItemForm implements Serializable {

    private Long itemId;

    @Size(min = 1, message = "Item 'Name' must be at least 1 symbol long.")
    private String itemName;

    private String description;

    private String countryOfManufactor;

    private String dateOfManufactor;

    private List<WarehouseInfo> warehouses;

    public ItemForm() {
    }

    public ItemForm(ItemEntity item) {
        this.itemId = item.getId();
        this.itemName = item.getItemName();
        this.description = item.getDescription();
        this.countryOfManufactor = item.getCountryOfManufacturing();
        this.dateOfManufactor = item.getDateOfManufacturing().toString();
    }

    public ItemForm(Long itemId, String itemName, String description, String dateOfManufactor, String countryOfManufactor) {
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

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public List<WarehouseInfo> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<WarehouseInfo> warehouses) {
        this.warehouses = warehouses;
    }

    @Override
    public String toString() {
        return "ItemForm{" +
                "itemName='" + itemName + '\'' +
                ", description='" + description + '\'' +
                ", countryOfManufactor='" + countryOfManufactor + '\'' +
                ", dateOfManufactor='" + dateOfManufactor + '\'' +
                '}';
    }
}
