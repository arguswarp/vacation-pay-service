package com.argus.vacationpayservice.repository;

import com.argus.vacationpayservice.model.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    Optional<Calendar> findByYear(Integer year);
}
