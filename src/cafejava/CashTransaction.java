package cafejava;

import java.util.Scanner;

public class CashTransaction extends Transaction {

    private double amountTendered;
    private double changeGiven;

    //initiate a card transaction will return the recept
    @Override
    public String initiate(MenuItem menuItem) {
        //initiate the super first
        System.out.println(super.initiate(menuItem));
        Scanner money = new Scanner(System.in);
        double payment = 0.00;
        //get the payment from the user
        do {
            System.out.println("please enter your payment");
            //check if input is double
            if (money.hasNextDouble()) {
                payment = money.nextDouble();
                //the price is not enough? start the loop again
                if (payment < menuItem.getPrice()) {
                    System.out.println("the money is not enough");
                } else if (payment >= menuItem.getPrice()) {
                    //if correct price set amounttendered and change
                    this.amountTendered = payment;
                    this.changeGiven = payment - menuItem.getPrice();
                    break;
                }
            } else {
                System.out.println("invalid input try again");
                money.next();
            }
        } while (true);
        return "//////////////RECEIPT FOR CAFEJAVA/////////////////////\n"
                + "/// Thank you for purchasing ///\n"
                + "/// " + menuItem.getItemName() + "\t" + "€" + menuItem.getPrice() + " ///\n"
                + "/// and your change is: " + changeGiven + " ///\n"
                + "///////////////////////////////////////////////////////";
    }

    //return the details of this transaction for eeasy print and save to file
    @Override
    public String toString() {
        return super.toString() + "\t" + "AmountTendered:" + amountTendered + "\t" + "ChangeGiven:" + changeGiven;
    }
}
