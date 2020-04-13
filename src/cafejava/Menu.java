package cafejava;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private final String inventoryFileUrl;

    public Menu(String inventoryFileUrl) {
        this.inventoryFileUrl = inventoryFileUrl;
    }

    //display a menu of items, process the transaction and return the transaction list
    public ArrayList<Transaction> start() {
        boolean exit = false;
        //start the loop
        //set menu array, from inventory-file
        ArrayList<MenuItem> menuItems = getItemsFromFile(inventoryFileUrl);
        //all transactions will be stored to this array
        ArrayList<Transaction> menuTransactions = new ArrayList<>();
        do {
            //present the options
            Scanner choice = new Scanner(System.in);
            // welcome user 
            System.out.println(
                    "Press 1 to buy coffee\n"
                    + "Press 2 to exit "
            );
            // accept and process the users choice
            String userType = choice.nextLine();
            switch (userType) {
                case "1":
                    //display the coffee menu
                    displayMenu(menuItems);
                    //choose an item
                    int selectedCoffee = -1;
                    do {

                        System.out.println("Choose an item by insert the item id");
                        if (choice.hasNextInt()) {
                            selectedCoffee = choice.nextInt();
                            choice.nextLine();
                            break;
                        }
                        System.out.println("invalid input");
                        choice.next();
                    } while (true);
                    System.out.println("you chose " + menuItems.get(selectedCoffee).getItemName() + " €" + menuItems.get(selectedCoffee).getPrice());
                    boolean paid = false;
                    //pay by card or cash
                    do {
                        // choice.hasNext();
                        System.out.println("press 1 to pay by cash");
                        System.out.println("press 2 to pay by card");

                        String payMethod = choice.nextLine();
                        switch (payMethod) {
                            case "1":
                                //pay by cash
                                Transaction cash = new CashTransaction();
                                String cashReceipt = cash.initiate(menuItems.get(selectedCoffee));
                                menuTransactions.add(cash);
                                //present receipt
                                System.out.println(cashReceipt);
                                paid = true;
                                break;
                            case "2":
                                //pay by card
                                Transaction card = new CardTransaction();
                                String cardReceipt = card.initiate(menuItems.get(selectedCoffee));
                                menuTransactions.add(card);
                                //present receipt
                                System.out.println(cardReceipt);
                                paid = true;
                                break;
                            default:
                                //wrong input
                                System.out.println("invalid input try again");
                        }
                    } while (!paid);
                    break;
                case "2":
                    //end the loop
                    exit = true;
                    break;
                default:
                    System.out.println("wrong input, please read the menu and try again");
                    break;
            }
        } while (!exit);
        return menuTransactions;
    }

    //get the cafe menu from inventoryfile.txt
    private ArrayList<MenuItem> getItemsFromFile(String inventoryFileUrl) {
        ArrayList<MenuItem> menuItems = null;
        //read inventory-file.txt
        try {
            FileReader reader = new FileReader(inventoryFileUrl);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String fileContent = " ";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                fileContent += line;
            }
            
            fileContent = fileContent.replaceAll("null", "  ").trim();
            ///extract cafe name and price as string of words split by space character
            String[] words = fileContent.split("\\s+");

            //convert words array to array of menuitems
            menuItems = new ArrayList<>();
            for (int c = 0; c < words.length; c += 2) {
                menuItems.add(new MenuItem(words[c], Double.valueOf(words[c + 1])));
            }
            reader.close();

        } catch (IOException | NumberFormatException e) {
        }
        //return menu items
        return menuItems;
    }

    //show the coffee menu
    private void displayMenu(ArrayList<MenuItem> menuItems) {
        System.out.println("Item-Id\tItem-name\tPrice");
        for (int c = 0; c < menuItems.size(); c++) {
            System.out.println(c + "\t" + menuItems.get(c).getItemName() + "\t" + "€" + menuItems.get(c).getPrice());
        }
    }
}
