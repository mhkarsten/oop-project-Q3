package client.serverCommunication;

import client.Service.UserSession;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class BaseController {

    protected RestTemplate restTemplate ;

    public BaseController() {
        this.restTemplate = new RestTemplate();


        UserSession session = UserSession.getInstace();
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(
            session.getUserName(), session.getPassword()));
    }

    public HttpHeaders getBaseHeaders(MediaType type) {
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(new MediaType[] {MediaType.APPLICATION_XML}));
        headers.setContentType(MediaType.APPLICATION_XML);

        return headers;
    }
}
