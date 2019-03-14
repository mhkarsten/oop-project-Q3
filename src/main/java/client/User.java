package client;

<<<<<<< HEAD
=======
import server.model.Achievement;

import java.util.Set;

>>>>>>> achievement
//TODO: Find a way to have client and server use the same User class
public class User extends server.model.User {
    private long id;
    private String name;
    private int points;
<<<<<<< HEAD

    /**User constructor.
     *
     * @param id Unique ID
     * @param name Name of the user
     * @param points Points of the user
     */
=======
    private Set<Achievement> achievement;
>>>>>>> achievement
    public User(long id, String name, int points) {
        this.id = id;
        this.name = name;
        this.points = points;
    }

    public User() {

    }
}
