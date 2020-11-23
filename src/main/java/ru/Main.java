package ru;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.config.WebConfig;
import ru.model.Role;
import ru.model.Statistic;
import ru.model.User;
import ru.service.StatisticService;
import ru.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

public class Main {




    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(WebConfig.class);

        StatisticService statisticService = applicationContext.getBean("statisticService", StatisticService.class);

        UserService userService = applicationContext.getBean("userService", UserService.class);


        userService.delete(4);
        Statistic statistic = statisticService.get(8, 1);

        System.out.println(statistic + ": " + statistic.getSymptoms());
    }
}
