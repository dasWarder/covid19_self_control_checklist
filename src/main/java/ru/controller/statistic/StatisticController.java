package ru.controller.statistic;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.model.Statistic;
import ru.service.CrudStatisticService;
import ru.service.StatisticService;
import java.util.*;

@Controller
public class StatisticController {

    @Autowired
    private CrudStatisticService statisticService;

    public StatisticController(CrudStatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("/statistic")
    public ResponseEntity<Statistic> save(Statistic statistic, int userId) {
        Statistic saveOrUpdate = statistic;
        if(statistic.getId() == null) {
            saveOrUpdate = statisticService.save(statistic, userId);
        } else {
            statisticService.update(statistic, userId);
        }

        return new ResponseEntity<>(saveOrUpdate, HttpStatus.CREATED);
    }

    @GetMapping("/statistic/{id}")
    public ResponseEntity<Statistic> get(@PathVariable("id") int id, int userId) {
        Statistic statistic = statisticService.get(id, userId);
        return new ResponseEntity<>(statistic, HttpStatus.ACCEPTED);
    }

    @GetMapping("/statistic")
    public ResponseEntity<List<Statistic>> getAll(int userId) {
        List<Statistic> all = statisticService.getAll(userId);
        return new ResponseEntity<>(all, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/statistic/{id}")
    public ResponseEntity<Statistic> delete(@PathVariable("id") int id, int userId) {
        statisticService.delete(id, userId);

        return new ResponseEntity<>(statisticService.get(id, userId), HttpStatus.BAD_REQUEST);
    }
}