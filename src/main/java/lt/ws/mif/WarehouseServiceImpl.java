package lt.ws.mif;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Romas on 5/7/2017.
 */
@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private RestOperations restTemplate;

    @Autowired
    private ItemRepository itemRepository;

    final String fooResourceUrl = "http://localhost:9001/warehouse/items";

    @Override
    public List<WarehouseAdresses> getAllwarehouseAddress() {
        List<WarehouseForm> returned = restTemplate.getForObject(fooResourceUrl, List.class);
        List<WarehouseAdresses> adresses = returned.stream().map(w -> new WarehouseAdresses(w.getId(), w.getCity(), w.getStreet(), w.getNumber())).collect(Collectors.toList());
        return adresses;
    }

    @Override
    public List<WarehouseForm> getAllwarehouseItemsInSpecificCity(String city) {
        WarehouseForm[] returned = restTemplate.getForObject(fooResourceUrl, WarehouseForm[].class);
        List<WarehouseForm> response = Arrays.asList(returned);
        List<WarehouseForm> filtered = response.stream().filter(w -> w.getCity().equals(city)).collect(Collectors.toList());
        return filtered;
    }


    @Override
    public boolean getSpecificItemFromWarehouseToShop(Long itemId) {
        String getItemUrl = "http://localhost:9001/warehouse/items/" + String.valueOf(itemId);
        try {
            WarehouseForm form = restTemplate.getForObject(getItemUrl, WarehouseForm.class);
            ItemEntity entity = new ItemEntity(form);
            itemRepository.save(entity);
            reduceQuantity(form, itemId);
            return true;
        } catch (HttpClientErrorException e) {
            return false;
        } catch (Exception e) {
            return false;
        }

    }

    private void reduceQuantity(WarehouseForm form, Long itemId) {
        String targetUrl = "http://localhost:9001/warehouse/items/" + String.valueOf(itemId);
        form.setQuantity(form.getQuantity() -1);
        HttpEntity<WarehouseForm> requestEntity = new HttpEntity<>(form, new HttpHeaders());
        restTemplate.exchange(targetUrl, HttpMethod.PUT, requestEntity ,ItemStateInWarehouse.class);
    }

    @Override
    public boolean sendItemBackTospecifixWarehouse(ItemInWarehouses item) {
        ItemEntity entity = itemRepository.findOne(item.getItemId());
        if(entity == null) {
            return false;
        }
        itemRepository.delete(item.getItemId());
        WarehouseForm warehouseForm = new WarehouseForm(entity, item.getCity(), item.getStreet(), item.getNumber());
        HttpEntity<WarehouseForm> request = new HttpEntity<>(warehouseForm);
        ItemStateInWarehouse entityState = restTemplate.postForObject(fooResourceUrl, request, ItemStateInWarehouse.class);
        if (entityState.equals(ItemStateInWarehouse.ITEM_ADDED_TO_WAREHOUSE)) {
            return true;
        }
        return false;

    }
}
