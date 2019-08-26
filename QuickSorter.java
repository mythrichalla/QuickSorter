// @ author Mythri Challa
// CS 3345.004, Spring 2019
/* The QuickSorter class is a static class that is not instantiated as an object - instead it is used to call
 * different versions of the QuickSort method and returns the elapsed runtime of each of the methods. It includes
 * the use of enumerated type for the pivot strategy, as well as a method that generates a random list of integers,
 * given the arrayList size by the user. Finally, the results are written to files for the user.
 */

import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;

public class QuickSorter
{
    private QuickSorter() {}        // Default constructor to prevent accidental instantiation of QuickSorter

    /*<p> The timedQuickSort method uses a switch case to choose each version of quickSort methods, and records their runtime
     * @param ArrayList<E> list, PivotStrategy strategy (enum type of the pivot strategy)
     * @return Duration timeDiff (the run time)
     */
    public static <E extends Comparable<E>> Duration timedQuickSort(ArrayList<E> list, PivotStrategy strategy) throws NullPointerException
    {
        // Throws exception if list or strategy is null
        if (list == null || strategy == null)
            throw new NullPointerException("Either the ArrayList or the strategy enumeration are null.");

        long startTime = System.nanoTime();     // Start time before rest of code begins

        switch(strategy)                // Switch case to go through each of the different QuickSort methods
        {
            case FIRST_ELEMENT:
                // Declare last index and call the QuickSort method version with first pivot
                int last = list.size() - 1;
                firstQuickSort(list, 0, last);
                break;

            case RANDOM_ELEMENT:
                // Declare last index and call the QuickSort method version with a random element as the pivot
                int lastRandom = list.size() - 1;
                randomQuickSort(list, 0, lastRandom);
                break;

            case MEDIAN_OF_THREE_RANDOM_ELEMENTS:
                // Declare last index and call the QuickSort method version with the median of 3 random elements
                int lastMedian = list.size() - 1;
                medianRandomQuickSort(list, 0, lastMedian);
                break;

            case MEDIAN_OF_THREE_ELEMENTS:
                // Declare last index and call the QuickSort method version with the median of 1st, center, and last elements
                int lastThree = list.size() - 1;
                medianThreeQuickSort(list, 0, lastThree);
                break;

            default:
                break;
        }

        long finishTime = System.nanoTime();    // Finish time after the process ends
        return Duration.ofNanos(finishTime - startTime);    // Calculating and returning the elapsed time of the method
    }

    /*<p> This generic swap method is of type void, it's used for generic swaps in the other QuickSort methods
     * @param ArrayList<E> list, integer indices a and b
     * @return void
     */
    private static <E extends Comparable<E>> void swap (ArrayList<E> list, int a, int b)
    {
        if (a >= 0 && b >= 0 && a < list.size() && b < list.size())        // Runs only if necessary conditions are met
        {
            // Sets a temp variable, then swaps the two given elements
            E temp = list.get(a);
            list.set(a, list.get(b));
            list.set(b, temp);
        }
    }

    /* <p> The firstQuickSort method sorts the list by choosing the first element as the pivot
     * @param ArrayList<E> list, integer indices left and right (to indicate sides of the arrayList)
     * @return void
     */
    private static <E extends Comparable<E>> void firstQuickSort(ArrayList<E> list, int left, int right)
    {
        // Runs so long as the left element is less than the right element
        if (left < right)
        {
            E pivot = list.get(left);       // Set the pivot to the leftmost element (first element)
            int i = left, j = right;        // i and j are the temporary left and right elements

            while (i < j)
            {
                i += 1;         // Moves the temporary left pivot to the right if i is less than j
                while (i <= right && list.get(i).compareTo(pivot) < 0)
                    i += 1;

                while (j >= left && list.get(j).compareTo(pivot) > 0)
                    j -= 1;           // Moves the pivot to the left if j is bigger than the left side

                if (i <= right && i < j)
                    swap(list, i, j);   // Swaps the two current variables if out of place
            }

            // Swap again and recursively call firstQuickSort to keep traversing through the list
            swap(list, left, j);
            firstQuickSort(list, left, j-1);
            firstQuickSort(list, j+1, right);
        }
    }

    /* <p> The randomQuickSort method chooses a random element as the pivot before swapping
     * @param ArrayList<E> list, integer indices left and right (to indicate sides of the arrayList)
     * @return void
     */
    private static <E extends Comparable<E>> void randomQuickSort(ArrayList<E> list, int left, int right)
    {
        // Checking default cases for left and right endpoints
        if (left >= right)
            return;
        else if (left < 0)
            return;
        else if (right > list.size() - 1)
            return;

        // Calls the helper method to choose the pivot, and then calls the randomQuickSort method recursively to
        // traverse through the list
        int pivot = splitRandomElement(list, left, right);
        randomQuickSort(list, left, pivot - 1);
        randomQuickSort(list, pivot + 1, right);
    }

    /* <p> The chooseRandomElement method chooses a random element as the pivot before swapping
     * @param ArrayList<E> list, integer indices left and right (to indicate sides of the arrayList)
     * @return int index of an element
     */
    private static <E extends Comparable<E>> int splitRandomElement(ArrayList<E> list, int left, int right)
    {
        int pivot = left + ((int)Math.random() * (list.size())) / (right - left + 1);  // Choosing random elem as pivot
        int last = right;               // Setting the temporary right bound
        swap (list, pivot, right);      // Swap the randomly chosen pivot and the right bound and move right down
        right --;

        // Moves the pivot based on the element values
        while (left <= right)
        {
            if(list.get(left).compareTo(list.get(last)) < 0)
                left++;
            else
            {
                swap(list, left, right);
                right--;
            }
        }
        // Final swap and returns the leftmost element after the split
        swap(list, left, last);
        return left;
    }

    /* <p> The medianRandomQuickSort method calculates the pivot as the median of 3 random elements
     * @param ArrayList<E> list, integer indices left and right (to indicate sides of the arrayList)
     * @return void
     */
    private static <E extends Comparable<E>> void medianRandomQuickSort(ArrayList<E> list, int left, int right)
    {
        // Checking default cases for left and right endpoints
        if (left >= right)
            return;
        else if (left < 0)
            return;
        else if (right > list.size() - 1)
            return;

        // Calls the helper method to choose the pivot, and then calls the randomQuickSort method recursively to
        // traverse through the list
        int pivot = splitMedianElement(list, left, right);
        medianRandomQuickSort(list, left, pivot - 1);
        medianRandomQuickSort(list, pivot + 1, right);
    }

    /* <p> The splitMedianElement method acts as a helper method for the medianRandomQuickSort method
     * @param ArrayList<E> list, integer indices left and right (to indicate sides of the arrayList)
     * @return int index of an element
     */
    private static <E extends Comparable<E>> int splitMedianElement(ArrayList<E> list, int left, int right)
    {
        // First choose 3 random elements, making sure to use the subrange instead of entire list
        Random f = new Random();
        int first = f.nextInt(right - left) + left;

        Random s = new Random();
        int second = s.nextInt(right - left) + left;

        Random t = new Random();
        int third = t.nextInt(right - left) + left;

        int pivot;          // Declare a variable for the pivot

        // Code to calculate the median
        if((first < second && second < third) || (third < second && second < first))
            pivot = second;
        else if((second < first && first < third) || (third < first && first < second))
            pivot = first;
        else
            pivot = third;

        int last = right;       // Setting the right bound for the sublist
        swap(list, pivot, right);
        right--;

        // Traverses the list and moves the pivot while comparing the element values
        while (left <= right)
        {
            if (list.get(left).compareTo(list.get(last))< 0)
                left++;
            else
            {
                swap(list, left, right);
                right--;
            }
        }
        // Final swap and returns leftmost element
        swap(list, left, last);
        return left;
    }

    /* <p> The medianThreeQuickSort method calculates the pivot as the median of the first, middle, and last elements
     * @param ArrayList<E> list, integer indices left and right (to indicate sides of the arrayList)
     * @return void
     */
    private static <E extends Comparable<E>> void medianThreeQuickSort(ArrayList<E> list, int left, int right)
    {
        // Checking default cases for left and right endpoints
        if (left >= right)
            return;
        else if (left < 0)
            return;
        else if (right > list.size() - 1)
            return;

        // Calls the helper method to choose the pivot, and then calls the randomQuickSort method recursively to
        // traverse through the list
        int pivot = splitMedianThree(list, left, right);
        medianThreeQuickSort(list, left, pivot - 1);
        medianThreeQuickSort(list, pivot + 1, right);
    }

    /* <p> The splitMedianThree method acts as a helper method for the medianThreeQuickSort method
     * @param ArrayList<E> list, integer indices left and right (to indicate sides of the arrayList)
     * @return int index of an element
     */
    private static <E extends Comparable<E>> int splitMedianThree(ArrayList<E> list, int left, int right)
    {
        int middle = (left + right) / 2;
        int pivot;

        // Logic to find the median
        if((left < middle && middle < right) || (right < middle && middle < left))
            pivot = middle;
        else if((middle < left && left < right) || (right < left && left < middle))
            pivot = left;
        else
            pivot = right;

        int last = right;       // Setting the right bound for the sublist
        swap(list, pivot, right);
        right--;

        // Traverses the list and moves the pivot while comparing the element values
        while (left <= right)
        {
            if (list.get(left).compareTo(list.get(last))< 0)
                left++;
            else
            {
                swap(list, left, right);
                right--;
            }
        }
        // Final swap and returns leftmost element
        swap(list, left, last);
        return left;
    }

    /* <p> The generateRandomList method is used to generate an arrayList of random integers
     * @param integer size (the given size of the array)
     * @return ArrayList<Integer> list (list of randomly generated integers)
     */
    public static ArrayList<Integer> generateRandomList(int size) throws IllegalArgumentException
    {
        // Throws exception if the arrayList size given is a negative value
        if (size < 0)
            throw new IllegalArgumentException("Size is non-negative, this is invalid.");

        // Declarations for arrayList and random object
        Random random = new Random();
        ArrayList<Integer> list = new ArrayList<>();

        // Generates random numbers and adds them to the arrayList
        for (int i = 0; i < size; i++)
            list.add(random.nextInt());

        return list;            // Returns the arrayList of randomly generated numbers
    }

    // Enumeration type for PivotStrategy
    public static enum PivotStrategy
    {
        FIRST_ELEMENT,
        RANDOM_ELEMENT,
        MEDIAN_OF_THREE_RANDOM_ELEMENTS,
        MEDIAN_OF_THREE_ELEMENTS
    }
}
