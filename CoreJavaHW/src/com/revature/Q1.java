package com.revature;

import java.util.Arrays;

public class Q1 {	
	public void run() {		
		int[] givenArray = {1,0,5,6,3,2,3,7,9,8,4};
		System.out.println("Pre-sorted:\t"+Arrays.toString(givenArray));
		
		int[] sortedArray = bubbleSort(givenArray);
		System.out.println("Sorted:\t\t"+Arrays.toString(sortedArray));
	}
	
	public int[] bubbleSort(int[] arr) {
		boolean numsWereSwapped = true;	//this will be used to keep track of if there was a swap in each passing of the array
		while(numsWereSwapped == true) {	//this while represents every passing through the whole array
										//check if there was a swap to see if it was already sorted
			numsWereSwapped = false;	//reset swapped when going through the array another time
			for(int i=0; i < arr.length-1; i++) {	//for each slot in the array
				if(arr[i] > arr[i+1]) {	//if current slot's value is bigger than the next one
					numsWereSwapped = true;	//set indicator true
					int temp = arr[i+1];	//store the next value
					arr[i+1] = arr[i];		//make the next value into the current one
					arr[i] = temp;			//make the current value into the stored next slot value
				}
			}
		}
		return arr;	//return the sorted array
	}
}
