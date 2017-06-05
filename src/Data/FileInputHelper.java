package Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import edu.kit.informatik.Terminal;

/**
 * Helper class for reading text files.
 * 
 * @author IPD Reussner, KIT
 * @author ITI Sinz, KIT
 * @version 1.1
 */
public final class FileInputHelper {
    
    public static List<String[]> splitStrings = new LinkedList<String[]>();
    
    /**
     * Private constructor to avoid instantiation.
     */
    private FileInputHelper() {
        // intentionally left blank
    }

    /**
     * Reads the specified file and returns its content as a String array, where the first array field contains the
     * file's first line, the second field contains the second line, and so on.
     * 
     * @param file
     *            the file to be read
     * @return the content of the file
     */
    public static String[] read(String file) {
        StringBuilder result = new StringBuilder();

        FileReader in = null;
        try {
            in = new FileReader(file);
        } catch (FileNotFoundException e) {
            Terminal.printLine("Error, " + e.getMessage());
            System.exit(1);
        }

        BufferedReader reader = new BufferedReader(in);
        try {
            String line = reader.readLine();
            
            while (line != null) {
                result.append(line);
                
                if (line != null) {
                    result.append("\n");
                }
       
                //file throws exception for emtpy line
              splitStrings.add(GetArguments.splitString(line));
       

                line = reader.readLine();

            
                }
                
            
        } catch (IOException e) {
            Terminal.printLine("Error, " + e.getMessage());
            System.exit(1);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                // no need for handling this exception
            }
             catch(NumberFormatException e) {
                 throw new IllegalArgumentException();
             }
        }

        return result.toString().split("\n");
    }

}
