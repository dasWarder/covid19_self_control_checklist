package ru.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.model.User;
import ru.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public boolean delete(int id) throws NotFoundException {
        if(userRepository.get(id) != null) {
            return userRepository.delete(id);
        } else {
            throw new NotFoundException("User not found");
        }
    }

    public User get(int id) {
        return userRepository.get(id);
    }

    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public void update(User user) {
        userRepository.save(user);
    }


}
