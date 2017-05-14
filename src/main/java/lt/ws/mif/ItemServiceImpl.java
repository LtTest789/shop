package lt.ws.mif;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by Romas on 2/11/2017.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private WarehouseService warehouseService;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private Optional<ItemEntity> ItemEntity;

    @Override
    public Operationresponse saveItem(ItemForm itemForm) {
        ItemEntity entity = new ItemEntity();
        entity.setItemName(itemForm.getItemName());
        entity.setDescription(itemForm.getDescription());
        entity.setCountryOfManufacturing(itemForm.getCountryOfManufactor());
        entity.setDateOfManufacturing(LocalDate.parse(itemForm.getDateOfManufactor(), formatter));
        try {
            ItemEntity itemEntity = itemRepository.save(entity);
            warehouseService.addItemToWarehouses(itemForm, itemEntity.getId());
            return new Operationresponse(entity.getId(), true);
        } catch (Exception e) {
            return new Operationresponse(0L, false);
        }
    }

    @Override
    public List<ItemForm> retrieveAllItems() {
        List<ItemEntity> itemEntities = itemRepository.findAll();
        List<WarehouseForm> formList = warehouseService.getAll();
        List<ItemForm> itemForms = new ArrayList<>();
        for(ItemEntity entity : itemEntities) {
            ItemForm itemForm = new ItemForm(entity);
            List<WarehouseInfo> warehouseInfos = resoveItems(entity.getId(), formList);
            itemForm.setWarehouses(warehouseInfos);
            itemForms.add(itemForm);
        }
        return itemForms;
    }

    private List<WarehouseInfo> resoveItems(long id, List<WarehouseForm> forms) {
        List<WarehouseInfo> warehouseInfos = new ArrayList<>();
        for(WarehouseForm wf : forms){
            wf.getItemFormList().stream().filter(e -> e.getItemId().equals(id)).forEach(e -> warehouseInfos.add(new WarehouseInfo(wf)));
        }
        return warehouseInfos;
    }

    @Override
    public ItemForm retrieveItem(Long itemId) {
        ItemEntity item = itemRepository.findOne(itemId);
        List<WarehouseForm> formList = warehouseService.getAll();
        List<WarehouseInfo> warehouseInfos = resoveItems(itemId, formList);
        if(item == null) {
            return null;
        }
        ItemForm itemForm = new ItemForm(item);
        itemForm.setWarehouses(warehouseInfos);
        return itemForm;
    }

    @Override
    public boolean deleteItem(Long itemId) {
        if (!itemExist(itemId)) {
            return false;
        }
        ItemEntity deleteItem = ItemEntity.get();
        warehouseService.deleteItem(deleteItem.getId());
        itemRepository.delete(deleteItem);
        return true;
    }

    @Override
    public Operationresponse updateItem(Long itemId, ItemForm itemForm) {
        if (itemExist(itemId)) {
            ItemForm updatableForm = checkForNulls(itemForm);
            ItemEntity updateItem = ItemEntity.get();
            updateItem.setItemName(updatableForm.getItemName());
            updateItem.setDescription(updatableForm.getDescription());
            updateItem.setDateOfManufacturing(LocalDate.parse(updatableForm.getDateOfManufactor(), formatter));
            updateItem.setCountryOfManufacturing(updatableForm.getCountryOfManufactor());
           try {
               warehouseService.updateItem(itemId, itemForm);
               itemRepository.save(updateItem);
               return new Operationresponse(updateItem.getId(), true);
           } catch (Exception e) {
               return new Operationresponse(0L, false);
           }
        } else {
            return new Operationresponse(0L, false);
        }

    }

    public boolean itemExist(Long userId) {
        ItemEntity = itemRepository.findById(userId);
        if (ItemEntity.isPresent()) {
            return true;
        }
        return false;
    }

    public ItemForm checkForNulls(ItemForm itemForm) {
        if (itemForm.getItemName() == null) {
            itemForm.setItemName(ItemEntity.get().getItemName());
        }
        if (itemForm.getCountryOfManufactor() == null) {
            itemForm.setCountryOfManufactor(ItemEntity.get().getCountryOfManufacturing());
        }
        if (itemForm.getDateOfManufactor() == null) {
            itemForm.setDateOfManufactor(ItemEntity.get().getDateOfManufacturing().toString());
        }
        if (itemForm.getDescription() == null) {
            itemForm.setDescription(ItemEntity.get().getDescription());
        }
        return itemForm;
    }
}
