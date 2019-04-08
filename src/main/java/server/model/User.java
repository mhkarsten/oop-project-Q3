package server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@SequenceGenerator(name = "user_seq", allocationSize = 1)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private long id;

    private String name;
    private String password;

    //    @ManyToMany
    @Column(columnDefinition = "int default 0")
    private int points = 0;

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(
        name = "user_achievement",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "achievement_id"))
    private Set<Achievement> unlockedAchievements;

    /**
     * This is a more intricate part of the user class. As the User class participates at both ends of the FOLLOW relation,
     * it both maps other User objects and is mapped by other User objects (Compare this to the @ManyToMany user_achievement relation for instance)
     */
    @ManyToMany
    @JoinTable(
        name = "followers",
        joinColumns = @JoinColumn(name = "follower", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "followed", referencedColumnName = "id"))
    private Set<User> following;
    @ManyToMany(mappedBy = "following", cascade = CascadeType.ALL)
    private Set<User> follower;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Feat> feats;


    public User() {

    }

    /**
     * Constructor for the User class.
     *
     * @param id   The numeric id of the user
     * @param name The name of the user
     */
    public User(long id, String name) {

        this.id = id;
        this.name = name;
    }

    /**
     * Constructor for the User class.
     *
     * @param name The name of the user
     */
    public User(String name, String password) {
        this.name = name;
        this.password = password;
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

    /**
     * The @PostLoad annotation ensures that whenever the database changes, feats are added etc, the user's points are updated.
     */
    @PostLoad
    public void calculatePoints() {
        points = 0;
        for (Feat x : feats) {
            points += x.getPoints();
        }
    }

    public int getPoints() {
        return points;
    }

    //Mainly here for debugging, does not mean much
    public void setPoints(int points) {
        this.points = points;
    }

    public void addFeat(Feat feat) {
        this.feats.add(feat);
    }

    public Set<Feat> getFeats() {
        return this.feats;
    }

    public void setFeats(Set<Feat> achievement) {
        this.feats = feats;
    }

    public Set<Achievement> getAchievements() {
        return this.unlockedAchievements;
    }

    public void addAchievement(Achievement achievement) {
        unlockedAchievements.add(achievement);
    }

    public void followUser(User user) {
        following.add(user);
    }

    public void addFollower(User user) {
        follower.add(user);
    }

    public void setAchievements(Set<Achievement> achievement) {
        this.unlockedAchievements = achievement;
    }

    //To prevent recursive trouble when returning a user object (a user having followers that have followers that have followers...), the JsonIgnore
    //annotation is used. Note that the reason that this is here is that only public fields are included in the Json serialization
    @JsonIgnore
    public Set<User> getFollowers() {
        return follower;
    }

    public void setFollower(Set<User> follower) {
        this.follower = follower;
    }

    //To prevent recursive trouble when returning a user object (a user following users that follow users that follow users...), the JsonIgnore
    //annotation is used.
    @JsonIgnore
    public Set<User> getFollowing() {
        return following;
    }

    public void setFollowing(Set<User> following) {
        this.following = following;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
