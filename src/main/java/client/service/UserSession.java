package client.service;

import client.model.User;
import org.springframework.stereotype.Service;

@Service
public final class UserSession {

    private static UserSession instance;

    private String userName;
    private String password;
    private User currentUser;

    private UserSession() {
    }

    /**
     * Checks if the instance is already instantiated, and if so return the single instance.
     * If not creates the single instance and returns it.
     * @return returns the instance of usersession
     */
    public static UserSession getInstance() {
        if (instance == null) {

            instance = new UserSession();
        }

        return instance;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Clears out the session by setting all properties to null again.
     */
    public void cleanUserSession() {
        userName = null;// or null
        password = null;// or null
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}