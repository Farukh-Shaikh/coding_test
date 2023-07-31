import com.fasterxml.jackson.databind.ObjectMapper;
import com.smallworld.TransactionDataFetcher;
import com.smallworld.TransactionRecord;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<TransactionRecord> transactions = readTransactions("transactions.json");

        //checking if transactions are found and parsed
        if(transactions == null){
            System.out.println("No transactions found");
        }

        TransactionDataFetcher dataFetcher = new TransactionDataFetcher(transactions);

        // Calculate and print the total transaction amount
        double totalAmount = dataFetcher.getTotalTransactionAmount();
        System.out.println("Total Transaction Amount: " + totalAmount);

        // Calculate total amount sent by sender
        String senderFullName = "Tom Shelby";
        double totalAmountSentBySender = dataFetcher.getTotalTransactionAmountSentBy(senderFullName);
        System.out.println("Total Transaction Amount Sent by " + senderFullName + ": " + totalAmountSentBySender);

        // Calculate max amount
        double maxAmount = dataFetcher.getMaxTransactionAmount();
        System.out.println("Maximum Transaction Amount: " + maxAmount);

        // print the count of unique clients
        int uniqueClientsCount = dataFetcher.countUniqueClients();
        System.out.println("Count of Unique Clients: " + uniqueClientsCount);

        // Get transactions by beneficiary names
        Map<String, List<TransactionRecord>> transactionsByBeneficiaryName = dataFetcher.getTransactionsByBeneficiaryName();

        // print ids of open issues
        Set<Integer> unsolvedIssueIds = dataFetcher.getUnsolvedIssueIds();
        System.out.println("Unsolved Issues: " + unsolvedIssueIds);

        // print messages of solved issues
        List<String> solvedIssueMessages = dataFetcher.getAllSolvedIssueMessages();
        System.out.println("Solved Messages: " + solvedIssueMessages);

        // Get the top 3 transactions
        List<TransactionRecord> top3Transactions = dataFetcher.getTop3TransactionsByAmount();
        System.out.println("\nTop 3 Transactions by Amount:");
        for (TransactionRecord transaction : top3Transactions) {
            System.out.println(transaction);
        }

        // most total sent amount
        Optional<TransactionRecord> topSender = dataFetcher.getTopSender();
        System.out.println("\nSender with Most Total Sent Amount:");
        topSender.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("No transactions found.")
        );

    }


    private static List<TransactionRecord> readTransactions(String path) {
        try {
            // read json with ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(new File(path), objectMapper.getTypeFactory().constructCollectionType(List.class, TransactionRecord.class));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return null;
        }
    }

}