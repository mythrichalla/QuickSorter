// @author Mythri Challa
// CS 3345.004, Spring 2019

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    // The main class takes in command line arguments for an input and output file and goes through each command
    // and calls the corresponding functions from the QuickSorter class
    public static void main(String[] args)
    {
        if (args.length == 4)
        {
            // Declarations and initializations for the files and PrintWriters
            PrintWriter reportWriter;
            PrintWriter sortedWriter;
            PrintWriter unsortedWriter;
            File reportFile = new File(args[1]);
            File unsortedFile = new File(args[2]);
            File sortedFile = new File (args[3]);
            int listSize = Integer.parseInt(args[0]);

            try
            {
                // Create output files if DNE
                if (!reportFile.exists())
                    reportFile.createNewFile();

                if (!sortedFile.exists())
                    sortedFile.createNewFile();

                if (!unsortedFile.exists())
                    unsortedFile.createNewFile();

                // Open files for writing
                reportWriter = new PrintWriter(reportFile);
                sortedWriter = new PrintWriter(sortedFile);
                unsortedWriter = new PrintWriter(unsortedFile);

                // Record the unsorted array into the unsortedFile, have 4 different versions of the arrayList
                ArrayList<Integer> list1 = QuickSorter.generateRandomList(listSize);
                for (int i = 0; i < list1.size(); i++)
                    unsortedWriter.println(list1.get(i));

                ArrayList<Integer> list2 = new ArrayList<>();
                for (int i = 0; i < list1.size(); i++)
                    list2.add(list1.get(i));

                ArrayList<Integer> list3 = new ArrayList<>();
                for (int i = 0; i < list1.size(); i++)
                    list3.add(list1.get(i));

                ArrayList<Integer> list4 = new ArrayList<>();
                for (int i = 0; i < list1.size(); i++)
                    list4.add(list1.get(i));



                // Calling the timedQuickSort method 4 times with each of the 4 enum types
                reportWriter.println("Array Size = " + listSize);
                reportWriter.println("FIRST_ELEMENT : " + QuickSorter.timedQuickSort(list1, QuickSorter.PivotStrategy.FIRST_ELEMENT));
                reportWriter.println("RANDOM_ELEMENT : " + QuickSorter.timedQuickSort(list2, QuickSorter.PivotStrategy.RANDOM_ELEMENT));
                reportWriter.println("MEDIAN_OF_THREE_RANDOM_ELEMENTS : " + QuickSorter.timedQuickSort(list3,
                        QuickSorter.PivotStrategy.MEDIAN_OF_THREE_RANDOM_ELEMENTS));
                reportWriter.println("MEDIAN_OF_THREE_ELEMENTS : " + QuickSorter.timedQuickSort(list4,
                        QuickSorter.PivotStrategy.MEDIAN_OF_THREE_ELEMENTS));

                // Writing the sorted list to sortedWriter
                for (int i = 0; i < list1.size(); i++)
                    sortedWriter.println(list1.get(i));

                // Closing the PrintWriters and printing output messages to console
                reportWriter.close();
                sortedWriter.close();
                unsortedWriter.close();
                System.out.println("Report output has been written to the file: " + args[1]);
                System.out.println("Unsorted and sorted lists have been written to the files: " + args[2] + " " + args[3]);
            }

            catch (FileNotFoundException fileException) {
                System.out.println(fileException.getMessage());
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        else
            System.out.println("Error: must input four command line arguments.");
    }
}
