package server.model;

import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import server.model.Achievement;

@Entity
@Table(name = "users")
public class User {
    private long id;
    private String name;
    private int points;

    /*
    @JoinTable(
            name = "user_achievements",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "achievement_id"))
    */
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return id == user.id
                && points == user.points
                && Objects.equals(name, user.name);
    }

    @Id
    @GeneratedValue(generator = "user_generator")
    @SequenceGenerator(
            name = "user_generator",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    public long getID() {
        return id;
    }

    public void setID(long userID) {
        this.id = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String userName) {
        this.name = userName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int userPoints) {
        this.points = userPoints;
    }
}
