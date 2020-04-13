package cafejava;

import java.io.File;
import java.io.IOException;

public class CafeJava {

    public static void main(String[] args) throws IOException {
        //start a new cafe
        new CafeRunner(
                //a menu
                new Menu(new File(".").getCanonicalPath() + "/src/cafejava/inventory-file.txt"),
                //inventory file url
                new File(".").getCanonicalPath() + "/src/cafejava/inventory-file.txt",
                //transaction file url
                new File(".").getCanonicalPath() + "/src/cafejava/transaction-file.txt"
        ).runCafe();
    }
}
