Common Programming Problems and their Solutions

1) Permute.C - Find permutations and combinations of a given string. The string
is passed as a command line parameter.

2) PriorityQ.C - Implementation of a simple priority queue using an array. Two
operations add and delete are implemented.

3) binaryTree.C - Implementation of a typical binary tree. Methods to add data
to the tree, delete the entire tree and traversal routines (inorder, postorder
and preorder) are implemented.

4) exp.C - Algorithms to calculate x^y. The main catch in this question is to
reduce the number of multiplications. Two routines one recursive and one
iterative are implemented that perform the calculation in O(logN)
multiplications.

5) intToStr.C - Code to convert an integer to a C-style string.

6) strToInt.C - Code to convert a C-style string to an integer. The trap in this
question is to detect and suitably handle integer overflow.

7) mergeSort.C - Code for the mergesort algorithm for an array. The running
time in O(NlogN).

8) quickSort.C - Code for a simple implementation of the quick sort algorithm.
The running time is O(NlogN). 

9) kthLargest.C - Algorithm and code to find the kth largest element in an
unsorted array. This algorithm uses a variation of quicksort. The running time
is O(logN).

10) removeChars.C - Given two strings s1 and s2, remove all the characters
from s1 that are present in s2.

11) stack.C - Implementation of a stack data structure using C++ template
list.

12) rotate.C - Rotate a given string n times in linear time and constant
space. "n" can be greater than the string length.

13) maxSubseqSum.C - Find maximum subsequence sum in a series in O(n) time.

14) gameOfLife.C - This implements the game of life function. Given an array
of game board, write a function to arrive at a new state of the board based on
the following conditions :
  a) Each cell has a value 1 or 0 namely, 1 being alive and 0 dead.
  b) Each cell's new state is determined as
     If a cell has 3 alive neighbors, that cell is alive
     If a cell has 2 alive neighbors, its state remains unchanged
     Otherwise it's state is dead.
  c) The new state should be independent of the manner in which the board
     matrix is traversed.

15) Anagrams.java - Given a set of words on the command line, determine if
these words are anagrams of each other. A word is an anagram of another word
if it has exactly the same characters as the other word, but possibly a
different order, e.g. the words stop, pots and tops are anagrams of each
other.

16) SuffixArray.java - Search a string (or text) using Suffix Array
Build a suffix array of the string and search for a specific substring using
binary search on the suffix array.

17) ExtractEmbedCode.java - Given a set of text files, each containing one URL
per line. Some URLs contain the query string "embedCode", retrieve the value
of embedCode and report unique values.

18) EditDistance.java - Given a set of strings, find all pairs of strings that
are less than edit distance 2 from each other.

19) MatrixTraversal.java - Traverse a 2D matrix in a spiral order.

20) MatrixAllPaths.java - Print all the paths in a 2D matrix from top-left to 
bottom-right when only two operations move_down and move_right are allowed.

21) NQueenProblem.java - Code to find all solutions of placing N queens on an NxN
chess board such that no queen can hit each other.

22) SmithWaterman.java - Class to study dynamic programming to partially 
match (align) 2 DNA strings using Smith-Waterman algorithm.

23) removeDups.C - Method to remove duplicate characters from a C string.

24) ConvertIntToBinaryStr.java - Method to accept an integer and print its
binary string representation.

25) Tree.java - Method to find lowest common ancestor of two nodes in a
binary tree where the binary tree maynot be a binary search tree.

26) URLSorter.java - Source code at https://gist.github.com/2313463
Given a large number of URLs, sort them based on their domain names.

For example, if the input list of URLs is

http://www.youtube.com/watch?v=9KR_OesLEWw&feature=g-vrec&context=G280bc82RVAAAAAAAAAA
http://www.yahoo.com
microsoft.com
http://www.google.com
github.com/drio
http://www.youtube.com/watch?v=Clb7kN3xdII&feature=g-vrec&context=G244f6c0RVAAAAAAAACw
www.microsoft.com/responsepoint
http://google.com/214/2143/12323/232323/23
http://www.youtube.com
dropbox.com
github.com/nirav99
nirav99.github.com
timesofindia.com
www.wikipedia.org
http://www.cnn.com
www.nytimes.com
www.utdallas.edu
www.bcm.edu
www.tamu.edu
www.utexas.edu
http://www.utdallas.edu/~bastani/

The output should be

www.bcm.edu
http://www.cnn.com
dropbox.com
github.com/drio
github.com/nirav99
http://www.google.com
http://google.com/214/2143/12323/232323/23
microsoft.com
www.microsoft.com/responsepoint
nirav99.github.com
www.nytimes.com
www.tamu.edu
timesofindia.com
www.utdallas.edu
http://www.utdallas.edu/~bastani/
www.utexas.edu
www.wikipedia.org
http://www.yahoo.com
http://www.youtube.com/watch?v=9KR_OesLEWw&feature=g-vrec&context=G280bc82RVAAAAAAAAAA
http://www.youtube.com/watch?v=Clb7kN3xdII&feature=g-vrec&context=G244f6c0RVAAAAAAAACw
http://www.youtube.com

(There's no need to sort the URLs from the same domain alphabetically).
