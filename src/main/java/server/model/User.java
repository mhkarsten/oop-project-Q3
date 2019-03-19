package server.model;

import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "users")
public class User {

    @Id
    private long id;
    private String name;
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

    @ManyToMany(mappedBy = "following")
    private Set<User> follower;
    public User() {

    }

    /**
     * Constructor for the User class.
     *
     * @param id     The numeric id of the user
     * @param name   The name of the user
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
