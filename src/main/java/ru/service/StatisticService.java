package ru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.model.Statistic;
import ru.repository.DataJpaStatisticRepository;
import ru.repository.StatisticRepository;

import java.util.*;
import static ru.util.ValidUtil.*;


@Service
public class StatisticService {

    private StatisticRepository statisticRepository;

    @Autowired
    public StatisticService(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    public List<Statistic> getAll(int userId)  {
        return statisticRepository.getAll(userId);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(statisticRepository.delete(id, userId), id);
    }

    public Statistic get(int id, int userId) {
        return checkNotFoundWithId(statisticRepository.get(id, userId), id);
    }

    public Statistic save(Statistic statistic, int userId) {
        Assert.notNull(statistic, "statistic must be not null");
        return statisticRepository.save(statistic, userId);
    }

    public void update(Statistic statistic, int userId) {
        Assert.notNull(statistic, "statistic must be not null");
        checkNotFoundWithId(statisticRepository.save(statistic, userId), statistic.id());
    }
}
