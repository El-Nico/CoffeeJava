package cafejava;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CafeRunner {

    private final Menu menu;
    private final String inventoryFileUrl;
    private final String transactionFileUrl;
    private static ArrayList<Transaction> transactions;

    public CafeRunner(Menu menu, String inventoryFileUrl, String transactionFileUrl) {
        this.menu = menu;
        this.inventoryFileUrl = inventoryFileUrl;
        this.transactionFileUrl = transactionFileUrl;
    }

    public void runCafe() {
        //main program loop
        do {
            //print welcome
            System.out.println("Welcome to cafe Java");
            //print/start menu
            //save all transactions on exit
            setTransactions(menu.start());
            //write daily transactions to transaction-file.txt
            saveTransactionsToFile(transactions);
            System.out.println("Written to transaction-file.txt...");
            //end the program
            System.out.println("goodbye");
            break; 
        } while (true);
    }

    public static void setTransactions(ArrayList<Transaction> transactions) {
        CafeRunner.transactions = transactions;
    }

    //write daily transactions to transaction-file.txt and also print it to console
    private void saveTransactionsToFile(ArrayList<Transaction> transactions) {
        System.out.println("///////////////////DAILY TRANSACTIONS//////////////////");
        transactions.forEach((transaction) -> {
            writeTransactionToFile(transactionFileUrl, transaction.toString());
            System.out.println(transaction.toString());
        });
    }

    //log transaction to transaction file using filewriter
    protected void writeTransactionToFile(String fileName, String line) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            try (BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
        }
    }
}
