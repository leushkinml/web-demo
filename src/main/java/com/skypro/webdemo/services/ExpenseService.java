package com.skypro.webdemo.services;

import com.skypro.webdemo.model.Expense;
import com.skypro.webdemo.repositories.ExpenseRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    private ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getAllExpenses(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return expenseRepository.findAll(pageRequest).getContent();
    }

//    public List<Expense> getAllExpensesSimple() {
//        return expenseRepository.findAll();
//    }
    public void createExpense(Expense expense) {
        expenseRepository.save(expense);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    public List<ExpenseByCategory> getExpensesByCategory() {
        return expenseRepository.getExpensesByCategory();
    }
}
