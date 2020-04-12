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
            System.out.println("welcome to coffee shop"
                    + "Press 1 to buy coffee"
                    + "Press 2 to exit ");
            // accept and process the users choice
            String userType = choice.nextLine();
            switch (userType) {
                case "1":
                    //display the coffee menu
                    displayMenu();
                    //choose an item
                    System.out.println("Choose an item by insert the item id");
                    //pay by card or cash
                    //?select card type
                    //present receipi
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
        System.out.println("i got here");
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

    private void displayMenu() {
        
    }
}
