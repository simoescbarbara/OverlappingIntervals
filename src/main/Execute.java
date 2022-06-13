package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Stack;

import model.Interval;

public class Execute {

	public static void main(String[] args) {
		
		File csvFile = new File("intervals.csv");
	    Scanner scanner;
	    ArrayList<Interval> arr= new ArrayList<Interval>();
        Stack<Interval> merge = new Stack<>();
		try {
			scanner = new Scanner(csvFile);
			//in order to read the file the algorithm complexity is a O(n) 
			//because we use one interaction
			 while(scanner.hasNext()) {
			        String[] line = scanner.nextLine().split(",");
			        Integer start = Integer.parseInt(line[0]);
			        Integer end = Integer.parseInt(line[1]);
			        Interval interval = new Interval(start, end);
			        arr.add(interval);
			     }
			 merge =	mergeIntervals(arr);
			//print the result by print the content of the Stack 
		    //to print the result we also have a computational consume of O(n)
		    System.out.print("The Merged Intervals are: ");
		    while (!merge.isEmpty()){
		        	Interval t = merge.pop();
		            System.out.print("["+t.getStart()+","+t.getEnd()+"] ");
		    } 
		} catch (FileNotFoundException e2) {
			System.out.println("File not found, please see your file path");
		}catch( NumberFormatException e1){
			System.out.println("The entry archive contains not compatible characters. Only numbers are allowed.");
		} catch (Exception e) {
			System.out.println("System not Available");
		}
	}
	
	//function that receive an ArrayList of Interval, merge the intervals 
		//and return a stack with the merge result
		 public static Stack<Interval>  mergeIntervals(ArrayList<Interval> arr) throws Exception
		    {
		        // Verify if there is a least one entry 
		        if (arr.isEmpty())
		            throw new Exception();
		   
		        // We are using a Stack to perform the merge operations because once the input Interval Array is sorted
		        //then the idea is the interval(i) don't overlap with interval(i-1) because the start of 
		        //interval(i+1) must be greater then or equal interval(i). So a Stack makes easy to perform this operations
		        Stack<Interval> stack = new Stack<>();
		   
		        //here the sort have a complexity of Big O O(n log n) and this is the
		        //biggest operational cost from this implementation
		        //we are sorting in an increasing order of start
		        Collections.sort(arr,new Comparator<Interval>(){
		            public int compare(Interval i1, Interval i2)
		            {
		                return i1.getStart() - i2.getStart();
		            }
		        });
		   
		        //begin the operation by pushing the first interval to stack
		        stack.push(arr.get(0));
		   
		        //Starting with the next interval and verify if a merge will be necessary
		        //Here we have a operational cost of O(n)
		        for (int i = 1 ; i < arr.size(); i++)
		        {
		            //see the interval at the top of the stack
		            Interval top = stack.peek();
		   
		            //if the current interval is not overlapping with stack top, then it dont
		            //need to be merged, so we are just going to push it into the stack
		            if (top.getEnd() < arr.get(i).getStart())
		                stack.push(arr.get(i));
		   
		            //if overlaps then we are going to update the end of top with the end of
		            //the current interval
		            else if (arr.get(i).getEnd() > top.getEnd())
		            {
		                top.setEnd(arr.get(i).getEnd());
		                stack.pop();
		                stack.push(top);
		            }
		        }
		        
		        //return the Merged intervals by returning the stack
		        return stack; 
		    }

}
