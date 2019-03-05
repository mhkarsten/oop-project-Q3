package client;

//TODO: Find a way to have client and server use the same User class
public class User {
    private String userID;
    private String userName;
    private int userPoints;

    public User() {

    }

    /** Constructor for the User class.
     *
     * @param usrID Unique UserID
     * @param usrName A username
     * @param usrPoints Amount of point associated with the user
     */
    public User(String usrID, String usrName, int usrPoints) {

        this.userID = usrID;
        this.userName = usrName;
        this.userPoints = usrPoints;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserPoints() {
        return userPoints;
    }

    public void setUserPoints(int userPoints) {
        this.userPoints = userPoints;
    }
}
