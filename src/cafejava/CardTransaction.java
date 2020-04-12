package cafejava;

import java.util.Scanner;

public class CardTransaction extends Transaction {

    @Override
    public String initiate(MenuItem menuItem) {
        System.out.println(super.initiate(menuItem));
        boolean paid = false;
        Scanner money = new Scanner(System.in);
        double payment = 0.00;
        double change = 0.00;
        //get card type
        boolean validCardType=false;
        do{
            System.out.println("select your card type, press 1 for visa or press 2 for master");
            String cardType= money.nextLine();
            if(null == cardType){}
            else switch (cardType) {
                case "1":
                    System.out.println("visa selected");
                    validCardType=true;
                    break;
                case "2":
                    System.out.println("master selected");
                    validCardType=true;
                    break;
                default:
                    System.out.println("invalid input");
                    break;
            }
        }while(!validCardType);
        do {
            System.out.println("please enter your payment");
            if (money.hasNextDouble()) {
                payment = money.nextDouble();
                if (payment < menuItem.getPrice()) {
                    System.out.println("the money is not enough");
                    money.next();
                    continue;
                } else if (payment > menuItem.getPrice()) {
                    change = payment - menuItem.getPrice();
                }
                paid = true;
            } else {
                System.out.println("invalid input try again");
                money.next();
            }
        } while (!paid);
        money.close();
        return "//////////////RECEIPT FOR CAFEJAVA/////////////////////\n"
                + "/// Thank you for purchasing ///\n"
                + "/// " + menuItem.getItemName() + "\t" + "€" + menuItem.getPrice() + " ///\n"
                + "/// and your change is: " + change + " ///\n"
                + "///////////////////////////////////////////////////////";
    }
}
