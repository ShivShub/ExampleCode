public class Benford {
	public static void main(String[] args) {
		//total number of digits
		int trials = 100000;
		
		//counts the number of results that start with the given digit
		//index 0 = 1, index 1 = 2, etc
		int[] digits = new int[9];
		
		//stores the total relative percentage of results that start with the given digit
		double[] percentages = new double[9];
		
		//declare variables to minimize runtime
		double num1;
		double num2;
		int result;
		for (int i = 0; i < trials; i++) {
			//a random number from 1 to 10
			num1 = Math.random() * 9 + 1;
			
			//a random number from 0 to 10
			num2 = Math.random() * 10;
			
			//num1 to the power of num2
			result = (int)Math.pow(num1, num2);
			
			//add one to the array's index equal to the first digit of the result
			digits[firstDigit(result)-1]++;
		}
		
		//calculate and store relative percentages
		for (int i = 0; i < percentages.length; i++) {
			percentages[i] = (double)digits[i]/trials;
		}
		
		//prints out results in a formatted output
		for (int i = 0; i < digits.length; i++) {
			System.out.printf("%7d", i + 1);
		}
		System.out.println();
		
		for (int i = 0; i < digits.length; i++) {
			System.out.printf("%7d", digits[i]);
		}
		System.out.println();
		
		for (int i = 0; i < percentages.length; i++) {
			System.out.printf("%7.2f", percentages[i]);
		}
	}
	
	//a function that returns the leftmost digit of a number
	public static int firstDigit(int num) {
		while(num >= 10) {
			num /= 10;
		}
		return num;
	}
}
