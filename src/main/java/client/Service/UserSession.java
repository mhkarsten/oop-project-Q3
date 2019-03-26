package client.Service;

import org.springframework.stereotype.Service;

@Service
public final class UserSession {

    private static UserSession instance;

    private String userName;
    private String password;

    private UserSession() {

    }

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

    public void cleanUserSession() {
        userName = null;// or null
    }

    @Override
    public String toString() {
        return "UserSession{" +
            "userName='" + userName + '\'' +
            '}';
    }
}