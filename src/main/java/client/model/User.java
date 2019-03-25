package client.model;

import java.util.Objects;
import java.util.Set;

public class User {

    private long id;
    private String name;
    private String password;
    private int points;
    private Set<Achievement> achievement;

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


     /** Constructor for the User class.
        * @param id The numeric id of the user
     * @param name The name of the user
     * @param points The points of the user
     */
    public User(long id, String name, String password, int points) {

        this.id = id;
        this.name = name;
        this.password = password;
        this.points = points;
    }

    /**
     * Constructor for the User class.
     * @param id The numeric id of the user
     * @param name The name of the user
     * @param points The points of the user
     */
    public User(String name, String password, int points) {
        this.password = password;
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

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", points=" + points +
            ", achievement=" + achievement +
            '}';
    }

    public Set<Achievement> getAchievements() {
        return this.achievement;
    }
    public void setAchievements(Set<Achievement> achievement) {
        this.achievement=achievement;
    }
}

