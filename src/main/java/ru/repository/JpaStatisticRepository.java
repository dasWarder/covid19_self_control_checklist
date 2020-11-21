package ru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.model.Statistic;


import java.util.*;

@Transactional(readOnly = true)
public interface JpaStatisticRepository extends JpaRepository<Statistic, Integer> {

    @Query("SELECT s FROM Statistic s JOIN FETCH s.user WHERE s.user.id =?1")
    List<Statistic> getAll(@Param("user_id") int userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Statistic s WHERE s.id=?1 AND s.user.id=?2")
    int delete(@Param("id") int id, @Param("user_id") int userId);

}
