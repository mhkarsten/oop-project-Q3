package server;

public class User {
    public String userID;
    public String userName;
    public int userPoints;

    public User() {

    }

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
