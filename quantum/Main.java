package quantum;

import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/quantum/distribution.csv");
        FileReader filereader = new FileReader(file);
        SwingUtilities.invokeLater(new UserInterface(filereader.getDecimals()));
    }
}
