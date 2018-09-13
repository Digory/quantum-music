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
    private final Scanner reader;
    private final List<Integer> MIDICodes;
    private final List<String> binaryStrings;
    private List<Integer> probabilities;
    private Map<Integer, List<String>> results;

    public FileReader(File file, Scanner reader) throws FileNotFoundException {
        this.file = file;
        this.reader = reader;
        MIDICodes = new ArrayList<>();
        binaryStrings = new ArrayList<>();
        readFile();
    }
    private void readFile() throws FileNotFoundException {
        findProbabilities();
        findMIDICodes(probabilities, results);
    }

    private void findProbabilities() throws FileNotFoundException {
        /*
        Each probability acts as a key in the 'results' HashMap. The values have
        to take into account the possibility of a probability occurring more
        than once, hence are stored in a List<String> object. The values are 
        in the form of binary strings, but have to be cleaned up (apostrophes 
        and commas removed).
        */
        probabilities = new ArrayList<>();
        results = new HashMap<>();

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
    }

    private void findMIDICodes(List<Integer> probabilities, Map<Integer, List<String>> results) {
        /*
        The probability list is sorted from highest to lowest probability. We
        then go through the HashMap using our probability keys; calculating the
        decimal versions of the binary values. We put these decimals in another
        list to be used as MIDI codes.
        */
        Collections.sort(probabilities);
        Collections.reverse(probabilities);

        for (Integer elements : probabilities) {
            for (String binaryString : results.get(elements)) {
                String binaryCode = binaryString.substring(1, 6);
                binaryStrings.add(binaryCode);
                int MIDICode = Integer.parseInt(binaryCode, 2);
                MIDICodes.add(MIDICode);
            }
        }
    }
    
    public List<Integer> getMIDICodes() {
        return MIDICodes;
    }

    public List<String> getBinaryStrings() {
        return binaryStrings;
    }

}
