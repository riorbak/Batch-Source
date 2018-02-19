package com.revature;

/*Q13. Display the triangle on the console as follows using any type of loop.  
 * Do NOT use a simple group of print statements to accomplish this.
 *  0
 *  1 0
 *  1 0 1
 *  0 1 0 1  */
public class Q13 {
	
	int col = 1;					//columns
	int row = 4;					//rows
	
	Boolean state = false;	 		//used for output
	
	public void drawTriangle()
	{
		for (int i = row; i > 0; i--)						//for each row,
		{
			for(int j = 0; j < col; j++)					// and for each column,
			{
				System.out.print(boolToInt(state) + " ");		//output
				state = !state; 								//flip the output
			}
			System.out.println();								//next row
			col++;												//add a column
		}
	}
	
	//Convert boolean to int for output
	public int boolToInt(boolean b)
	{
		return b ? 1 : 0;
	}
	
}
