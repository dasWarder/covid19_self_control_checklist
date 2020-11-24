package ru.testData;

import ru.model.Statistic;
import ru.model.Symptom;
import ru.model.User;
import util.TestMatcher;

import java.time.LocalDateTime;
import java.util.*;

public class StatisticTestData {
    public static TestMatcher<Statistic> STATISTIC_MATCHER = TestMatcher.getTestMatcher("date", "user", "symptoms");

    public static final Statistic STATISTIC_1 = new Statistic(8, 37.2,
            LocalDateTime.of(2020,
            10,
            30,
            10,
            00));
    public static final Statistic STATISTIC_2 = new Statistic(9, 37.4,
            LocalDateTime.of(2020,
                    10,
                    30,
                    15,
                    00));
    public static final Statistic STATISTIC_3 = new Statistic(10, 36.8,
            LocalDateTime.of(2020,
                    10,
                    31,
                    12,
                    00));
    public static final Statistic STATISTIC_ADMIN_1 = new Statistic(11, 36.4,
            LocalDateTime.of(2020,
                    11,
                    1,
                    10,
                    20));

    public static final Symptom SYMPTOM_1 = new Symptom(3, "snuffle");
    public static final Symptom SYMPTOM_2 = new Symptom(4, "cough");
    public static final Symptom SYMPTOM_3 = new Symptom(5, "headache");
    public static final Symptom SYMPTOM_4 = new Symptom(6, "diarrhea");
    public static final Symptom SYMPTOM_5 = new Symptom(7, "chills");





}
