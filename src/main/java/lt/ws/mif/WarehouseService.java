package lt.ws.mif;

import java.util.List;

/**
 * Created by Romas on 5/7/2017.
 */
public interface WarehouseService {

    List<WarehouseAdresses> getAllwarehouseAddress();

    List<WarehouseForm> getAllwarehouseItemsInSpecificCity(String city);

    boolean getSpecificItemFromWarehouseToShop(Long itemId);

    boolean sendItemBackTospecifixWarehouse(ItemInWarehouses item);
}
