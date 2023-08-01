package com.smallworld;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

//creating test class for unit testing
class TransactionDataFetcherTest {
    private List<TransactionRecord> transactions;
    private TransactionDataFetcher dataFetcher;

    @BeforeEach
    void setUp() {
        //  test data
        transactions = new ArrayList<>();
        transactions.add(new TransactionRecord(663458, 430.2, "Tom Shelby", 22, "Alfie Solomons", 33, 1, false, "Looks like money laundering"));
        transactions.add(new TransactionRecord(1284564, 150.2, "Tom Shelby", 22, "Arthur Shelby", 60, 2, true, "Never gonna give you up"));
        dataFetcher = new TransactionDataFetcher(transactions);
    }
    //writing some unit tests
    @Test
    void testGetTotalTransactionAmount() {
        double expectedTotalAmount = 430.2 + 150.2;
        Assertions.assertEquals(expectedTotalAmount, dataFetcher.getTotalTransactionAmount());
    }

    @Test
    void testGetTotalTransactionAmountSentBy() {
        double expectedTotalAmountSentByTomShelby = 430.2 + 150.2;
        Assertions.assertEquals(expectedTotalAmountSentByTomShelby, dataFetcher.getTotalTransactionAmountSentBy("Tom Shelby"));
    }

    @Test
    void testGetMaxTransactionAmount() {
        double expectedMaxAmount = 430.2;
        Assertions.assertEquals(expectedMaxAmount, dataFetcher.getMaxTransactionAmount());
    }

    @Test
    void testCountUniqueClients() {
        int expectedUniqueClientsCount = 3;
        Assertions.assertEquals(expectedUniqueClientsCount, dataFetcher.countUniqueClients());
    }

}