package cafejava;

import java.time.LocalDateTime;

//super class helps to set details and process a transaction
public class Transaction {

    String dateTime;
    String itemName;
    double itemPrice;

    public String getDateTime() {
        return dateTime;
    }

    public String getItemName() {
        return itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String initiate(MenuItem menuItem) {
        //set the time of transaction
        setDateTime(LocalDateTime.now().toString());
        //set the purchased item
        setItemName(menuItem.getItemName());
        //set the price
        setItemPrice(menuItem.getPrice());
        return "initializing transaction for " + menuItem.getItemName() + " €" + menuItem.getPrice() + "...";
    }

    //return the details of this object for easy log to transaction-file.txt
    @Override
    public String toString() {
        return "DateTime:" + getDateTime() + "\t" + "ItemPurchased:" + getItemName() + "\t" + "ItemPrice:" + getItemPrice();
    }
}
