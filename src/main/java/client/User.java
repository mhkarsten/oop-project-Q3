package client;

import java.util.Objects;

//TODO: Find a way to have client and server use the same User class
public class User {
    private long id;
    private String name;
    private int points;

    public User() {

    }

    /** Constructor for the User class.
     *
     * @param usrID Unique UserID
     * @param usrName A username
     * @param usrPoints Amount of point associated with the user
     */
    public User(long usrID, String usrName, int usrPoints) {

        this.id = usrID;
        this.name = usrName;
        this.points = usrPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                points == user.points &&
                Objects.equals(name, user.name);
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

    public void setUserName(String userName) {
        this.name = userName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int userPoints) {
        this.points = userPoints;
    }
}
