package org.lebedeva.service;

import org.lebedeva.model.User;
import org.lebedeva.dao.UserDao;

import java.sql.SQLException;

public class UserService {
    private UserDao dao;

    public UserService(UserDao dao) {
        this.dao = dao;
    }

    public boolean checkUserPresent(User user) throws SQLException {
        User user1 = dao.getUserBuUsername(user.getUsername());
        return user1 != null;
    }
}
