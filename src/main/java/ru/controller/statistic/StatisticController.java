package ru.controller.statistic;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.model.Statistic;
import ru.service.CrudStatisticService;
import ru.service.StatisticService;
import java.util.*;

@RestController
@RequestMapping(value = "/statistic", produces = MediaType.APPLICATION_JSON_VALUE)
public class StatisticController {


    private CrudStatisticService statisticService;

    public StatisticController(CrudStatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Statistic> save(@RequestBody Statistic statistic, @RequestParam(defaultValue = "0") int userId) {
        Statistic saveOrUpdate = statistic;
        if(statistic.getId() == null) {
            saveOrUpdate = statisticService.save(statistic, userId);
        } else {
            statisticService.update(statistic, userId);
        }

        return new ResponseEntity<>(saveOrUpdate, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Statistic get(@PathVariable int id, @RequestParam(defaultValue = "0") int userId) {

        return statisticService.get(id, 1);
    }

    @GetMapping
    public List<Statistic> getAll(@RequestParam(defaultValue = "0") int userId) {
        return statisticService.getAll(1);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @RequestParam(defaultValue = "0") int userId) {
        statisticService.delete(id, userId);
    }
}
