package lt.ws.mif;

/**
 * Created by Romas on 5/19/2017.
 */
public class Error {

    private String message;

    public Error() {
    }

    public Error(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
