package lt.ws.mif;

import java.util.List;

/**
 * Created by Romas on 5/7/2017.
 */
public interface WarehouseService {
    boolean addItemToWarehouses(ItemForm warehouseForms, long id);
    List<WarehouseForm> getAll();
    boolean updateItem(Long id, ItemForm itemForm);
    boolean deleteItem(Long id);
}
