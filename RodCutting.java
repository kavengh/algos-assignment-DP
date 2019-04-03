/**
 * Rod cutting problem described in Chapter 15 of textbook.
 * Given a rod of length n inches and an array of prices that contains prices of all pieces of size smaller than n.
 * Determine the maximum value obtainable by cutting up the rod and selling the pieces.
 */
public class RodCutting {

  // Do not change the parameters!

	/**
	 * Recursively Check and add the maximum value of selling pieces of the rod.
	 * @param rodLength - The total length of the rod.
	 * @param lengthPrices - Array that stores the prices the pieces of the rod sell for.
	 * @return the maximum value of selling the pieces of the rod.
	 */
	public int rodCuttingRecur(int rodLength, int[] lengthPrices) {

		//base case if the length is 0
		if ( rodLength <= 0) {
			return rodLength;
		}

		//by default we set the totalPrice to the min value
		int endPrice = Integer.MIN_VALUE;

		//loop that goes through each possible length until the max and calculates
		//the maximum profit from selling the pieces of the rod
		for (int i = 0; i < rodLength; i++) {

			//we take the max between the previous max and the max of the the previous piece and the new piece
			endPrice = Math.max(endPrice, lengthPrices[i] + rodCuttingRecur(rodLength-i-1, lengthPrices));
		}

		//return total price of all pieces sold
		return endPrice;
   	}

  // Do not change the parameters!

	/**
	 * Dynamically Check and add the maximum value of selling pieces of the rod.
	 * @param rodLength - The total length of the rod.
	 * @param lengthPrices - Array that stores the prices the pieces of the rod sell for.
	 * @return the maximum value of selling the pieces of the rod.
	 */
	public int rodCuttingBottomUp(int rodLength, int[] lengthPrices) {

		//we create an array of the size of the rod length
        int totalPrice[] = new int[rodLength+1];

        //base case if no pieces then equal to 0
        totalPrice[0] = 0;


        //loop iterates through all possible rod lengths
        for (int i = 1; i<=rodLength; i++)
        {
        	//each time we are setting the end price to the min value
            int endPrice = Integer.MIN_VALUE;

            //we set the total price to the max between the previous total price and
            //the total price including the new piece
            for (int j = 0; j < i; j++) {

                endPrice = Math.max(endPrice, lengthPrices[j] + totalPrice[i-j-1]);

            }

            //before checking the next rod length we store this value that way we dont have to
            //compute the total of the previous peices again.
            totalPrice[i] = endPrice;
        }

        //we return the total price of all the pieces
        return totalPrice[rodLength];
	}


    /* Driver program to test above functions */
    public static void main(String args[]){
        RodCutting rc = new RodCutting();

        // In your turned in copy, do not touch the below lines of code.
        // Make sure below is your only output.
        int length1 = 7;
        int[] prices1 = {1, 4, 7, 3, 19, 5, 12};
        int length2 = 14;
        int[] prices2 = {2, 5, 1, 6, 11, 15, 17, 12, 13, 9, 10, 22, 18, 26};
        int maxSell1Recur = rc.rodCuttingRecur(length1, prices1);
        int maxSell1Bottom = rc.rodCuttingBottomUp(length1, prices1);
        int maxSell2Recur = rc.rodCuttingRecur(length2, prices2);
        int maxSell2Bottom = rc.rodCuttingBottomUp(length2, prices2);
        System.out.println(maxSell1Recur + " " + maxSell1Bottom);
        System.out.println(maxSell2Recur + " " + maxSell2Bottom);
    }
} 
