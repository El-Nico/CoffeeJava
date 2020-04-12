package cafejava;

import java.util.ArrayList;

public class CafeRunner {

    Menu menu;
    String inventoryFileUrl;
    String transactionFileUrl;
    ArrayList<Transaction> transactions;

    public CafeRunner(Menu menu, String inventoryFileUrl, String transactionFileUrl) {
        this.menu = menu;
        this.inventoryFileUrl = inventoryFileUrl;
        this.transactionFileUrl = transactionFileUrl;
    }

    public void runCafe() {
        //start loop
        //main program loop
        boolean mainExit = false;
        do {
            //print welcome
            //print/start menu
            menu.start();
            //save all transactions on exit
            saveTransactions(getTransactions());
            //end the program
            mainExit = true;
            System.out.println("goodbye");
        } while (!mainExit);
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void saveTransactions(ArrayList<Transaction> transactions) {
        //write all transactions to the transaction file
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

}
