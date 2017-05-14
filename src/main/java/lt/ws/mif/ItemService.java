package lt.ws.mif;

import java.util.List;

/**
 * Created by Romas on 2/11/2017.
 */
public interface ItemService {
    Operationresponse saveItem(ItemForm itemForm);

    List<ItemForm> retrieveAllItems();

    ItemForm retrieveItem(Long itemId);

    boolean deleteItem(Long itemId);

    Operationresponse updateItem(Long itemId, ItemForm itemForm);
}
