package net.argania.core.managers;

import net.argania.core.Objects.User;

import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private static List<User> users;

    static {
        UserManager.users = new ArrayList<User>();
    }

    public static List<User> getUsers() {
        return new ArrayList<User>(UserManager.users);
    }

    public static void addUser(final User u) {
        if (!UserManager.users.contains(u)) {
            UserManager.users.add(u);
        }
    }

    public static void removeUser(final User u) {
        if (UserManager.users.contains(u)) {
            UserManager.users.remove(u);
        }
    }
}
