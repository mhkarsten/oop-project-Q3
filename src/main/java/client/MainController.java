package client;


import client.Service.UrlEndPoints;
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
            String url = UrlEndPoints.BASE_URL;
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            System.out.println("Status: You have logged in!");

        } catch (Exception e) {
            System.out.println("tesfwef.");
        }

    }
}
