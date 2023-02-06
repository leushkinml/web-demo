package com.skypro.webdemo.repositories;

import com.skypro.webdemo.model.Expense;
import com.skypro.webdemo.services.ExpenseByCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpenseRepository  extends JpaRepository<Expense, Long> {
    void deleteById(Long id);

    @Query(value = "SELECT category, SUM(amount) as amount FROM expenses GROUP BY category", nativeQuery = true)
    List<ExpenseByCategory> getExpensesByCategory();
}
