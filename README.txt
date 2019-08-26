README File

Name: Mythri Challa
Section: CS 3345.004
Project: Project 5

- The project was developed and compiled in IntelliJ, and also tested through Windows command line terminal
- The version of Java used is Java 8
- Arguments for the file names are passed in through command line from IntelliJ or through terminal
- All methods should work normally
- Program works for all test cases provided with instructions
- Exceptions are thrown for when the arrayList size is negative or if the enumerations or list is null
- Error checking is done for whether the input file exists, whether 4 arguments have been passed in, and also
	for whether the output file exists (if output file doesn't exist, a new one will be created) 

-*-*- SAMPLE RUNS -*-*-

-- Error Handling Cases -- 

D:\Users\mythr\Documents\Spring 2019\CS 3345\Project5\src>java Main one
Error: must input four command line arguments.

D:\Users\mythr\Documents\Spring 2019\CS 3345\Project5\src>java Main 50 report.txt DNE.txt out.txt
DNE.txt (The system cannot find the file specified)

D:\Users\mythr\Documents\Spring 2019\CS 3345\Project5\src>java Main -10 report.txt unsorted.txt out.txt
Exception in thread "main" java.lang.IllegalArgumentException: Size is non-negative, this is invalid.
	at QuickSorter.generateRandomList(QuickSorter.java:291)
	at Main.main(Main.java:41)

-*- TEST RUNS -*- 

PROOF OF SORTING: 

Array Size = 7
FIRST_ELEMENT : PT0.000524337S
RANDOM_ELEMENT : PT0.000203566S
MEDIAN_OF_THREE_RANDOM_ELEMENTS : PT0.00007094S
MEDIAN_OF_THREE_ELEMENTS : PT0.000036498S

Unsorted.txt: 
-1614326271
-1133482498
2142418086
1786190067
-1452762955
-1934594704
-1170223453

Sorted.txt: 
-1934594704
-1614326271
-1452762955
-1170223453
-1133482498
1786190067
2142418086

--- Other Cases ---

Array Size = 150
FIRST_ELEMENT : PT0.002017154S
RANDOM_ELEMENT : PT0.001052272S
MEDIAN_OF_THREE_RANDOM_ELEMENTS : PT0.001460432S
MEDIAN_OF_THREE_ELEMENTS : PT0.000395309S

Array Size = 5000
FIRST_ELEMENT : PT0.010032308S
RANDOM_ELEMENT : PT0.0052007S
MEDIAN_OF_THREE_RANDOM_ELEMENTS : PT0.017129873S
MEDIAN_OF_THREE_ELEMENTS : PT0.007305757S

Array Size = 50000
FIRST_ELEMENT : PT0.047254558S
RANDOM_ELEMENT : PT0.065378614S
MEDIAN_OF_THREE_RANDOM_ELEMENTS : PT0.099242025S
MEDIAN_OF_THREE_ELEMENTS : PT0.102447676S

Array Size = 500000
FIRST_ELEMENT : PT0.679561922S
RANDOM_ELEMENT : PT0.407273886S
MEDIAN_OF_THREE_RANDOM_ELEMENTS : PT0.47255226S
MEDIAN_OF_THREE_ELEMENTS : PT0.303131883S

Array Size = 1000000
FIRST_ELEMENT : PT0.58096555S
RANDOM_ELEMENT : PT0.860511022S
MEDIAN_OF_THREE_RANDOM_ELEMENTS : PT0.784494028S
MEDIAN_OF_THREE_ELEMENTS : PT0.75211521S

General Summary: I found that there was still a lot of variation on times in the different ArrayList sizes, 
			when the ArrayList size is very small, there's almost no difference, but as the size 
			increases, it seems to be that either choosing the first element or a random element
			as the pivot results in the fastest time. In fact, even though choosing the first 
			element is usually not recommended, for such large arrays, my computer showed it to be
			the fastest time, possibly because it saves the runtime of having to partition the 
			ArrayList and find another pivot. 


Summary after running sorted and almost sorted arrays: 
		When running arrayLists with already sorted and nearly sorted data, I found that the pivot 
		as the first element quickly becomes a worst case scenario, but the other times were generally 
		similar. This shows that the pivot as the first element is only optimal if we know for sure
		that the list will be highly unsorted, in any other case, the other pivot methods are more
		stable choices. 
