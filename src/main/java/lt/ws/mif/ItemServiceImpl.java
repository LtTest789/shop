package lt.ws.mif;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Created by Romas on 2/11/2017.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private Optional<ItemEntity> ItemEntity;

    @Override
    public boolean saveItem(ItemForm itemForm) {
        ItemEntity entity = new ItemEntity();
        entity.setItemName(itemForm.getItemName());
        entity.setDescription(itemForm.getDescription());
        entity.setCountryOfManufacturing(itemForm.getCountryOfManufactor());
        entity.setDateOfManufacturing(LocalDate.parse(itemForm.getDateOfManufactor(), formatter));
        entity.setPrice(itemForm.getPrice());
        try {
            itemRepository.save(entity);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<ItemForm> retrieveAllItems() {
        List<ItemEntity> itemEntities = itemRepository.findAll();
        List<ItemForm> itemForms = new ArrayList<>();
        for(ItemEntity entity : itemEntities) {
            itemForms.add(new ItemForm(entity));
        }
        return itemForms;
    }

    @Override
    public ItemForm retrieveItem(Long itemId) {
        ItemEntity item = itemRepository.findOne(itemId);
        if(item == null) {
            return new ItemForm();
        }
        return new ItemForm(item);
    }

    @Override
    public boolean deleteItem(Long itemId) {
        if (!itemExist(itemId)) {
            return false;
        }
        ItemEntity deleteItem = ItemEntity.get();
        itemRepository.delete(deleteItem);
        return true;
    }

    @Override
    public boolean updateItem(Long itemId, ItemForm itemForm) {
        if (itemExist(itemId)) {
            ItemForm updatableForm = checkForNulls(itemForm);
            ItemEntity updateItem = ItemEntity.get();
            updateItem.setItemName(updatableForm.getItemName());
            updateItem.setDescription(updatableForm.getDescription());
            updateItem.setDateOfManufacturing(LocalDate.parse(updatableForm.getDateOfManufactor(), formatter));
            updateItem.setCountryOfManufacturing(updatableForm.getCountryOfManufactor());
            updateItem.setPrice(updatableForm.getPrice());
            itemRepository.save(updateItem);
            return true;
        } else {
            return false;
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
        if (itemForm.getPrice() == null) {
            itemForm.setPrice(ItemEntity.get().getPrice());
        }
        return itemForm;
    }
}
