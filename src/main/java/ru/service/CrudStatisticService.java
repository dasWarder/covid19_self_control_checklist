package ru.service;

import org.springframework.util.Assert;
import ru.model.Statistic;

import java.util.List;

import static ru.util.ValidUtil.checkNotFoundWithId;

public interface CrudStatisticService {

    List<Statistic> getAll(int userId);

    void delete(int id, int userId);

    Statistic get(int id, int userId);

    Statistic save(Statistic statistic, int userId);

    void update(Statistic statistic, int userId);
}
