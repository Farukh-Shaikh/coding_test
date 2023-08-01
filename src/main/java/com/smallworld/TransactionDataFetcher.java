package com.smallworld;

import java.util.*;
import java.util.stream.Collectors;

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
        return transactions.stream().mapToDouble(TransactionRecord::getAmount).sum();

    }

    /**
     * Returns the sum of the amounts of all transactions sent by the specified client
     */
    public double getTotalTransactionAmountSentBy(String senderFullName) {
        return transactions.stream()
                .filter(transaction -> transaction.getSenderFullName().equals(senderFullName))
                .mapToDouble(TransactionRecord::getAmount)
                .sum();
    }

    /**
     * Returns the highest transaction amount
     */
    public double getMaxTransactionAmount() {
        return transactions.stream()
                .mapToDouble(TransactionRecord::getAmount)
                .max()
                .orElse(0.0);

    }

    /**
     * Counts the number of unique clients that sent or received a transaction
     */
    public int countUniqueClients() {
        //using set to get unique records
        Set<String> uniqueClients = new HashSet<>();
        for (TransactionRecord transaction : transactions) {
            uniqueClients.add(transaction.getSenderFullName());
            uniqueClients.add(transaction.getBeneficiaryFullName());
        }
        return uniqueClients.size();

    }

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     */
    public boolean hasOpenComplianceIssues(String clientFullName) {
        return transactions.stream()
                .filter(transaction -> clientFullName.equals(transaction.getSenderFullName())
                        || clientFullName.equals(transaction.getBeneficiaryFullName()))
                .anyMatch(transaction -> transaction.getIssueId() != null && !transaction.isIssueSolved());
    }

    /**
     * Returns all transactions indexed by beneficiary name
     */
    public Map<String, List<TransactionRecord>> getTransactionsByBeneficiaryName() {
        //using hashmap to store transactions
        Map<String, List<TransactionRecord>> transactionsByBeneficiaryName = new HashMap<>();
        for (TransactionRecord transaction : transactions) {
            //add all transactions with beneficiary Name
            String beneficiaryName = transaction.getBeneficiaryFullName();
            if (!transactionsByBeneficiaryName.containsKey(beneficiaryName)) {
                transactionsByBeneficiaryName.put(beneficiaryName, new ArrayList<>());
            }
            transactionsByBeneficiaryName.get(beneficiaryName).add(transaction);
        }
        return transactionsByBeneficiaryName;
    }

    /**
     * Returns the identifiers of all open compliance issues
     */
    public Set<Integer> getUnsolvedIssueIds() {
        //getting ids of all issues
        return transactions.stream()
                .filter(transaction -> transaction.getIssueId() != null && !transaction.isIssueSolved())
                .map(TransactionRecord::getIssueId)
                .collect(Collectors.toSet());

    }

    /**
     * Returns a list of all solved issue messages
     */
    public List<String> getAllSolvedIssueMessages() {
        return transactions.stream()
                .filter(transaction -> transaction.getIssueId() != null && transaction.isIssueSolved())
                .map(TransactionRecord::getIssueMessage)
                .collect(Collectors.toList());
    }

    /**
     * Returns the 3 transactions with highest amount sorted by amount descending
     */
    public List<TransactionRecord> getTop3TransactionsByAmount() {
        // Sort in desc by amount
        return transactions.stream()
                .sorted(Comparator.comparingDouble(TransactionRecord::getAmount).reversed())
                .limit(3)
                .collect(Collectors.toList());

    }

    /**
     * Returns the sender with the most total sent amount
     */
    public Optional<Map.Entry<String, Double>> getTopSender() {
        // Create a map to store the total sent amount for each sender
        Map<String, Double> totalSentAmountBySender = transactions.stream()
                .collect(Collectors.groupingBy(
                        TransactionRecord::getSenderFullName,
                        Collectors.summingDouble(TransactionRecord::getAmount)
                ));

        return totalSentAmountBySender.entrySet().stream()
                .max(Map.Entry.comparingByValue());
    }

}
