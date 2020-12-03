package ru.service;

import org.springframework.util.Assert;
import ru.model.User;

import java.util.List;

import static ru.util.ValidUtil.checkNotFound;
import static ru.util.ValidUtil.checkNotFoundWithId;

public interface CrudUserService {
    User save(User user);

    void delete(int id);

    User get(int id);

    User getByEmail(String email);

    List<User> getAll();

    void update(User user);
}
