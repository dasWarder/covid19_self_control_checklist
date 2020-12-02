package ru.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ru.config.MainConfig;
import ru.model.Statistic;
import ru.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import static ru.testData.StatisticTestData.*;
import static ru.testData.UserTestData.*;
import static org.junit.Assert.assertThrows;

@RunWith(value = SpringRunner.class)
@Sql(scripts = "classpath:db/populate.sql")
@ContextConfiguration(classes = MainConfig.class)
public class StatisticServiceTest {

    @Autowired
    protected StatisticService statisticService;

    @Test
    public void getAll() {
        List<Statistic> allFromDbUser = statisticService.getAll(USER_ID);
        STATISTIC_MATCHER.assertMatch(allFromDbUser, STATISTIC_1, STATISTIC_2, STATISTIC_3);
    }

    @Test
    public void delete() {
        statisticService.delete(STATISTIC_1.getId(), USER_ID);
        assertThrows(NotFoundException.class, () -> statisticService.get(STATISTIC_1.getId(), USER_ID));
    }

    @Test
    public void deleteWrongUser() {
        assertThrows(NotFoundException.class, () -> statisticService.delete(STATISTIC_2.getId(), ADMIN_ID));
    }

    @Test
    public void deleteWrongId() {
        assertThrows(NotFoundException.class, () -> statisticService.delete(INCORRECT_ID, ADMIN_ID));
    }

    @Test
    public void get() {
        Statistic getAdminDb = statisticService.get(STATISTIC_ADMIN_1.getId(), ADMIN_ID);
        STATISTIC_MATCHER.assertMatch(getAdminDb, STATISTIC_ADMIN_1);
    }

    @Test
    public void getWrongUserId() {
        assertThrows(NotFoundException.class, () -> statisticService.get(STATISTIC_1.getId(), ADMIN_ID));

    }

    @Test
    public void getWrongStatId() {
        assertThrows(NotFoundException.class, () -> statisticService.get(INCORRECT_ID, USER_ID));
    }

    @Test
    public void save() {
        Statistic dbSaveStat = statisticService.save(getNewStatistic(), ADMIN_ID);
        int id = dbSaveStat.getId();

        Statistic newStat = getNewStatistic();
        newStat.setId(id);
        STATISTIC_MATCHER.assertMatch(dbSaveStat, newStat);
        STATISTIC_MATCHER.assertMatch(statisticService.get(id, ADMIN_ID), newStat);
    }

    @Test
    public void update() {
        Statistic updated = getUpdated();
        statisticService.update(updated, ADMIN_ID);
        STATISTIC_MATCHER.assertMatch(statisticService.get(STATISTIC_ADMIN_1.getId(), ADMIN_ID), getUpdated());
    }
}