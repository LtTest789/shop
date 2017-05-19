package lt.ws.mif;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Romas on 5/19/2017.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "JWT WebService Down.")
public class RandomExection extends Exception {

    public RandomExection(String message) {
            super(message);
        }
}

