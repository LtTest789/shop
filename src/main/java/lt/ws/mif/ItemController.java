package lt.ws.mif;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public HttpStatus add(@RequestBody @Valid ItemForm itemForm) {
        if (itemService.saveItem(itemForm)) {
            return HttpStatus.ACCEPTED;
        }
        return HttpStatus.NOT_ACCEPTABLE;
    }

    @RequestMapping(path = "/items", method = RequestMethod.GET)
    public List<ItemForm> getAllItems() {
        return itemService.retrieveAllItems();
    }

    @RequestMapping(path = "/retrieveItem/{id}", method = RequestMethod.GET)
    public ItemForm getItemById(@PathVariable("id") Long itemId) {
        return itemService.retrieveItem(itemId);
    }

    @RequestMapping(path = "/deleteItem/{id}", method = RequestMethod.DELETE)
    public HttpStatus deleteItem(@PathVariable("id") Long itemId) {
        if (itemService.deleteItem(itemId)) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @RequestMapping(path = "/updateItem/{id}", method = RequestMethod.PUT)
    public HttpStatus updateItem(@PathVariable("id") Long userId, @RequestBody @Valid ItemForm itemForm) {
        if (itemService.updateItem(userId, itemForm)) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
