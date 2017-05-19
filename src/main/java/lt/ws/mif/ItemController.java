package lt.ws.mif;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private LoginService loginService;

    @RequestMapping(path = "/items", method = RequestMethod.POST)
    public ResponseEntity<ItemState> add(@RequestBody @Valid ItemForm itemForm, HttpServletResponse response, HttpServletRequest request) throws TokenException, RandomExection {
        String header = request.getHeader("Authorization");
        if(loginService.sendToken(header)) {
            Operationresponse result = itemService.saveItem(itemForm);
            if (result.isStatus()) {
                response.setHeader("Item", "/shop/items/" + result.getItemId().toString());
                return new ResponseEntity(ItemState.ITEM_ADDED_TO_DATABSE, HttpStatus.CREATED);
            }
            return new ResponseEntity(ItemState.ITEM_ALREADY_EXIST, HttpStatus.NOT_ACCEPTABLE);
        }
        throw new TokenException("Invalid JWT token!");
    }

    @RequestMapping(path = "/items", method = RequestMethod.GET)
    public ResponseEntity<List<ItemForm>> getAllItems(HttpServletRequest request) throws TokenException, RandomExection {
        String header = request.getHeader("Authorization");
        if(loginService.sendToken(header)){
            return new ResponseEntity<List<ItemForm>>(itemService.retrieveAllItems(), HttpStatus.OK);
        }
        throw new TokenException("Invalid JWT token!");
    }

    @RequestMapping(path = "/items/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getItemById(@PathVariable("id") Long itemId, HttpServletRequest request) throws TokenException, RandomExection {
        String header = request.getHeader("Authorization");
        if(loginService.sendToken(header)) {
            ItemForm retrievedItem = itemService.retrieveItem(itemId);
            if (retrievedItem == null) {
                return new ResponseEntity<>(ItemStateInWarehouse.ITEM_NOT_FOUND, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(itemService.retrieveItem(itemId), HttpStatus.FOUND);
        }
        throw new TokenException("Invalid JWT token!");
    }

    @RequestMapping(path = "/items/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ItemState> deleteItem(@PathVariable("id") Long itemId, HttpServletRequest request) throws TokenException, RandomExection {
        String header = request.getHeader("Authorization");
        if(loginService.sendToken(header)) {
            if (itemService.deleteItem(itemId)) {
                return new ResponseEntity<>(ItemState.ITEM_DELETED_FROM_DATABASE, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(ItemState.ITEM_NOT_FOUND, HttpStatus.NOT_FOUND);
            }
        }
        throw new TokenException("Invalid JWT token!");
    }

    @RequestMapping(path = "/items/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ItemState> updateItem(@PathVariable("id") Long userId, @RequestBody @Valid ItemForm itemForm, HttpServletResponse response, HttpServletRequest request) throws TokenException, RandomExection {
        String header = request.getHeader("Authorization");
        if(loginService.sendToken(header)) {
            Operationresponse result = itemService.updateItem(userId, itemForm);
            if (result.isStatus()) {
                response.setHeader("Item", "/shop/items/" + result.getItemId().toString());
                return new ResponseEntity<>(ItemState.ITEM_UPDATED, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(ItemState.ITEM_NOT_FOUND, HttpStatus.NOT_FOUND);
            }
        }
        throw new TokenException("Invalid JWT token!");
    }
}
