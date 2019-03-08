package client;

import java.util.Objects;

//TODO: Find a way to have client and server use the same User class
public class User extends server.model.User {
    private long id;
    private String name;
    private int points;

    public User(long id, String name, int points) {
        this.id = id;
        this.name = name;
        this.points = points;
    }

    public User() {

    }
}
