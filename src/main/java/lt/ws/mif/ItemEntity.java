package lt.ws.mif;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Romas on 2/11/2017.
 */
@Entity
@Table(name = "items")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "item_name", nullable = false, unique = true)
    private String itemName;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "country_of_manufacturing")
    private String countryOfManufacturing;

    @Column(name = "date_of_manufacturing")
    private LocalDate dateOfManufacturing;

    @Column(name = "price")
    private Long price;

    public ItemEntity() {
    }

    public ItemEntity(long id, String itemName, String description, String countryOfManufactor, LocalDate dateOfManufactor, Long price) {
        this.id = id;
        this.itemName = itemName;
        this.description = description;
        this.countryOfManufacturing = countryOfManufactor;
        this.dateOfManufacturing = dateOfManufactor;
        this.price = price;
    }

    public ItemEntity(WarehouseForm form) {
        this.itemName = form.getItemName();
        this.description = "not specified";
        this.countryOfManufacturing = "not specified";
        this.dateOfManufacturing = LocalDate.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getCountryOfManufacturing() {
        return countryOfManufacturing;
    }

    public void setCountryOfManufacturing(String countryOfManufacturing) {
        this.countryOfManufacturing = countryOfManufacturing;
    }

    public LocalDate getDateOfManufacturing() {
        return dateOfManufacturing;
    }

    public void setDateOfManufacturing(LocalDate dateOfManufacturing) {
        this.dateOfManufacturing = dateOfManufacturing;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }



}
