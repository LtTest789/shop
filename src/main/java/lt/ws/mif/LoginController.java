package lt.ws.mif;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Romas on 5/18/2017.
 */
@RestController
@RequestMapping(path = "/shop")
public class LoginController {

//    final String fooResourceUrl = "http://localhost:9003/login";
    final String fooResourceUrl = "http://jwt:9003/login";

    @Autowired
    private RestOperations restTemplate;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> add(@RequestBody @Valid Login login, HttpServletRequest request) {

        HttpEntity<Login> rt = new HttpEntity<>(login);
        try {
            String http = restTemplate.postForObject(fooResourceUrl, rt, String.class);
            return new ResponseEntity<>("Bearer "+http, HttpStatus.OK);
        } catch (HttpClientErrorException errorException) {
            return new ResponseEntity<>("Wrong username or password!", HttpStatus.BAD_REQUEST);
        }
    }

}
