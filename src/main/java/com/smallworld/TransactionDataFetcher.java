package com.smallworld;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class TransactionDataFetcher {
    private List<TransactionRecord> transactions;

    // Constructor
    public TransactionDataFetcher(List<TransactionRecord> transactions) {
        this.transactions = transactions;
    }

    /**
     * Returns the sum of the amounts of all transactions
     */
    public double getTotalTransactionAmount() {
        double totalAmount = 0.0;
        for (TransactionRecord transaction : transactions) {
            totalAmount += transaction.getAmount();
        }
        return totalAmount;

    }

    /**
     * Returns the sum of the amounts of all transactions sent by the specified client
     */
    public double getTotalTransactionAmountSentBy(String senderFullName) {
        double totalAmountBySender = 0.0;
        for (TransactionRecord transaction : transactions) {
            if (transaction.getSenderFullName().equals(senderFullName)) {
                totalAmountBySender += transaction.getAmount();
            }
        }
        return totalAmountBySender;
    }

    /**
     * Returns the highest transaction amount
     */
    public double getMaxTransactionAmount() {
        double maxAmount = Double.MIN_VALUE;
        for (TransactionRecord transaction : transactions) {
            if (transaction.getAmount() > maxAmount) {
                maxAmount = transaction.getAmount();
            }
        }
        return maxAmount;

    }

    /**
     * Counts the number of unique clients that sent or received a transaction
     */
    public long countUniqueClients() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     */
    public boolean hasOpenComplianceIssues(String clientFullName) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns all transactions indexed by beneficiary name
     */
    public Map<String, Object> getTransactionsByBeneficiaryName() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the identifiers of all open compliance issues
     */
    public Set<Integer> getUnsolvedIssueIds() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns a list of all solved issue messages
     */
    public List<String> getAllSolvedIssueMessages() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the 3 transactions with highest amount sorted by amount descending
     */
    public List<Object> getTop3TransactionsByAmount() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the sender with the most total sent amount
     */
    public Optional<Object> getTopSender() {
        throw new UnsupportedOperationException();
    }

}
