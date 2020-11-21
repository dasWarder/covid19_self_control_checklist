package ru;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.config.WebConfig;
import ru.model.Statistic;
import ru.service.StatisticService;

import java.time.LocalDateTime;
import java.util.List;

public class Main {




    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(WebConfig.class);

        StatisticService statisticService = applicationContext.getBean("statisticService", StatisticService.class);



        Statistic statistic = statisticService.get(8, 1);

        System.out.println(statistic + ": " + statistic.getSymptoms());
    }
}
