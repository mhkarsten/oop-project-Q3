package client.Service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class MyRestTemplate extends RestTemplate {

    /**
     * Fetches the username and password from the user session and sets an Basic authentication Interceptor based on the values.
     * Subsequent HTTP requests now automatically add basic authentication headers.
     */
    public MyRestTemplate() {
        UserSession session = UserSession.getInstace();
        this.getInterceptors().add(new BasicAuthenticationInterceptor(
            session.getUserName(), session.getPassword()));
    }

    /**
     * Helper function to provide the accepted mediaType headers.
     * @param type
     * @return
     */
    public static HttpHeaders getBaseHeaders(MediaType type) {
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(new MediaType[] {MediaType.APPLICATION_XML}));
        headers.setContentType(MediaType.APPLICATION_XML);

        return headers;
    }
}
