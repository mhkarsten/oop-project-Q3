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

import java.util.ArrayList;
import java.util.Arrays;

public class FeatRetrive extends BaseRetrieve {

    /**
     * Method to get all the feats of a user.
     * @param userID id of the target user
     * @return
     */
    public ArrayList<Feat> getUserFeats(long userID) throws Exception {
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

        achList.addAll(Arrays.asList(list));

        if (list == null) {
            throw new Exception("(Client Side) Getting all Achievements failed.");
        }

        return achList;


    }
}
