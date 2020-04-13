package cafejava;

import java.util.Scanner;

public class CardTransaction extends Transaction {

    private String cardType;

    //initiate a card transaction returns the receipt at the end of the transaction
    @Override
    public String initiate(MenuItem menuItem) {
        //initialize the the super first
        System.out.println(super.initiate(menuItem));
        boolean paid = false;
        Scanner money = new Scanner(System.in);
        double payment = 0.00;
        double change = 0.00;
        //get card type
        boolean validCardType = false;
        do {
            System.out.println("select your card type, press 1 for visa or press 2 for master");
            String cardType = money.nextLine();
            if (null == cardType) {
            } else {
                //set the cardtype
                switch (cardType) {
                    case "1":
                        System.out.println("visa selected");
                        this.cardType = "visa";
                        validCardType = true;
                        break;
                    case "2":
                        System.out.println("master selected");
                        this.cardType = "master";
                        validCardType = true;
                        break;
                    default:
                        System.out.println("invalid input");
                        break;
                }
            }
        } while (!validCardType);
        //return the receipt
        return "//////////////RECEIPT FOR CAFEJAVA/////////////////////\n"
                + "/// Thank you for purchasing ///\n"
                + "/// " + menuItem.getItemName() + "\t" + "€" + menuItem.getPrice() + " ///\n"
                + "/// and card type is: " + cardType + " ///\n"
                + "///////////////////////////////////////////////////////";
    }

    //string value of this transaction for easy entry to file
    @Override
    public String toString() {
        return super.toString() + "\t" + "CardType:" + cardType;
    }
}