package server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(generator = "user_generator")
    @SequenceGenerator(
            name = "user_generator",
            sequenceName = "user_sequence",
            initialValue = 1
    )
    private long id;
    private String name;
    private int points;

    public User() {

    }

    /**
     * Constructor for the User class.
     * @param id The numeric id of the user
     * @param name The name of the user
     * @param points The points of the user
     */
    public User(long id, String name, int points) {

        this.id = id;
        this.name = name;
        this.points = points;
    }

    public long getUserID() {
        return id;
    }

    public void setUserID(long userID) {
        this.id = userID;
    }

    public String getUserName() {
        return name;
    }

    public void setUserName(String userName) {
        this.name = userName;
    }

    public int getUserPoints() {
        return points;
    }

    public void setUserPoints(int userPoints) {
        this.points = userPoints;
    }
}
