package org.lebedeva.dao;

import org.junit.Before;
import org.junit.Test;
import org.lebedeva.model.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class UserDaoTest {

    private UserDao dao;

    @Before
    public void setUp() throws Exception {
        this.dao = new UserDaoImpl();
    }

    @Test
    public void getUserByUsername_1() throws Exception {
        User user = dao.getUserBuUsername("user1");
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo("user1");
    }

    @Test
    public  void getUserByUsername_2() throws Exception{
        User hello = dao.getUserBuUsername("hello");
        assertThat(hello).isNull();
    }

    @Test
    public void findAll(){
        List<User> users=dao.findAllUsers();

        assertThat(users.get(0)).isEqualToComparingFieldByField(new User("user1", "GUEST"));
        assertThat(users).contains( new User("user1", "GUEST"));
    }
}
