package quantum;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/quantum/distribution.csv");
        Scanner reader = new Scanner(System.in);
        FileReader fileReader = new FileReader(file, reader);
        SwingUtilities.invokeLater(new UserInterface(fileReader.getMIDICodes(), fileReader.getBinaryStrings()));
    }
}
