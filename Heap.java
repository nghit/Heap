/***************************************************************
* file: Heap.java
* author: N.Tran
* class: CS 241 – Data Structure and Algorithms 2
*
* assignment: program 2
* date last modified: 10/30/2017
*
* purpose: a max-heap program that allows the user to test the program 
* with 100 randomly generated integers or with 100 fixed values from 1-100.
****************************************************************/ 
//A heap implemented using an array
import java.lang.Math;

public class Heap {
   private final int array[];
   private int end;
   private int swaps;

   //size must be provided to produce the array
   public Heap(int size) {
       array = new int[size];
       end = 0; //last element + 1
       swaps = 0;
   }

   //adds an item directly into the next slot, checking for duplicates, optimize must be called when complete
   public boolean addArbitrarily(int num) {
       if (duplicate(num) || end > array.length - 1)
           return false;
       else {
           array[end] = num;
           end++;
           return true;
       }
   }

   //reheaps the tree after a series of arbitrary adds
   public void optimize() {
       //find the bottom rung
       int j = end - 1;
       int level;
       level = (int) Math.ceil(Math.log((double) (j + 1)) / Math.log(2.0)); //the the level of j
       int i = j - 1;
       int newLevel;
       newLevel = (int) Math.ceil(Math.log((double) (i + 1)) / Math.log(2.0)); // the level of the previous node
       while (newLevel == level) { //find the left most node on this level
               i--;
               newLevel = (int) Math.ceil(Math.log((double) (i + 1)) / Math.log(2.0));
       } //now i to j is the last level, i - 1 is the end of the next level up
       while (i != 0) {
           for (int k = i; k <= j; k += 2) {
               if (k + 1 > j) { //only one child
                       if (array[k] > array[(k - 1) / 2]) {
                               //swap
                               int temp = array[k];
                               array[k] = array[(k - 1) / 2];
                               array[(k - 1) / 2] = temp;
                               swaps++;
                               shiftDown(k);
                       }
               }
               else { //two children
                   if (array[k] > array[k + 1] && array[k] > array[(k - 1) / 2]) {
                       //swap
                       int temp = array[k];
                       array[k] = array[(k - 1) / 2];
                       array[(k - 1) / 2] = temp;
                       swaps++;
                       shiftDown(k);
                   }
                   else if (array[k + 1] > array[k] && array[k + 1] > array[(k - 1) / 2]) {
                       //swap
                       int temp = array[k + 1];
                       array[k + 1] = array[(k - 1) / 2];
                       array[(k - 1) / 2] = temp;
                       swaps++;
                       shiftDown(k + 1);
                   }
               }
           }
           //move to the next level
           j = i - 1;
           i = (i - 1) / 2;
       }
   }
  
   //helper function for optomize to move down swapped nodes
   private void shiftDown(int m) {
           //have to do bounds checking before accessing the array
           while (m != end - 1 && ( (2 * m + 1 < end && array[m] < array[2 * m + 1]) || (2 * m + 2 < end && array[m] < array[2 * m + 2]) ) ) {
               if (2 * m + 2 >= end || array[2 * m + 1] > array[2 * m + 2]) {
                   //swap
                   int temp = array[2 * m + 1];
                   array[2 * m + 1] = array[m];
                   array[m] = temp;
                   swaps++;
                   m = 2 * m + 1;
               }
               else {
                   //swap
                   int temp = array[2 * m + 2];
                   array[2 * m + 2] = array[m];
                   array[m] = temp;
                   swaps++;
                   m = 2 * m + 2;
               }
           }
   }

   //add with swapping
   public boolean add(int num) {
       if (duplicate(num) || end > array.length - 1)
           return false;
       int i = end;
       array[i] = num;
       int parent = (i - 1) / 2;
       while (i != 0 && num > array[parent]) {
           array[i] = array[parent]; //swap
           array[parent] = num;
           swaps++;
           i = parent;
           parent = (i - 1) / 2;
       }
       end++;
       return true;
   }

   //efficient check for duplicates
   public boolean duplicate(int num) {
       for (int i = 0; i < end; i++) {
           if (num > array[i])
               return false;
           else if (num == array[i])
               return true;
       }
       return false;
   }

   //remove the root node and shift the new one down
   public void remove() {
       array[0] = array[end - 1];
       int i = 0;
       shiftDown(0);
       end--;
   }

   //returns the number of swaps made by this heap
   public int getSwaps() {
       return swaps;
   }

   //prints the first n values of the array
   public void print(int n) {
       int i;
       for (i = 0; i < n && i < end; i++) {
           System.out.print(array[i]);
           if (i != end- 1)
               System.out.print(',');
       }
       if (i != end)
               System.out.print("...");
   }
}

