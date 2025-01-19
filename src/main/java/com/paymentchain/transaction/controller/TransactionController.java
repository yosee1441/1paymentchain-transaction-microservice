package com.paymentchain.transaction.controller;

import com.paymentchain.transaction.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.paymentchain.transaction.service.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @GetMapping()
    public List<Transaction> findAll(){
        return transactionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findById(@PathVariable("id") Long id) {
        return transactionService.findById(id);
    }

    @GetMapping("/customer/transactions")
    public ResponseEntity<List<Transaction>> findAllCustomer(@RequestParam("ibaAccount") String accountIban) {
        return transactionService.findAllCustomer(accountIban);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> update(@PathVariable("id") Long id, @RequestBody Transaction dto) {
        return transactionService.update(id, dto);
    }

    @PostMapping
    public ResponseEntity<Transaction> save(@RequestBody Transaction dto) {
        return transactionService.save(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        return transactionService.delete(id);
    }
}
