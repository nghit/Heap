/***************************************************************
* file: Heap_Driver.java
* author: N.Tran
* class: CS 241 – Data Structure and Algorithms 2
*
* assignment: program 2
* date last modified: 10/30/2017
*
* purpose: heap’s driver
****************************************************************/ 
import java.util.*;

public class Heap_Driver{
   public static void main(String[] args) {
       Scanner input = new Scanner(System.in);
       Random rand = new Random();
       System.out.print("Please select how to test the program:\n(1) 20 sets of 100 randomly generated integers\n(2) Fixed integer values 1-100\nEnter choice: ");
       int choice = input.nextInt();
       switch (choice) {
           case 1: Heap heaps[] = new Heap[20];
               int totSwaps = 0;

               //series of insertions
               for (int i = 0; i < 20; i ++) {
                   heaps[i] = new Heap(100);
                   for (int j = 0; j < 100;) {
                       if (heaps[i].add(rand.nextInt(2147483647) + 1)) //if it was added, meaning it wasn't a duplicate, increase the count
                           j++;
                   }
                   totSwaps += heaps[i].getSwaps();
               }
               double avgSwaps = totSwaps / 20.0;
               System.out.print("\nAverage swaps for series of insertions: ");
               System.out.println(avgSwaps);

               //optimal method
               totSwaps = 0;
               for (int i = 0; i < 20; i ++) {
                   heaps[i] = new Heap(100);
                   for (int j = 0; j < 100;) {
                       if (heaps[i].addArbitrarily(rand.nextInt(2147483647) + 1)) //checks for duplicates
                           j++; //only increment if it was added, meaning no duplicates
                   }
                   heaps[i].optimize();
                   totSwaps += heaps[i].getSwaps();
               }
               avgSwaps = totSwaps / 20.0;
               System.out.print("Average swaps for optimal method: ");
               System.out.println(avgSwaps);
               break;
                  
           case 2: //series of insertions
               Heap heap = new Heap(100);
               for (int i = 1; i <= 100; i++) {
                   heap.add(i);
               }
               System.out.print("\nHeap built using series of insertions: ");
               heap.print(10);
               System.out.println();
               System.out.print("Number of swaps: ");
               System.out.println(heap.getSwaps());
               for (int i = 0; i < 10; i++) {
                   heap.remove();
               }
               System.out.print("Heap after 10 removals: ");
               heap.print(10);
               System.out.println();

               //optimal method
               heap = new Heap(100);
               for (int i = 1; i <= 100; i++) {
                   heap.addArbitrarily(i);
               }
               heap.optimize();
               System.out.print("\nHeap built using optimal method: ");
               heap.print(10);
               System.out.println();
               System.out.print("Number of swaps: ");
               System.out.println(heap.getSwaps());
               for (int i = 0; i < 10; i++) {
                   heap.remove();
               }
               System.out.print("Heap after 10 removals: ");
               heap.print(10);
               System.out.println();
               break;
           default: System.out.print("Invalid selection.");
       }
   }
}
