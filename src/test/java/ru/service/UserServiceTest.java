package ru.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ru.config.WebConfig;
import ru.model.User;
import ru.testData.UserTestData;


import static org.junit.Assert.*;


@RunWith(value = SpringRunner.class)
@Sql(scripts = "classpath:db/populate.sql")
@ContextConfiguration(classes = WebConfig.class)
public class UserServiceTest {

    @Autowired
    protected UserService userService;

    @Test
    public void save() {
        User savedUser = userService.save(UserTestData.getNew());
        Integer id = savedUser.getId();

        User newUser = UserTestData.getNew();
        newUser.setId(id);
    }

    @Test
    public void delete() {
    }

    @Test
    public void get() {
    }

    @Test
    public void getByEmail() {
    }

    @Test
    public void getAll() {
    }
}