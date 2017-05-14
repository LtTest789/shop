package lt.ws.mif;

/**
 * Created by Romas on 5/14/2017.
 */
public class Operationresponse {
    private Long itemId;
    private boolean status;

    public Operationresponse(Long itemId, boolean status) {
        this.itemId = itemId;
        this.status = status;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
