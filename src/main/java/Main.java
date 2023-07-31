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
        TransactionDataFetcher dataFetcher = new TransactionDataFetcher(transactions);

        // Calculate and print the total transaction amount
        double totalAmount = dataFetcher.getTotalTransactionAmount();
        System.out.println("Total Transaction Amount: " + totalAmount);
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