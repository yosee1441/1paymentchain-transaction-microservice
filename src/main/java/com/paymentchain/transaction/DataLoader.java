package com.paymentchain.transaction;

import com.paymentchain.transaction.service.TransactionService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.paymentchain.transaction.entities.Transaction;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader {
    @Autowired
    TransactionService transactionService;

    @PostConstruct
    public void loadData() {
        Transaction transaction1 = Transaction.builder()
                .reference("REF123")
                .accountIban("ES12345678901234567890")
                .date(LocalDateTime.now())
                .fee(2.5)
                .amount(1500.0)
                .channel("Online")
                .description("Pago de servicios")
                .status("Completed")
                .build();

        Transaction transaction2 = Transaction.builder()
                .reference("REF456")
                .accountIban("ES98765432109876543210")
                .date(LocalDateTime.now().minusDays(1))
                .fee(1.0)
                .amount(300.0)
                .channel("Mobile")
                .description("Transferencia a amigo")
                .status("Pending")
                .build();

        Transaction transaction3 = Transaction.builder()
                .reference("REF789")
                .accountIban("ES11223344556677889900")
                .date(LocalDateTime.now().minusDays(2))
                .fee(3.0)
                .amount(5000.0)
                .channel("Branch")
                .description("Pago de hipoteca")
                .status("Completed")
                .build();

        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        transactions.forEach(transaction -> {
            transactionService.save(transaction);
        });
    }
}
