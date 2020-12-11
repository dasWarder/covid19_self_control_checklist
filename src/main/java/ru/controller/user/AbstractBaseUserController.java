package ru.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import ru.controller.statistic.StatisticController;
import ru.model.User;
import ru.service.CrudUserService;
import ru.util.ValidUtil;

import java.awt.*;
import java.util.List;

import static ru.util.ValidUtil.checkNotFound;
import static ru.util.ValidUtil.checkNotFoundWithId;



abstract public class AbstractBaseUserController {
    protected final Logger logger = LoggerFactory.getLogger(AbstractBaseUserController.class);

    @Autowired
    protected CrudUserService userService;


    public AbstractBaseUserController() {};


    public User save(User user) {
        logger.debug("Save entity with id=" + user.getId());
        return userService.save(user);
    }

    public void delete(int id)  {
        userService.delete(id);
        logger.debug("Delete entity with id=" + id);
    }

    public User get(int id) {
        logger.debug("Get entity with id=" + id);
        return userService.get(id);
    }

    public User getByEmail(String email) {
        logger.debug("Get entity with email=" + email);
        return userService.getByEmail(email);
    }

    public List<User> getAll() {
        logger.debug("Get all");
        return userService.getAll();
    }

    public void update(User user, int id) {
        ValidUtil.checkIdIsConsistent(user, id);
        logger.debug("Update entity with id=" + id);
        userService.update(user);
    }
}
