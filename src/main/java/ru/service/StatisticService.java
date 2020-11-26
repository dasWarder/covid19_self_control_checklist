package ru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.model.Statistic;
import ru.repository.DataJpaStatisticRepository;
import ru.repository.StatisticRepository;

import java.util.*;


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

    public int delete(int id, int userId) {
        return statisticRepository.delete(id, userId);
    }

    public Statistic get(int id, int userId) {
        return statisticRepository.get(id, userId);
    }

    public Statistic save(Statistic statistic, int userId) {
        return statisticRepository.save(statistic, userId);
    }
}
