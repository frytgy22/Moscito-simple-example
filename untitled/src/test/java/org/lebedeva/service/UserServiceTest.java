package org.lebedeva.service;

import org.junit.Test;
import org.lebedeva.dao.UserDao;
import org.lebedeva.model.User;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

    @Mock
    private UserDao userdao;
    private UserService userService;

    public UserServiceTest() {

        MockitoAnnotations.initMocks(this);
        this.userService = new UserService(userdao);
    }

    @Test
    public void checkUserPresent_1() throws SQLException {

        //if invoke
        given(userdao.getUserBuUsername("user1")).willReturn(
                new User("user1", "GUEST")
        );

        boolean present = userService.checkUserPresent(new User("user1", "GUEST"));
        assertThat(present).isTrue();

        //after execute check parameter
        verify(userdao).getUserBuUsername("user1");
    }

    @Test
    public void checkUserPresent_2() throws SQLException {
        given(userdao.getUserBuUsername("hello")).willReturn(null);

        boolean present = userService.checkUserPresent(new User("user1", "GUEST"));
        assertThat(present).isFalse();
    }

    @Test(expected = Exception.class)
    public void checkUserPresent_3() throws SQLException {
        given(userdao.getUserBuUsername(anyString())).willThrow(Exception.class);//or any(User.class)

        userService.checkUserPresent(new User("user1", "GUEST"));
    }

    @Test
    public void testCaptor() throws SQLException {
        given(userdao.getUserBuUsername(anyString())).willReturn(new User("user1"));

        boolean present = userService.checkUserPresent(new User("user1"));

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(userdao).getUserBuUsername(captor.capture());
        String values = captor.getValue();
//check parameter dao after execute
        assertThat(values).isEqualTo("user1");
    }
};
