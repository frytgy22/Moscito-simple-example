package org.lebedeva.dao;

import org.lebedeva.model.User;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private List<User> users;

    public UserDaoImpl() {
        this.users = Arrays.asList(
                new User("user1", "GUEST"),
                new User("user2", "USER"),
                new User("user3", "ADMIN")
        );
    }

    @Override
    public User getUserBuUsername(String name) throws SQLException {
        return users.stream()
                .filter(user -> name.equals(user.getUsername()))
                .findAny().orElse(null);
    }

    @Override
    public List<User> findAllUsers() {
        return users;
    }
}
