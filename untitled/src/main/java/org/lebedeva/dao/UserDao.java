package org.lebedeva.dao;

import org.lebedeva.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    User getUserBuUsername(String name) throws SQLException;

    List<User> findAllUsers();
}
