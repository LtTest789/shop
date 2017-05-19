package lt.ws.mif;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestOperations;

/**
 * Created by Romas on 5/19/2017.
 */
@Service
public class LoginServiceImpl implements LoginService {

//    final String fooResourceUrl = "http://localhost:9003/auth";
    final String fooResourceUrl = "http://jwt:9003/auth";

    @Autowired
    private RestOperations restTemplate;

    @Override
    public boolean sendToken(String token) throws RandomExection {
        try {

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", token);
            HttpEntity<String> rt = new HttpEntity<>(headers);
            String http = restTemplate.postForObject(fooResourceUrl, rt, String.class);
            if (http.equals("OK")) {
                return true;
            }
            return false;
        } catch (HttpServerErrorException e) {
           throw new RandomExection("Jwt Webservice down");
        } catch (Exception e) {
            return false;
        }
    }

}
