package server.model;

import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Formula;


@Entity
@Table(name = "users")
public class User {

    @Id
    private long id;
    private String name;

    //This is not an actual database attribute of user, though it appears as such here in java because whenever it is requested this query calculates the value.
    //Admittedly, this query got a bit complex. This was to ensure that users without feats get 0 points instead of NULL points which crashes the server
    @Formula("SELECT COALESCE(sum(feats.points),0) FROM feats RIGHT JOIN users ON (users.id = feats.user_id) GROUP BY users.id HAVING users.id=id")
    private int points;

    @ManyToMany
    @JoinTable(
            name = "user_achievement",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "achievement_id"))
    private Set<Achievement> achievement;

    @ManyToMany
    @JoinTable(
            name = "followers",
            joinColumns = @JoinColumn(name="follower",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="followed",referencedColumnName = "id"))
    private Set<User> following;

    @OneToMany
    private Set<Feat> feat;

    @ManyToMany(mappedBy = "following")
    private Set<User> follower;
    public User() {

    }
    /**
     * Constructor for the User class.
     *
     * @param id     The numeric id of the user
     * @param name   The name of the user
     */
    public User(long id, String name) {

        this.id = id;
        this.name = name;
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

    public Set<Achievement> getAchievements() {
        return this.achievement;
    }

    public void setAchievements(Set<Achievement> achievement) {
        this.achievement = achievement;
    }
    @JsonIgnore
    public Set<User> getFollowers() {
        return follower;
    }
    public void setFollower(Set<User> follower) {
        this.follower = follower;
    }
    @JsonIgnore
    public Set<User> getFollowing() {
        return following;
    }
    public void setFollowing(Set<User> following) {
        this.following = following;
    }
}
