package com.paymentchain.transaction.service;

import com.paymentchain.transaction.entities.Transaction;
import com.paymentchain.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    public List<Transaction> findAll(){
        return transactionRepository.findAll();
    }

    public ResponseEntity<Transaction> findById(Long id) {
        return transactionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<Transaction>> findAllCustomer(String accountIban) {
        List<Transaction> transactions = transactionRepository.findByIbaAccount(accountIban);
        if (transactions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transactions);
    }

    public ResponseEntity<Transaction> update(Long id, Transaction dto) {
        Optional<Transaction> existingTransaction = transactionRepository.findById(id);

        if (existingTransaction.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Transaction savedProduct = existingTransaction.get();
        savedProduct.setReference(dto.getReference());
        savedProduct.setAccountIban(dto.getAccountIban());
        savedProduct.setDate(dto.getDate());
        savedProduct.setAmount(dto.getAmount());
        savedProduct.setFee(dto.getFee());
        savedProduct.setDescription(dto.getDescription());
        savedProduct.setStatus(dto.getStatus());
        savedProduct.setChannel(dto.getChannel());

        transactionRepository.save(savedProduct);

        return ResponseEntity.ok(savedProduct);
    }

    public ResponseEntity<Transaction> save(Transaction dto) {
        Transaction product = transactionRepository.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    public ResponseEntity<Void> delete(Long id) {
        if (!transactionRepository.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        transactionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
