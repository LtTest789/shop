package lt.ws.mif;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Romas on 2/11/2017.
 */
@RestController
@RequestMapping(path = "/shop")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(path = "/items", method = RequestMethod.POST)
    public ResponseEntity<ItemState> add(@RequestBody @Valid ItemForm itemForm, HttpServletResponse response) {
        Operationresponse result = itemService.saveItem(itemForm);
        if (result.isStatus()) {
            response.setHeader("Item", "/shop/items/" + result.getItemId().toString());
            return new ResponseEntity(ItemState.ITEM_ADDED_TO_DATABSE, HttpStatus.CREATED);
        }
        return new ResponseEntity(ItemState.ITEM_WITH_ALREADY_EXIST, HttpStatus.NOT_ACCEPTABLE);
    }

    @RequestMapping(path = "/items", method = RequestMethod.GET)
    public ResponseEntity<List<ItemForm>> getAllItems() {
        return new ResponseEntity<List<ItemForm>>(itemService.retrieveAllItems(), HttpStatus.OK);
    }

    @RequestMapping(path = "/items/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getItemById(@PathVariable("id") Long itemId) {
        ItemForm retrievedItem = itemService.retrieveItem(itemId);
        if (retrievedItem == null) {
            return new ResponseEntity<>(ItemStateInWarehouse.ITEM_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(itemService.retrieveItem(itemId), HttpStatus.FOUND);
    }

    @RequestMapping(path = "/items/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ItemState> deleteItem(@PathVariable("id") Long itemId) {
        if (itemService.deleteItem(itemId)) {
            return new ResponseEntity<>(ItemState.ITEM_DELETED_FROM_DATABASE, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(ItemState.ITEM_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/items/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ItemState> updateItem(@PathVariable("id") Long userId, @RequestBody @Valid ItemForm itemForm, HttpServletResponse response) {
        Operationresponse result = itemService.updateItem(userId, itemForm);
        if (result.isStatus()) {
            response.setHeader("Item", "/shop/items/" + result.getItemId().toString());
            return new ResponseEntity<>(ItemState.ITEM_UPDATED, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(ItemState.ITEM_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }
}
