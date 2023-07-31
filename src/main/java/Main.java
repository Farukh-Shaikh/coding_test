import com.fasterxml.jackson.databind.ObjectMapper;
import com.smallworld.TransactionDataFetcher;
import com.smallworld.TransactionRecord;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<TransactionRecord> transactions = readTransactions("transactions.json");

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