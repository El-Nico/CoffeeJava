package cafejava;

import java.util.Scanner;

public class CashTransaction extends Transaction {
    
    @Override
    public String initiate(MenuItem menuItem) {
        System.out.println(super.initiate(menuItem));
        boolean paid = false;
        Scanner money = new Scanner(System.in);
        double payment = 0.00;
        double change = 0.00;
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
