package client.retrieve;

import client.model.Feat;
import client.service.MyRestTemplate;
import client.service.UrlEndPoints;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Arrays;

public class FeatRetrieve extends BaseRetrieve {

    /**
     * Method to get all the feats of a user.
     * @param userID id of the target user
     * @return returns the feats of a given user if successful
     */
    public ArrayList<Feat> getUserFeats(long userID) {
        try {
            //GETS ALL THE FEATS THAT THE USER HAS
            HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);
            HttpEntity<Feat[]> entity = new HttpEntity<>(headers);

            Object[] uriValues = new Object[] {userID};
            ResponseEntity<Feat[]> response = restTemplate.exchange(UrlEndPoints.Feat.URL_USERFEATS,
                HttpMethod.POST, entity, Feat[].class, uriValues);

            HttpStatus statusCode = response.getStatusCode();
            System.out.println("(Client Side) The http status code is: " + statusCode);

            Feat[] list = response.getBody();

            ArrayList<Feat> achList = new ArrayList<>();

            if (list != null) {

                achList.addAll(Arrays.asList(list));

                return achList;
            }
        } catch (HttpClientErrorException excp) {

        }
        System.out.println("(Client Side) Getting all feats of user failed.");
        return null;

    }
}
