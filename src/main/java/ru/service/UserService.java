package ru.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.model.User;
import ru.repository.UserRepository;
import ru.util.ValidUtil;

import static ru.util.ValidUtil.*;

import java.util.List;

@Service
public class UserService implements CrudUserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return checkNotFoundWithId(userRepository.save(user), user.getId());
    }

    public void delete(int id)  {
        checkNotFoundWithId(userRepository.delete(id), id);
    }

    public User get(int id) {
        return checkNotFoundWithId(userRepository.get(id), id);
    }

    public User getByEmail(String email) {
        return checkNotFound(userRepository.getByEmail(email), "email=" + email);
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public void update(User user) {
        Assert.notNull(user, "must be not null");
        checkNotFoundWithId(userRepository.save(user), user.getId());
    }


}
