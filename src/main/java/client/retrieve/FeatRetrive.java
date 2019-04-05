package client.retrieve;

import client.Service.MyRestTemplate;
import client.Service.UrlEndPoints;
import client.model.Achievement;
import client.model.Feat;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.Arrays;

public class FeatRetrive extends BaseRetrieve {

    public ArrayList<Feat> getUserFeats(long userID) {
        //GETS ALL THE FEATS THAT THE USER HAS
        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);
        HttpEntity<Feat[]> entity = new HttpEntity<>(headers);

        Object[] uriValues = new Object[] {userID};

        ResponseEntity<Feat[]> response = restTemplate.exchange(UrlEndPoints.Feat.URL_USERFEATS,
            HttpMethod.POST, entity, Feat[].class, uriValues);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);

        if (statusCode == HttpStatus.OK) {
            Feat[] list = response.getBody();

            ArrayList<Feat> achList = new ArrayList<>();

            if (list != null) {

                achList.addAll(Arrays.asList(list));

                return achList;
            }
        }

        System.out.println("(Client Side) Getting all Achievements failed.");
        return null;
    }
}
