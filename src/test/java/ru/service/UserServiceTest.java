package ru.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.config.MainConfig;
import ru.model.Role;
import ru.model.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.testData.UserTestData.*;




@ExtendWith(SpringExtension.class)
@Sql(scripts = "classpath:db/populate.sql")
@ContextConfiguration(classes = MainConfig.class)
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
    public void delete() {
        userService.delete(USER_ID);
        assertThrows(ru.util.exception.NotFoundException.class, () -> userService.get(USER_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(ru.util.exception.NotFoundException.class, () -> userService.delete(INCORRECT_ID));
    }

    @Test
    public void get() {
        User user = userService.get(USER_ID);
        USER_MATCHER.assertMatch(user, USER);
    }

    @Test
    public void getNotFound() {
        assertThrows(ru.util.exception.NotFoundException.class, () -> userService.get(INCORRECT_ID));
    }

    @Test
    public void getByEmail() {
        User admin = userService.getByEmail("j@gmail.com");
        USER_MATCHER.assertMatch(admin, ADMIN);
    }

    @Test
    public void duplicateMail() {
        assertThrows(DataAccessException.class, () -> userService.save(
                new User(null, "duplicated", "password", "j@gmail.com", Role.ROLE_ADMIN)
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