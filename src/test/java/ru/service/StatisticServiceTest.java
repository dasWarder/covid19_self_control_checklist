package ru.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ru.config.WebConfig;

import static org.junit.Assert.*;

@RunWith(value = SpringRunner.class)
@Sql(scripts = "classpath:db/populate.sql")
@ContextConfiguration(classes = WebConfig.class)
public class StatisticServiceTest {



    @Test
    public void getAll() {

    }

    @Test
    public void delete() {
    }

    @Test
    public void get() {
    }

    @Test
    public void save() {
    }
}