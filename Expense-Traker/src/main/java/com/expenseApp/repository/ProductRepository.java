package com.expenseApp.repository;

import com.expenseApp.entities.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {



    // Get all expenses for a budget
    List<Product> findByBudgetId(int budgetId);

    // Optional: find by user too
    List<Product> findByUserIdOrderByDateDesc(int userId);


    //Returns the lastets product purchase and total amount spent on it
    @Query(value = """
    SELECT 
        (SELECT name FROM product 
         WHERE type = :type AND budget_id = :budgetId 
         ORDER BY date DESC, time DESC LIMIT 1) AS latestProductName,
        (SELECT SUM(price) FROM product 
         WHERE type = :type AND budget_id = :budgetId) AS totalSpent
    """, nativeQuery = true)
    Object getProductSummary(@Param("type") String type, @Param("budgetId") int budgetId);


    //Returns the top 3 purched product
    @Query("SELECT FUNCTION('DATE_FORMAT', p.date, '%d %M %Y'), p.type, p.name, p.price " +
            "FROM Product p " +
            "WHERE p.budget.id = :budgetId " +
            "ORDER BY p.date DESC, p.time DESC")
    List<Object[]> getTop3FormattedProductsByBudgetId(@Param("budgetId") int budgetId, Pageable pageable);


    List<Product> findByUserId(int userId);
}
