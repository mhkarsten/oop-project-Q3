package server.repository;

import org.springframework.stereotype.Repository;

import java.util.*;
import server.model.User;

@Repository
public class UserDAO {

    private static final Map<Long, User> userMap = new HashMap<Long, User>();
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
