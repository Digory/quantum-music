package quantum;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FileReader {
    private final File file;

    public FileReader(File file) {
        this.file = file;
    }
    
    public List<Integer> getDecimals() throws FileNotFoundException {
        /*
        Each probability acts as a key in the 'results' HashMap. The values have
        to take into account the possibility of a probability occurring more
        than once, hence are stored in a List<Integer> object. The values are 
        in the form of binary strings, but have to be cleaned up (apostrophes 
        and commas removed) before being sent to the getMusicNotes() method.
        */
        List<Integer> probabilities = new ArrayList<>();
        Map<Integer, List<String>> results = new HashMap<>();
        Scanner reader = new Scanner(file);

        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] parts = line.split(",");
            try {
                if (results.containsKey(Integer.valueOf(parts[1]))) {
                    results.get(Integer.valueOf(parts[1])).add(parts[0]);
                } else {
                    List<String> binaryStrings = new ArrayList<>();
                    binaryStrings.add(parts[0]);
                    results.put(Integer.valueOf(parts[1]), binaryStrings);
                    probabilities.add(Integer.valueOf(parts[1]));
                }
            } catch (NumberFormatException e) {
            }
        }
        return getMusicNotes(probabilities, results);
    }

    private List<Integer> getMusicNotes(List<Integer> probabilities, Map<Integer, List<String>> results) {
        /*
        The probability list is sorted from highest to lowest probability. We
        then go through the HashMap using our probability keys; calculating the
        decimal versions of the binary values. We put these decimals in another
        list and return it.
        */
        Collections.sort(probabilities);
        Collections.reverse(probabilities);
        List<Integer> decimals = new ArrayList<>();
        
        for (Integer elements : probabilities) {
            for (String binaryStrings : results.get(elements)){
            String binaryCode = binaryStrings.substring(1, 6);
            int decimal = Integer.parseInt(binaryCode, 2);
            decimals.add(decimal);
        }}
        return decimals;
    }

}
