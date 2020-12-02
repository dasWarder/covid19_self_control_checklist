package ru;

import javassist.NotFoundException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.config.MainConfig;
import ru.model.Statistic;
import ru.service.StatisticService;
import ru.service.UserService;

public class Main {


    public static void main(String[] args) throws NotFoundException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);

        StatisticService statisticService = applicationContext.getBean("statisticService", StatisticService.class);

        UserService userService = applicationContext.getBean("userService", UserService.class);


    }
}
