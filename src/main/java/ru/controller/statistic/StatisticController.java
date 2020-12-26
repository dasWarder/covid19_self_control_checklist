package ru.controller.statistic;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.model.Statistic;
import ru.model.User;
import ru.repository.DataJpaUserRepository;
import ru.service.CrudStatisticService;
import ru.service.StatisticService;
import ru.service.UserService;

import java.util.*;

@RestController
@RequestMapping(value = "/statistic", produces = MediaType.APPLICATION_JSON_VALUE)
public class StatisticController {
    private final Logger logger = LoggerFactory.getLogger(StatisticController.class);
    public static Integer TEST_USER_ID = 1;

    private CrudStatisticService statisticService;
    private UserService userService;

    @Autowired
    public StatisticController(UserService userService, CrudStatisticService statisticService) {
        this.userService = userService;
        this.statisticService = statisticService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Statistic> save(@RequestBody Statistic statistic, @RequestParam(defaultValue = "0") int userId) {
        Statistic saveOrUpdate = statistic;
        User user = getLoggedUser();

        if(statistic.getId() == null) {
            saveOrUpdate = statisticService.save(statistic, user.getId());
        } else {
            statisticService.update(statistic, user.getId());
        }
        logger.debug("Save entity with id=" + statistic.getId());

        return new ResponseEntity<>(saveOrUpdate, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Statistic get(@PathVariable int id, @RequestParam(defaultValue = "0") int userId) {
        logger.debug("Get entity with id=" + id);
        User user = getLoggedUser();
        return statisticService.get(id, user.getId());
    }

    @GetMapping
    public List<Statistic> getAll(@RequestParam(defaultValue = "0") int userId) {
        logger.debug("Get all for user " + userId);
        User user = getLoggedUser();
        return statisticService.getAll(user.getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @RequestParam(defaultValue = "0") int userId) {
        logger.debug("Delete entity with id=" + id);
        User user = getLoggedUser();
        statisticService.delete(id, user.getId());
    }

    private User getLoggedUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        User user = userService.getByEmail(name);
        return user;
    }
}
