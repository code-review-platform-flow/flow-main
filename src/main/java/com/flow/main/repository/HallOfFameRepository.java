package com.flow.main.repository;

import com.flow.main.entity.HallOfFameEntity;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface HallOfFameRepository extends JpaRepository<HallOfFameEntity, Long> {

    @Query("SELECT h FROM HallOfFameEntity h WHERE h.dateAwarded = :dateAwarded ORDER BY h.rank ASC")
    Optional<List<HallOfFameEntity>> findAllByDateAwarded(@Param("dateAwarded") LocalDate dateAwarded);

}
