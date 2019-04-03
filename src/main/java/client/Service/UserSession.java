package client.Service;

import client.model.User;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
     * @return
     */
    public static UserSession getInstace() {

        if(instance == null) {

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
    }

    @Override
    public String toString() {
        return "UserSession{"
            + "userName='" + userName + '\''
            + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserSession session = (UserSession) o;
        return Objects.equals(userName, session.userName)
            && Objects.equals(password, session.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}