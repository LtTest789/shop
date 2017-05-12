package lt.ws.mif;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Romas on 5/6/2017.
 */
@RestController
@RequestMapping(path = "/rest/warehouses/")
public class WarehouseController {

    @Autowired
    private RestOperations restTemplate;

    private String url = "http://193.219.91.103:9001";

    @Autowired
    private WarehouseService warehouseService;

    @RequestMapping(path = "/addresses", method = RequestMethod.GET)
    public List<WarehouseAdresses> getWarehousesAddresses() {
        return warehouseService.getAllwarehouseAddress();
    }

    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public List<WarehouseForm> getWarehousesAddressesinSpecificCity(@RequestParam("city") String city) {
        return warehouseService.getAllwarehouseItemsInSpecificCity(city);
    }

    @RequestMapping(path = "/items/to-shop", method = RequestMethod.POST)
    public ResponseEntity<ItemState> fromWarehouseToShop(@RequestParam("itemId") Long itemId, HttpServletResponse response) {
        if (warehouseService.getSpecificItemFromWarehouseToShop(itemId)) {
            response.setHeader("Item", "/shop/items");
            return new ResponseEntity(ItemState.ITEM_ADDED_TO_DATABSE, HttpStatus.CREATED);
        }
        return new ResponseEntity(ItemState.ITEM_WITH_ALREADY_EXIST, HttpStatus.NOT_ACCEPTABLE);
    }

    @RequestMapping(path = "/items/to-warehouse", method = RequestMethod.POST)
    public ResponseEntity<ItemState> fromShopToearehouse(@RequestBody ItemInWarehouses item, HttpServletResponse response) {
        if (warehouseService.sendItemBackTospecifixWarehouse(item)) {
            response.setHeader("Item", "/warehouse/items");
            return new ResponseEntity(ItemState.RETURNED_TO_WAREHOUSE, HttpStatus.CREATED);
        }
        return new ResponseEntity(ItemState.CANT_RETURN_ITEM, HttpStatus.NOT_ACCEPTABLE);
    }

    @RequestMapping(path = "/item", method = RequestMethod.GET)
    public List<WarehouseForm> getWarehouses() {
        String uri = url + "/warehouse/items";
        List<WarehouseForm> returned = restTemplate.getForObject(uri, List.class);
        return returned;
    }

    @RequestMapping(path = "/item/{id}", method = RequestMethod.GET)
    public ResponseEntity<WarehouseForm> getWarehousesItem(@PathVariable("id") Long itemId) {
        String getItemUrl = url + "/warehouse/items/" + String.valueOf(itemId);
        try {
            WarehouseForm form = restTemplate.getForObject(getItemUrl, WarehouseForm.class);
            return new ResponseEntity<>(form, HttpStatus.FOUND);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/item", method = RequestMethod.POST)
    public ResponseEntity<ItemStateInWarehouse> postToWarehouse(@RequestBody @Valid WarehouseForm warehouseForm, HttpServletResponse response) {
        HttpEntity<WarehouseForm> request = new HttpEntity<>(warehouseForm);
        String fooResourceUrl = url + "/warehouse/items";
        ItemStateInWarehouse entity = restTemplate.postForObject(fooResourceUrl, request, ItemStateInWarehouse.class);
        if (entity.equals(ItemStateInWarehouse.ITEM_ADDED_TO_WAREHOUSE)) {
            response.setHeader("warehouse-item", "/warehouses/item");
            return new ResponseEntity(entity, HttpStatus.CREATED);
        }
        return new ResponseEntity(entity, HttpStatus.NOT_ACCEPTABLE);
    }

    @RequestMapping(path = "/item/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ItemStateInWarehouse> putWarehousesItem(@PathVariable("id") Long itemId, @RequestBody @Valid WarehouseForm warehouseForm, HttpServletResponse response) {
        String targetUrl =  url + "/warehouse/items/" + String.valueOf(itemId);
        HttpEntity<WarehouseForm> requestEntity = new HttpEntity<>(warehouseForm, new HttpHeaders());
        ResponseEntity<ItemStateInWarehouse> form = restTemplate.exchange(targetUrl, HttpMethod.PUT, requestEntity, ItemStateInWarehouse.class);
        if (ItemStateInWarehouse.ITEM_DELETED_FROM_DATABASE.equals(form)) {
            return new ResponseEntity<>(form.getBody(), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(form.getBody(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/item/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ItemStateInWarehouse> deleteWarehousesItem(@PathVariable("id") Long itemId) {
        String targetUrl =  url +"/warehouse/items/" + String.valueOf(itemId);
        HttpEntity<?> requestEntity = new HttpEntity<Object>(new HttpHeaders());
        ResponseEntity<ItemStateInWarehouse> form = restTemplate.exchange(targetUrl, HttpMethod.DELETE, requestEntity, ItemStateInWarehouse.class);
        if (ItemStateInWarehouse.ITEM_DELETED_FROM_DATABASE.equals(form)) {
            return new ResponseEntity<>(form.getBody(), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(form.getBody(), HttpStatus.NOT_FOUND);
        }
    }

}
