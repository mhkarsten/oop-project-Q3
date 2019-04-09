package demo;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import server.model.Feat;
import server.model.User;

import java.net.URI;
import java.util.Date;

public class DemoApplication {
    public static void main(String[] args) {
        RestTemplate template=new RestTemplate();
        User user=new User("Mark","s3cretp4ssword");
        try {
            user=template.postForObject( "http://localhost:8080/auth/register", new HttpEntity<>(user),User.class);
        } catch (HttpClientErrorException exception) {
            System.out.println("The user already exists!");
        }
        //Set the proper basic authentication header for every request
        template.getInterceptors().add(new BasicAuthenticationInterceptor(user.getName(), user.getPassword()));
        template.postForEntity("http://localhost:8080/auth/login/"+user.getName(),new HttpEntity<>(user),User.class);
        System.out.println(user.toString());

        //Two ways of getting a user
        user=template.postForObject( "http://localhost:8080/users/byName/"+user.getName(), new HttpEntity<>(user),User.class);
        User userById=template.postForObject( "http://localhost:8080/users/"+user.getID(), new HttpEntity<>(user),User.class);

        Feat feat = new Feat(1,150,4, new Date(),null);
        template.postForLocation( "http://localhost:8080/users/5/feats/new", new HttpEntity<>(feat));
        User betterUser = template.getForObject( "http://localhost:8080/users/"+user.getID(),  User.class);

        System.out.println("Points of Mark before feat:"+user.getPoints()+
            "\n"+"Points of Mark after feat:"+betterUser.getPoints());

}
}
