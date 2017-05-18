package lt.ws.mif;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Romas on 5/19/2017.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid JWT token")
public class TokenException extends Exception {

    public TokenException(String message) {
        super(message);
    }
}
