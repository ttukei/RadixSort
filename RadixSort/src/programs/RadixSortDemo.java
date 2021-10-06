package programs;

import structures.LinkedQueue;
import structures.QueueADT;

/**
 * This utility class contains an implementation of "Radix Sort" using queues.
 * @author timontukei
 * @version 1
 */
public final class RadixSortDemo {

    /**
     * Inhibits external instantiation.
     */
    private RadixSortDemo() {
        // do nothing
    }
    
    /**
     * Sorts a queue of integers using Radix Sort.
     * @param theNumbers is a queue of unsorted positive integers
     * @return a queue of sorted integers
     */
    public static QueueADT<Integer> radixSort(final QueueADT<Integer> theNumbers) {
        
        final QueueADT<Integer> master = theNumbers;
        final int numOfDigitQueues = 10;
        @SuppressWarnings("unchecked")
        final LinkedQueue<Integer>[] digitQueues = new LinkedQueue[numOfDigitQueues];
        for (int digitQueue = 0; digitQueue < numOfDigitQueues; digitQueue++) {
            digitQueues[digitQueue] = new LinkedQueue<Integer>();
        }
        
        final int masterSize = master.size();
        int greatestPlaceValue = 0;
        int currentNumber = 0;
        for (int index = 0; index < masterSize; index++) {
            currentNumber = master.dequeue();
            master.enqueue(currentNumber);
            
            if (String.valueOf(currentNumber).length() > greatestPlaceValue) {
                greatestPlaceValue = String.valueOf(currentNumber).length();
            }
        }
        
        currentNumber = 0;
        
        for (int currPlaceValue = 0; currPlaceValue < greatestPlaceValue; currPlaceValue++) {
            for (int index = 0; index < masterSize; index++) {
                currentNumber = master.dequeue();
                // Pads zeros onto the left of current number until its length is equal to the 
                // greatest place value.
                final String padZero = 
                        String.format("%0" + greatestPlaceValue + "d", currentNumber);
                // Gets the digit at the current place value;
                final int digit = Character.getNumericValue(
                        padZero.charAt(greatestPlaceValue - currPlaceValue - 1)
                        );
                digitQueues[digit].enqueue(currentNumber);
            }
            
            int digitQueue = 0;
            LinkedQueue<Integer> current;
            while (digitQueue < numOfDigitQueues) {
                current = digitQueues[digitQueue];
                if (current.isEmpty()) {
                    digitQueue++;
                } else {
                    master.enqueue(current.dequeue());
                }
            }
        }
        
        return master;
    }
}