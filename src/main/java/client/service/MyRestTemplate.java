package client.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.Arrays;

@Service
public class MyRestTemplate extends RestTemplate {

    /**
     * Fetches the username and password from the user session and sets an Basic authentication Interceptor based on the values.
     * Subsequent HTTP requests now automatically add basic authentication headers.
     */
    public MyRestTemplate() {
        UserSession session = UserSession.getInstance();
        this.getInterceptors().add(new BasicAuthenticationInterceptor(
            session.getUserName(), session.getPassword()));

        DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory(UrlEndPoints.BASE_URL);
        this.setUriTemplateHandler(uriBuilderFactory);
    }

    /**
     * For alernative urls fetches the username and password from the user session and sets an Basic authentication Interceptor based on the values.
     * Subsequent HTTP requests now automatically add basic authentication headers.
     * @param alternativeBaseUrl the alternative url
     */
    public MyRestTemplate(String alternativeBaseUrl) {
        UserSession session = UserSession.getInstance();
        this.getInterceptors().add(new BasicAuthenticationInterceptor(
            session.getUserName(), session.getPassword()));

        DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory(alternativeBaseUrl);
        this.setUriTemplateHandler(uriBuilderFactory);
    }

    /**
     * Helper function to provide the accepted mediaType headers.
     * @param type the type of MediaType
     * @return Sets the accept and send content headers and returns these.
     */
    public static HttpHeaders getBaseHeaders(MediaType type) {
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(new MediaType[] {type}));
        headers.setContentType(type);

        return headers;
    }
}
