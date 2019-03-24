package client;


import client.Service.UserSession;
import javafx.fxml.FXML;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

public class MainController {
    private RestTemplate restTemplate;

    public MainController() {
        UserSession session = UserSession.getInstace();
        this.restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(
            session.getUserName(), session.getPassword()));
    }

    public void clickMe(javafx.event.ActionEvent event) {

        try {
            String fooResourceUrl = "http://localhost:8080/";
            ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());

            System.out.println("Status: You have logged in!");

        } catch (Exception e) {
            System.out.println("tesfwef.");
        }

    }
}
