package ru.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.model.Statistic;

import java.util.*;

@Repository
public class DataJpaStatisticRepository implements StatisticRepository {

    private final JpaStatisticRepository statisticRepository;
    private final JpaUserRepository userRepository;

    @Autowired
    public DataJpaStatisticRepository(JpaStatisticRepository statisticRepository, JpaUserRepository userRepository) {
        this.statisticRepository = statisticRepository;
        this.userRepository = userRepository;
    }

    public Statistic save(Statistic statistic, int userId) {
            if(!statistic.isNew() && get(statistic.getId(), userId) == null) {
                return null;
            }

        statistic.setUser(userRepository.getOne(userId));
        return statisticRepository.save(statistic);
    }

    public Statistic get(int id, int userId) {
        return statisticRepository.findById(id)
                .filter(s -> s.getUser().getId() == userId)
                .orElse(null);
    }

    public List<Statistic> getAll(int userId) {
        return statisticRepository.getAll(userId);
    }

    public int delete(int id, int userId) {
        return statisticRepository.delete(id, userId);
    }

    
}
