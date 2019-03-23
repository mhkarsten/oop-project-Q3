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
    @Column(columnDefinition = "int default 0")
    private int points=0;
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

    @OneToMany(mappedBy="user")
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
    @PostLoad
    public void calculatePoints() {
        points=0;
        for(Feat x:feat) {
            points+=x.getPoints();
        }
    }

    public int getPoints() {
        return points;
    }
    public Set<Feat> getFeat() {
        return this.feat;
    }

    public void setFeat(Set<Feat> achievement) {
        this.feat = feat;
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
