package lt.ws.mif;

import javax.validation.constraints.Size;

/**
 * Created by Romas on 5/14/2017.
 */
public class ItemInfo {
    private Long itemId;

    private String itemName;

    private String description;

    private String countryOfManufactor;

    private String dateOfManufactor;

    public ItemInfo() {
    }

    public ItemInfo(ItemForm warehouseForms) {
        this.itemId = warehouseForms.getItemId();
        this.itemName = warehouseForms.getItemName();
        this.description = warehouseForms.getDescription();
        this.countryOfManufactor = warehouseForms.getCountryOfManufactor();
        this.dateOfManufactor = warehouseForms.getDateOfManufactor().toString();
    }

    public ItemInfo(ItemForm warehouseForms, Long id) {
        this.itemId = id;
        this.itemName = warehouseForms.getItemName();
        this.description = warehouseForms.getDescription();
        this.countryOfManufactor = warehouseForms.getCountryOfManufactor();
        this.dateOfManufactor = warehouseForms.getDateOfManufactor().toString();
    }

    public ItemInfo(Long itemId, String itemName, String description, String dateOfManufactor, String countryOfManufactor) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.description =description;
        this.countryOfManufactor = countryOfManufactor;
        this.dateOfManufactor = dateOfManufactor;
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
