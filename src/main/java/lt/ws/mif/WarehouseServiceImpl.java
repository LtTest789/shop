package lt.ws.mif;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;

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

    private String url = "http://localhost:9001";

    final String fooResourceUrl = "http://localhost:9001/warehouse/items";

    @Override
    public List<WarehouseAdresses> getAllwarehouseAddress() {
        String uri = url + "/warehouse/items";
        WarehouseForm[] returned = restTemplate.getForObject(fooResourceUrl, WarehouseForm[].class);
        List<WarehouseForm> returnedList = Arrays.asList(returned);
        List<WarehouseAdresses> adresses = returnedList.stream().map(w -> new WarehouseAdresses(w.getId(), w.getCity(), w.getStreet(), w.getNumber())).collect(Collectors.toList());
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
        String uri = url + "/warehouse/items" + String.valueOf(itemId);
        try {
            WarehouseForm form = restTemplate.getForObject(uri, WarehouseForm.class);
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
        String uri = url + "/warehouse/items" + String.valueOf(itemId);
        form.setQuantity(form.getQuantity() - 1);
        HttpEntity<WarehouseForm> requestEntity = new HttpEntity<>(form, new HttpHeaders());
        restTemplate.exchange(uri, HttpMethod.PUT, requestEntity, ItemStateInWarehouse.class);
    }

    @Override
    public boolean sendItemBackTospecifixWarehouse(ItemInWarehouses item) {
        ItemEntity entity = itemRepository.findOne(item.getItemId());
        if (entity == null) {
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
