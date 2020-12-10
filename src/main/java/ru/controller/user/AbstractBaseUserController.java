package ru.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import ru.model.User;
import ru.service.CrudUserService;
import ru.util.ValidUtil;

import java.awt.*;
import java.util.List;

import static ru.util.ValidUtil.checkNotFound;
import static ru.util.ValidUtil.checkNotFoundWithId;



abstract public class AbstractBaseUserController {

    protected CrudUserService userService;

    @Autowired
    public AbstractBaseUserController(CrudUserService userService) {
        this.userService = userService;
    }

    public AbstractBaseUserController() {};


    public User save(User user) {
        return userService.save(user);
    }

    public void delete(int id)  {
        userService.delete(id);
    }

    public User get(int id) {
        return userService.get(id);
    }

    public User getByEmail(String email) {
        return userService.getByEmail(email);
    }

    public List<User> getAll() {
        return userService.getAll();
    }

    public void update(User user, int id) {
        ValidUtil.checkIdIsConsistent(user, id);
        userService.update(user);
    }
}
