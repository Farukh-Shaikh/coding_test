package com.smallworld;

import java.util.*;

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
        for (TransactionRecord transaction : transactions) {
            if (transaction.getSenderFullName().equals(clientFullName) || transaction.getBeneficiaryFullName().equals(clientFullName)) {
                if (!transaction.isIssueSolved()) {
                    return true;
                }
            }
        }
        return false;
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
        Set<Integer> unsolvedIssueIds = new HashSet<>();
        for (TransactionRecord transaction : transactions) {
            if (!transaction.isIssueSolved() && transaction.getIssueId() != null) {
                unsolvedIssueIds.add(transaction.getIssueId());
            }
        }
        return unsolvedIssueIds;
    }

    /**
     * Returns a list of all solved issue messages
     */
    public List<String> getAllSolvedIssueMessages() {
        List<String> solvedIssueMessages = new ArrayList<>();
        for (TransactionRecord transaction : transactions) {
            //check if issue is solved and message is not null
            if (transaction.isIssueSolved() && transaction.getIssueMessage() != null) {
                solvedIssueMessages.add(transaction.getIssueMessage());
            }
        }
        return solvedIssueMessages;
    }

    /**
     * Returns the 3 transactions with highest amount sorted by amount descending
     */
    public List<TransactionRecord> getTop3TransactionsByAmount() {
        // Sort in desc by amount
        transactions.sort(Comparator.comparingDouble(TransactionRecord::getAmount).reversed());
        return transactions.subList(0, 3);
    }

    /**
     * Returns the sender with the most total sent amount
     */
    public Optional<TransactionRecord> getTopSender() {
        Map<String, Double> totalSentAmount = new HashMap<>();

        // Calculate the total sent amount
        for (TransactionRecord transaction : transactions) {
            String senderFullName = transaction.getSenderFullName();
            // add total sent amount by sender
            totalSentAmount.put(senderFullName, totalSentAmount.getOrDefault(senderFullName, 0.0) + transaction.getAmount());
        }

        // Find the sender with the most total sent amount with Optional
        Optional<Map.Entry<String, Double>> topSenderEntry = totalSentAmount.entrySet().stream().max(Map.Entry.comparingByValue());

        return topSenderEntry.map(entry -> transactions.stream().filter(transaction -> transaction.getSenderFullName().equals(entry.getKey())).findFirst().orElse(null));
    }

}
