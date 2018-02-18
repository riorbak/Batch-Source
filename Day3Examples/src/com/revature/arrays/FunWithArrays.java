package com.revature.arrays;

import java.util.Arrays;
import java.util.Random;

public class FunWithArrays {

	public static void main(String[] args) {		
		//int array with 7 slots
		int[] myArray = new int[7];
		//int array with hardcoded values
		int[] chaos = {6,6,6};

		Random rand = new Random();
		for(int i=0; i<myArray.length; i++) {
			myArray[i] = rand.nextInt(15);
		}
		
		for(int i : myArray) {
			System.out.println(i);
		}
		System.out.println(Arrays.toString(chaos));
	}

}
