package ru.service;

import javassist.NotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ru.config.WebConfig;
import ru.model.Role;
import ru.model.User;

import java.util.List;

import static ru.testData.UserTestData.*;





@RunWith(value = SpringRunner.class)
@Sql(scripts = "classpath:db/populate.sql")
@ContextConfiguration(classes = WebConfig.class)
public class UserServiceTest {

    @Autowired
    protected UserService userService;

    @Test
    public void save() {
        User savedUser = userService.save(getNew());
        Integer id = savedUser.getId();

        User newUser = getNew();
        newUser.setId(id);
        USER_MATCHER.assertMatch(savedUser, newUser);
        USER_MATCHER.assertMatch(userService.get(id), newUser);
    }

    @Test
    public void delete() throws NotFoundException {
        userService.delete(USER_ID);
        USER_MATCHER.assertMatch(userService.get(1),null);
    }

    @Test
    public void deleteNotFound() throws NotFoundException {
        Assert.assertThrows(NotFoundException.class, () -> userService.delete(6));
    }

    @Test
    public void get() {
        User user = userService.get(1);
        USER_MATCHER.assertMatch(user, USER);
    }

    @Test
    public void getNotFound() {
        USER_MATCHER.assertMatch(userService.get(5), null);
    }

    @Test
    public void getByEmail() {
        User admin = userService.getByEmail("j@gmail.com");
        USER_MATCHER.assertMatch(admin, ADMIN);
    }

    @Test
    public void duplicateMail() {
        Assert.assertThrows(DataAccessException.class, () -> userService.save(
                new User(null, "duplicated", "password", "j@gmail.com", Role.ADMIN)
        ));
    }

    @Test
    public void getAll() {
        List<User> users = userService.getAll();
        USER_MATCHER.assertMatch(users, USER,ADMIN);
    }

    @Test
    public void update() {
        User user = userService.get(1);
        user.setName("newUser");
        user.setEmail("new@gmail.com");
        Integer id = user.getId();
        User updated = userService.save(user);

        USER_MATCHER.assertMatch(userService.get(id), updated);
    }
}