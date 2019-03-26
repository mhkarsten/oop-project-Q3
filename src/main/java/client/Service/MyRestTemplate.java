package client.Service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class MyRestTemplate extends RestTemplate {

    public MyRestTemplate() {
        UserSession session = UserSession.getInstace();
        this.getInterceptors().add(new BasicAuthenticationInterceptor(
            session.getUserName(), session.getPassword()));
    }

    public static HttpHeaders getBaseHeaders(MediaType type) {
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(new MediaType[] {MediaType.APPLICATION_XML}));
        headers.setContentType(MediaType.APPLICATION_XML);

        return headers;
    }
}
