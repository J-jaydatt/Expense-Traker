package com.expenseApp.repository;

import com.expenseApp.entities.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget,Integer> {

    //Derived Methods (Custum methods)
    Optional<Budget> findByUserIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            int userId, Date today1, Date today2
    );

    // Get last created budget (to update its end date)
    Optional<Budget> findTopByUserIdOrderByStartDateDesc(int userId);


    @Query("SELECT b FROM Budget b WHERE b.userId = :userId AND :today BETWEEN b.startDate AND b.endDate AND b.status = 'ACTIVE' ORDER BY b.id DESC")
    Budget findLatestActiveBudget(@Param("userId") int userId, @Param("today") LocalDate today);


}
