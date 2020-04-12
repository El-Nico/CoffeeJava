package cafejava;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    String inventoryFileUrl;

    public Menu(String inventoryFileUrl) {
        this.inventoryFileUrl = inventoryFileUrl;
    }

    public void start() {
        boolean exit = false;
        //start the loop
        do {
            //initialize temporary transaction array, inventory from file
            ArrayList<MenuItem> menuItems = getItemsFromFile(inventoryFileUrl);
            ArrayList<Transaction> menuTransactions = new ArrayList<>();
            //present the options
            Scanner choice = new Scanner(System.in);
            // welcome user 
            System.out.println(
                    "welcome to coffee shop\n"
                    + "Press 1 to buy coffee\n"
                    + "Press 2 to exit ");
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
                                String cashReceipt=cash.initiate(menuItems.get(selectedCoffee));
                                menuTransactions.add(cash);
                                //present receipt
                                System.out.println(cashReceipt);
                                paid = true;
                                break;
                            case "2":
                                //pay by card
                                Transaction card = new CardTransaction();
                                String cardReceipt=card.initiate(menuItems.get(selectedCoffee));
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
                    //set the transaction array in caferunner
                    exit = true;
                    break;
                default:
                    System.out.println("wrong input, please read the menu and try again");

                    break;
            }
        } while (!exit);
    }

    //get the cafe menu from inventoryfile.txt
    private ArrayList<MenuItem> getItemsFromFile(String inventoryFileUrl) {
        ArrayList<MenuItem> menuItems = null;
        //read file
        try {
            FileReader reader = new FileReader(inventoryFileUrl);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String fileContent = " ";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                fileContent += line;
            }
            //extract cafe name and price as string of words
            fileContent = fileContent.replaceAll("null", "  ").trim();
            String[] words = fileContent.split("\\s+");

            //convert words array to array of menuitems
            menuItems = new ArrayList<>();
            for (int c = 0; c < words.length; c += 2) {
                menuItems.add(new MenuItem(words[c], Double.valueOf(words[c + 1])));
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        //return menu items
        return menuItems;
    }

    private void displayMenu(ArrayList<MenuItem> menuItems) {
        System.out.println("Item-Id\tItem-name\tPrice");
        for (int c = 0; c < menuItems.size(); c++) {
            System.out.println(c + "\t" + menuItems.get(c).getItemName() + "\t" + "€" + menuItems.get(c).getPrice());
        }
    }
}
