/**
 * Glass Falling:
 * You are the designer and inventor of a shock-proof landing mat that could catch the fall of glass from a certain height.
 * You want to guarantee that up to a certain number of floors, the glass will not break.
 * You need to calculate that highest floor X where the glass will not break, but you only have a set amount of glass panes.
  * Author: Kaven Vohra
 *
public class GlassFalling {

	/**
	 * Function that calculates the minimum number of trials it requires to determine
	 * if a sheet of glass will break when dropped
	 * @param floors - number of floors
	 * @param sheets - number of sheets of glass
	 * @return	the minimum number of trials
	 */
	public int glassFallingRecur(int floors, int sheets) {

		//base case, when the numbers of floors are equal to 0 or 1
		if(floors == 1 || floors == 0) {
			return floors;
		}

		//now that the base case for floors is made we need to make the base case for the number of sheets
		if(sheets == 1) {
			return floors;
		}

		//all base cases were made now we have to decide how to solve the problem

		//we set the minimum to the max value to start with
		int minimumTrials  = Integer.MAX_VALUE;
		int result;

		//loop through all of the floors starting from the 2nd floor
		for(int currentFloor = 2; currentFloor <= floors ; currentFloor++) {

			result = Math.max(glassFallingRecur(currentFloor - 1, sheets - 1), glassFallingRecur(floors - currentFloor, sheets));

			if(result < minimumTrials) {
				minimumTrials = result;
			}
		}
		return minimumTrials + 1;
	}


	//function that returns the max between two numbers
	public static int max(int num1, int num2) {
		if( num1 > num2) {
			return num1;
		}else {
			return num2;
		}

	}



	// Optional:
	// Pick whatever parameters you want to, just make sure to return an int.

	/**
	 * Function stores the values of previous trials allowing us to solve the problem much faster
	 * we trade space for time
	 * @param floors
	 * @param sheets
	 * @return The minimum number of trials needed to find what floor the sheet will break on.
	 */
	public int glassFallingMemoized(int floors, int sheets) {

		//create a 2D array that will hold the answers to each trial
		int[][] memo= new int [floors +1][sheets +1];

		int result;


		//the base case of when we have one floor or 0 floors still holds
		for(int k =1; k<=sheets; k++) {
			memo[0][k] = 1;
			memo[1][k] = 0;
			//doesn't matter how many sheets we have when the floor is one or 0 we fill the array with the number of the floors
		}

		//we also have the base case for when the number of sheets is equal to 1
		for(int f = 1; f<= floors; f++) {
			memo[f][1] = f;
			//since we only have one sheet we have to check all of the floors
		}

		//now that all of the base cases are done we can calculate all of the trials

		/*
		 * we need 3 for loops, the 1st and second loop will iterate through out 2D array that
		 * is holding all of the calculations so far for the floors and the sheets, and the third loop will do all of the
		 * calculations for the current iteration
		 */

		for(int currentFloor = 2; currentFloor <= floors; currentFloor++) {

			for(int currentNumSheet = 2; currentNumSheet <= sheets; currentNumSheet++) {

				//first we set the value of each position to the max
				memo[currentFloor][currentNumSheet] = Integer.MAX_VALUE;

				for( int subProblemFloor = 2; subProblemFloor <= currentFloor; subProblemFloor++ ) {
					//result = 1 + max(memo[numfloor - 1][ x - 1], memo[numfloor - x][sheets]);

					result = 1+  max(memo[subProblemFloor - 1][ currentNumSheet - 1], memo[currentFloor - subProblemFloor][ currentNumSheet]);


					if(result < memo[currentFloor][currentNumSheet]) {
						memo[currentFloor][currentNumSheet] = result;
					}
				}
			}

		}


		//This is the last slot of the table which is the minimum trials
		return memo[floors][sheets];
	}

	// Do not change the parameters!

	/**
	 * This function solves the minimum number of trials starting from the bottom. This is similar to the
	 * recursive solution however,
	 * @param floors
	 * @param sheets
	 * @return The minimum number of trials needed to find what floor the sheet will break on.
	 */
	public int glassFallingBottomUp(int floors, int sheets) {
		//base case, when the numbers of floors are equal to 0 or 1
		if(floors == 1 || floors == 0) {
			return floors;
		}

		//now that the base case for floors is made we need to make the base case for the number of sheets
		if(sheets == 1) {
			return floors;
		}

		//all base cases were made now we have to decide how to solve the problem
		int minimumTrails  = Integer.MAX_VALUE; //by default set the min to worst case
		int result;

		//loop through all of the floors starting from the 2nd floor
		for(int currentFloor = floors; currentFloor >= 2 ; currentFloor--) {

			result = Math.max(glassFallingRecur(currentFloor - 1, sheets - 1), glassFallingRecur(floors - currentFloor, sheets));

			if(result < minimumTrails) {

				minimumTrails = result;
			}

		}
		return minimumTrails + 1;
	}

	public static void main(String args[]){

		 GlassFalling gf = new GlassFalling();

	     // Do not touch the below lines of code, and make sure
	     // in your final turned-in copy, these are the only things printed
	     int minTrials1Recur = gf.glassFallingRecur(27, 2);
	     int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);
	     int minTrials2Memo = gf.glassFallingMemoized(100, 3);
	     int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);
	     System.out.println(minTrials1Recur + " " + minTrials1Bottom);
	     System.out.println(minTrials2Memo + " " + minTrials2Bottom);

	}

}
