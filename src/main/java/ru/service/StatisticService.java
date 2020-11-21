package ru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.model.Statistic;
import ru.repository.DataJpaStatisticRepository;

import java.util.*;


@Service
public class StatisticService {

    private DataJpaStatisticRepository dataJpaStatisticRepository;

    @Autowired
    public StatisticService(DataJpaStatisticRepository dataJpaStatisticRepository) {
        this.dataJpaStatisticRepository = dataJpaStatisticRepository;
    }

    public List<Statistic> getAll(int userId)  {
        return dataJpaStatisticRepository.getAll(userId);
    }

    public int delete(int id, int userId) {
        return dataJpaStatisticRepository.delete(id, userId);
    }

    public Statistic get(int id, int userId) {
        return dataJpaStatisticRepository.get(id, userId);
    }

    public Statistic save(Statistic statistic, int userId) {
        return dataJpaStatisticRepository.save(statistic, userId);
    }
}
