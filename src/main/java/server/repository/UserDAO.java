package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import server.model.User;
import server.repository.UserRepository;

@Repository
public class UserDAO {

    private static final Map<Long, User> userMap = new HashMap<Long, User>();

    static {

        intUsers();
    }

    private static void intUsers() {
        User user1 = new User(1, "Alex", 100);
        User user2 = new User(2, "Jim", 200);
        User user3 = new User(3, "Jennifer", 150);

        userMap.put(user1.getUserID(), user1);
        userMap.put(user2.getUserID(), user2);
        userMap.put(user3.getUserID(), user3);
    }

    public static User getUser(String userID) {

        return userMap.get(userID);
    }

    /**Adds a user to the Map.
     *
     * @param usr User to be added
     * @return returns the user that has been added
     */
    public static User addUser(User usr) {

        userMap.put(usr.getUserID(), usr);
        return usr;
    }

    /**Updates a user inside the map.
     *
     * @param usr User to be updated
     * @return Returns the updated user
     */
    public static User updateUser(User usr) {

        userMap.put(usr.getUserID(), usr);
        return usr;
    }

    public static void deleteUser(String userID) {

        userMap.remove(userID);
    }

    /**Get all users.
     *
     * @return Returns a list of all users
     */
    public static List<User> getAllUsers() {

        Collection<User> collect = userMap.values();
        List<User> userList = new ArrayList<User>();
        userList.addAll(collect);

        return userList;
    }


}
