package com.fin.Traxpenses.repository;

import com.fin.Traxpenses.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, UUID> {
    @Query("""
    SELECT e FROM Expense e
    WHERE e.userId = :userId
        and e.deleted = false
      AND e.date BETWEEN :start AND :end
""")
    List<Expense> findByUserIdAndDeletedFalseAndDateBetween(
            @Param("userId") UUID userId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);
}
