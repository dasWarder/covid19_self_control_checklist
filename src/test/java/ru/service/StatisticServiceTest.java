package ru.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ru.config.WebConfig;
import ru.model.Statistic;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;
import static ru.testData.StatisticTestData.*;
import static ru.testData.UserTestData.*;

@RunWith(value = SpringRunner.class)
@Sql(scripts = "classpath:db/populate.sql")
@ContextConfiguration(classes = WebConfig.class)
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
        STATISTIC_MATCHER.assertMatch(statisticService.get(STATISTIC_1.getId(), USER_ID), null);
    }

    @Test
    public void deleteWrongUser() {
        statisticService.delete(STATISTIC_2.getId(), ADMIN_ID);
        STATISTIC_MATCHER.assertMatch(statisticService.get(STATISTIC_2.getId(), USER_ID), STATISTIC_2);
    }

    @Test
    public void deleteWrongId() {
        statisticService.delete(4, ADMIN_ID);
        STATISTIC_MATCHER.assertMatch(statisticService.get(4, ADMIN_ID), null);
    }

    @Test
    public void get() {
        Statistic getAdminDb = statisticService.get(STATISTIC_ADMIN_1.getId(), ADMIN_ID);
        STATISTIC_MATCHER.assertMatch(getAdminDb, STATISTIC_ADMIN_1);
    }

    @Test
    public void getWrongUserId() {
        Statistic incorrectStat = statisticService.get(STATISTIC_1.getId(), ADMIN_ID);
        STATISTIC_MATCHER.assertMatch(incorrectStat, null);
    }

    @Test
    public void getWrongStatId() {
        Statistic incorrectStat = statisticService.get(20, USER_ID);
        STATISTIC_MATCHER.assertMatch(incorrectStat, null);
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
        Statistic originalStat = statisticService.get(STATISTIC_ADMIN_1.getId(), ADMIN_ID);
        originalStat.setTemperature(38.1);
        originalStat.setDate(LocalDateTime.now());
        Integer id = originalStat.getId();
        Statistic save = statisticService.save(originalStat, ADMIN_ID);

        STATISTIC_MATCHER.assertMatch(statisticService.get(id, ADMIN_ID), save);
    }
}