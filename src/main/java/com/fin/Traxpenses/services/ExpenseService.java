package com.fin.Traxpenses.services;

import com.fin.Traxpenses.dto.ExpenseCreateDTO;
import com.fin.Traxpenses.dto.ExpenseResponseDTO;
import com.fin.Traxpenses.dto.ExpenseUpdateDTO;
import com.fin.Traxpenses.exceptions.ExpenseOpsException;
import com.fin.Traxpenses.models.Expense;
import com.fin.Traxpenses.models.User;
import com.fin.Traxpenses.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private UserService userService;

    /**
     *
     * @return
     */
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    /**
     * Create a new expense
     * @param dto
     */
    public ExpenseResponseDTO createNewExpense(ExpenseCreateDTO dto, String number) {
        User user = userService.getUserByNumber(number);
        Expense expense = new Expense();
        expense.setUserId(user.getId());
        expense.setAmount(dto.getAmount());
        expense.setDescription(dto.getDescription());
        expense.setCategory(dto.getCategory());
        expense.setDate(dto.getDate() != null ? dto.getDate() : LocalDateTime.now());
        expenseRepository.save(expense);

        return new ExpenseResponseDTO(expense.getId(), expense.getDescription(), expense.getAmount(), expense.getCategory(), expense.getDate().toLocalDate());
    }

    /**
     * Get monthly expenses
     * @param userId
     * @param month
     * @param year
     * @return
     */
    public List<Expense> getExpensesByMonth(UUID userId, int month, int year) {
        LocalDateTime start = LocalDate.of(year, month, 1).atStartOfDay();
        LocalDateTime end = LocalDate.of(year, month, 1)
                .withDayOfMonth(YearMonth.of(year, month).lengthOfMonth())
                .atTime(23, 59, 59);
        return expenseRepository.findByUserIdAndDeletedFalseAndDateBetween(userId, start, end);
    }

    /**
     * Get expense via id
     * @param id
     * @return
     */
    public Expense getExpenseById(UUID id) {
        return expenseRepository.findById(id).orElseThrow(() -> new ExpenseOpsException("Expense not found", HttpStatus.NOT_FOUND));
    }

    /**
     * Update expense
     * @param dto
     */
    public ExpenseResponseDTO updateExpense(ExpenseUpdateDTO dto, UUID expenseId, String number) {
        Expense expenseInDB = getExpenseById(expenseId);

        if(expenseInDB.isDeleted()) { throw new ExpenseOpsException("Expense not found", HttpStatus.NOT_FOUND); }

        User user = userService.getUserByNumber(number);
        if(!expenseInDB.getUserId().equals(user.getId())) { throw new AccessDeniedException("User not authorized"); }

        if(dto.getAmount() != null) expenseInDB.setAmount(dto.getAmount());
        if(dto.getDate() != null) expenseInDB.setDate(dto.getDate());
        if(dto.getDescription() != null) expenseInDB.setDescription(dto.getDescription());
        if(dto.getCategory() != null) expenseInDB.setCategory(dto.getCategory());
        expenseRepository.save(expenseInDB);
        return new ExpenseResponseDTO(expenseInDB.getId(), expenseInDB.getDescription(), expenseInDB.getAmount(), expenseInDB.getCategory(), expenseInDB.getDate().toLocalDate());
    }

    /**
     * Delete expense
     * @param id
     */
    public void deleteExpense(UUID id) {
        String number = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByNumber(number);
        Expense expense = getExpenseById(id);
        if(expense.isDeleted()) {throw new ExpenseOpsException("Expense does not exist", HttpStatus.NOT_FOUND); }
        if(!user.getId().equals(expense.getUserId())) { throw new ExpenseOpsException("User not found", HttpStatus.NOT_FOUND); }
        expense.setDeleted(true);
        expenseRepository.save(expense);
    }

    /**
     * Delete all expense of a particular user
     * @param userId
     */
    public void deleteAllExpensesOfUser(UUID userId) {
        List<Expense> expenseList =  expenseRepository.findByUserIdAndDeletedFalse(userId);
        for(Expense expense: expenseList) {
            expense.setDeleted(true);
            expenseRepository.save(expense);
        }
    }
}
