package com.fin.Traxpenses.controllers;

import com.fin.Traxpenses.dto.ExpenseCreateDTO;
import com.fin.Traxpenses.dto.ExpenseResponseDTO;
import com.fin.Traxpenses.dto.ExpenseUpdateDTO;
import com.fin.Traxpenses.models.Expense;
import com.fin.Traxpenses.models.User;
import com.fin.Traxpenses.services.ExpenseService;
import com.fin.Traxpenses.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> expensesByMonth(@RequestParam int month, @RequestParam int year) {
        String number = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByNumber(number);
        List<Expense> expenses = expenseService.getExpensesByMonth(user.getId(), month, year);
        return ResponseEntity.status(HttpStatus.OK).body(expenses);
    }

    @PostMapping("/new-expense")
    public ResponseEntity<?> addExpense(@RequestBody ExpenseCreateDTO dto) {
        String number = SecurityContextHolder.getContext().getAuthentication().getName();
        ExpenseResponseDTO res =  expenseService.createNewExpense(dto, number);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteExpense(@RequestParam UUID id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateExpense(@RequestBody ExpenseUpdateDTO dto, @PathVariable UUID id) {
        String number = SecurityContextHolder.getContext().getAuthentication().getName();
        ExpenseResponseDTO res = expenseService.updateExpense(dto, id, number);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
