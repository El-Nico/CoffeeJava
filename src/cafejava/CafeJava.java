package cafejava;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Nicholas
 */
public class CafeJava {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        new CafeRunner(
                //initialize and start this cafe here
                //a menu
                new Menu(new File(".").getCanonicalPath() + "/src/cafejava/inventory-file.txt"),
                //inventory file url
                new File(".").getCanonicalPath() + "/src/cafejava/inventory-file.txt",
                //transaction file url
                new File(".").getCanonicalPath() + "/src/cafejava/transaction-file.txt"
        ).runCafe();
    }

}
