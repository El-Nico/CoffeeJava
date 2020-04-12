package cafejava;


public class Transaction {
    String dateTime;
    String itemName;
    String itemPrice;

    public String getDateTime() {
        return dateTime;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }
    
    
    public String initiate(MenuItem menuItem){
        return "initializing transaction for "+ menuItem.getItemName() + " €" + menuItem.getPrice()+"...";
    }
}
