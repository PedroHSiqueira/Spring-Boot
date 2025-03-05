package io.github.cursospringboot.libraryapi.repository;

import io.github.cursospringboot.libraryapi.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransactionalTest {
    @Autowired
    TransactionService transactionService;

    @Test
    public void transactionalTest(){
        transactionService.executar();
    }
}
