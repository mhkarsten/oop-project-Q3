package server;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserDAO {

    private static final Map<String, User> userMap = new HashMap<String, User>();

    static {

        intUsers();
    }

    private static void intUsers() {
        User user1 = new User("U01", "Alex", 100);
        User user2 = new User("U02", "Jim", 200);
        User user3 = new User("U03", "Jennifer", 150);

        userMap.put(user1.getUserID(), user1);
        userMap.put(user2.getUserID(), user2);
        userMap.put(user3.getUserID(), user3);
    }

    public static User getUser(String userID) {

        return userMap.get(userID);
    }

    public static User addUser(User usr) {

        userMap.put(usr.getUserID(), usr);
        return usr;
    }

    public static User updateUser(User usr) {

        userMap.put(usr.getUserID(), usr);
        return usr;
    }

    public static void deleteUser(String userID) {

        userMap.remove(userID);
    }

    public static List<User> getAllUsers() {

        Collection<User> c = userMap.values();
        List<User> userList = new ArrayList<User>();
        userList.addAll(c);

        return userList;
    }


}
