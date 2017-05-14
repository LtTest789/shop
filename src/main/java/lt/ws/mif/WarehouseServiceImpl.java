package lt.ws.mif;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;

import java.util.ArrayList;
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

    private String url = "http://warehouse:9001";

    final String fooResourceUrl = "http://warehouse:9001/warehouse/items";
//    final String fooResourceUrl = "http://localhost:9001/warehouse/items/";


    @Override
    public boolean addItemToWarehouses(ItemForm warehouseForms, long id) {
        warehouseForms.setItemId(id);
            resolveWarehouses(warehouseForms);
            return true;
//            if (entityState.equals(ItemStateInWarehouse.ITEM_ADDED_TO_WAREHOUSE)) {
//                return true;
//            } else {
//                return  false;
//            }
    }

    private void resolveWarehouses(ItemForm warehouseForms) {
        ItemStateInWarehouse entityState = ItemStateInWarehouse.FAILED_ADD_ITEM_TO_WAREHOUSE;
        for(WarehouseInfo warehouseinfo: warehouseForms.getWarehouses()) {
            WarehouseForm warehouseForm = new WarehouseForm(warehouseinfo);
            List<ItemInfo> tmpList = new ArrayList<>();
            tmpList.add(new ItemInfo(warehouseForms));
            warehouseForm.setItemFormList(tmpList);
            HttpEntity<WarehouseForm> request = new HttpEntity<>(warehouseForm);
            entityState = restTemplate.postForObject(fooResourceUrl, request, ItemStateInWarehouse.class);
        }
    }

    @Override
    public List<WarehouseForm> getAll() {
        WarehouseForm[] returned = restTemplate.getForObject(fooResourceUrl, WarehouseForm[].class);
        return Arrays.asList(returned);
    }

    public List<WarehouseForm> getOneItem(Long id) {
        String getItemUrl = fooResourceUrl + String.valueOf(id);
        try {
            WarehouseForm form = restTemplate.getForObject(getItemUrl, WarehouseForm.class);
            return Arrays.asList(form);
        } catch (HttpClientErrorException e) {
            return null;
        }
    }

    @Override
    public boolean updateItem(Long id, ItemForm itemForm) {
        WarehouseForm[] returned = restTemplate.getForObject(fooResourceUrl, WarehouseForm[].class);
        List<WarehouseForm> warehouseFormList = Arrays.asList(returned);
//        for(WarehouseForm wf : warehouseFormList) {
//            for(ItemInfo item : wf.getItemFormList()) {
//                if(item.getItemId().equals(id)) {
//                    item.setCountryOfManufactor(itemForm.getCountryOfManufactor());
//                    item.setDateOfManufactor(itemForm.getDateOfManufactor());
//                    item.setDescription(itemForm.getDescription());
//                    item.setItemName(itemForm.getItemName());
//                }
//            }
//
//            HttpEntity<WarehouseForm> requestEntity = new HttpEntity<>(wf, new HttpHeaders());
//            restTemplate.exchange(fooResourceUrl + wf.getId().toString(), HttpMethod.PUT, requestEntity, ItemStateInWarehouse.class);
//        }
        boolean flag = false;
        if(!itemForm.getWarehouses().isEmpty()) {
            for(WarehouseInfo warehouseinfo: itemForm.getWarehouses()) {
                for(WarehouseForm whf:warehouseFormList) {
                    if (!whf.getCity().equals(warehouseinfo.getCity())
                            || !whf.getNumber().equals(warehouseinfo.getNumber())
                            || !whf.getStreet().equals(warehouseinfo.getStreet())) {
                        flag = true;
                    } else {
                        flag = false;
                    }
                }
                if(flag) {
                    WarehouseForm warehouseForm = new WarehouseForm(warehouseinfo);
                    List<ItemInfo> tmpList = new ArrayList<>();
                    tmpList.add(new ItemInfo(itemForm, id));
                    warehouseForm.setItemFormList(tmpList);
                    HttpEntity<WarehouseForm> request = new HttpEntity<>(warehouseForm);
                    restTemplate.postForObject(fooResourceUrl, request, ItemStateInWarehouse.class);
                }

            }
        }
        return true;
    }


    @Override
    public boolean deleteItem(Long id) {
        WarehouseForm[] returned = restTemplate.getForObject(fooResourceUrl, WarehouseForm[].class);
        List<WarehouseForm> warehouseFormList = Arrays.asList(returned);
        for(WarehouseForm wf : warehouseFormList) {
            List<ItemInfo> filteredList = wf.getItemFormList().stream().filter(e -> !e.getItemId().equals(id)).collect(Collectors.toList());
            wf.setItemFormList(filteredList);
            HttpEntity<WarehouseForm> requestEntity = new HttpEntity<>(wf, new HttpHeaders());
            restTemplate.exchange(fooResourceUrl + wf.getId().toString(), HttpMethod.PUT, requestEntity, ItemStateInWarehouse.class);
        }
        return true;
    }


}
