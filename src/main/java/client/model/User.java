package client.model;

import server.model.Feat;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.PostLoad;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "users")
@SequenceGenerator(name = "user_seq", initialValue = 20,allocationSize = 1)
public class User {

    private long id;
    private String name;
    private String password;
    private int points = 0;
    private Set<Achievement> unlockedAchievements;

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
     * @param name The name of the user
     * @param password The password of the user (unhashed at this point)
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

    /**
     * method to add a feat to the user's feat set.
     * @param feat feat to be added
     */
    public void addFeat(Feat feat) {
        if (this.feats == null) {
            this.feats = new HashSet<>();
        }

        this.feats.add(feat);
    }

    public Set<Feat> getFeats() {
        return this.feats;
    }


    public Set<Achievement> getAchievements() {
        return this.unlockedAchievements;
    }

    /**
     * Method to add a achievement to the user's achievement set.
     * @param achievement the achievement to be added
     */
    public void addAchievement(Achievement achievement) {
        if (this.unlockedAchievements == null) {
            this.unlockedAchievements = new HashSet<>();
        }
        unlockedAchievements.add(achievement);
    }

    public void setAchievements(Set<Achievement> achievement) {
        this.unlockedAchievements = achievement;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
