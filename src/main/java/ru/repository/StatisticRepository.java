package ru.repository;

import ru.model.Statistic;
import java.util.*;

public interface StatisticRepository {
    Statistic save(Statistic statistic, int userId);

    Statistic get(int id, int userId);

    List<Statistic> getAll(int userId);

    boolean delete(int id, int userId);
}
