package com.fin.Traxpenses.services;

import com.fin.Traxpenses.models.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrchestrationService {
    @Autowired
    private UserService userService;
    @Autowired
    private ExpenseService expenseService;

    /**
     * Delete expenses of a particular user
     * @param userId
     */
    public void deleteUserAndExpenses(UUID userId) {
        userService.deleteUser(userId);
        expenseService.deleteAllExpensesOfUser(userId);
    }
}
