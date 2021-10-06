package programs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

import structures.LinkedQueue;
import structures.QueueADT;

/**
 * This console application prompts the user accepts a queue of integers from the user and
 * sort them using the radixSort algorithm. 
 * @author timontukei
 * @version 1
 */
public final class ConsoleApplication {
    
    /**
     * Inhibits external instantiation.
     */
    private ConsoleApplication() { 
        // do nothing
    }
    
    /**
     * Prompts user for a file containing integers until a valid file name is entered. 
     * @return A scanner object for the inputed file.
     * @throws FileNotFoundException
     */
    private static Scanner getFileOfUnsortedIntegers() {
        
        Scanner fileScanner = null;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please enter a file name: ");
            final File file = new File(scanner.next());
            fileScanner = new Scanner(file);
        } catch (final FileNotFoundException e) {
            System.out.println("File does not exist!\n");
            getFileOfUnsortedIntegers();
        }
        
        return fileScanner;
    }
    
    /**
     * Uses a scanner for a file to get integers and store them in a queue.
     * @param theFile a scanner object for an input file.
     * @return A queue of unsorted integers read from a file
     */
    private static QueueADT<Integer> readUnsortedIntegers(final Scanner theFile) {
        
        final QueueADT<Integer> unsortedIntegers = new LinkedQueue<>();
        while (theFile.hasNextLine()) {
            final String number = theFile.nextLine();
            unsortedIntegers.enqueue(Integer.valueOf(number));
        }
        theFile.close();
        return unsortedIntegers;
    }
    
    /**
     * Sorts a queue of unsorted integers using Radix Sort.
     * @param theUnsortedIntegers a queue of unsorted integers.
     * @return A sorted queue of integers
     */
    private static QueueADT<Integer> sort(final QueueADT<Integer> theUnsortedIntegers) {
        
        return RadixSortDemo.radixSort(theUnsortedIntegers);
    }
    
    /**
     * Writes a queue of sorted integers to a file titled "output.txt".
     * @param theSortedIntegers
     */
    private static void writeToOutputFile(final QueueADT<Integer> theSortedIntegers) {
        
        final File outputFile = new File("output.txt");
        try (Writer output = new FileWriter(outputFile, false)) {
            while (!theSortedIntegers.isEmpty()) {
                output.write(theSortedIntegers.dequeue() + "\n");
            }
            output.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Executes the program.
     * @param theArgs
     */
    public static void main(final String[] theArgs) {
        
        writeToOutputFile(sort(readUnsortedIntegers(getFileOfUnsortedIntegers())));
    }
}
