/*
 * author		: []
 * matric no.	: []
 */

import java.util.*;
import java.lang.*;

public class Palindrome {
	/* use this method to check whether the string is palindrome word or not
	 * 		PRE-Condition  :both have equal length and same case letters
	 * 		POST-Condition :
	 */
	public static boolean isPalindrome(String word) {
		return true;
	}
	
	public static void main(String[] args) {
		// declare the necessary variables
		char word1[] = new char[100];
		char word2[] = new char[100];
		int length;
		boolean state=true;
		
		// declare a Scanner object to read input
		Scanner sc= new Scanner(System.in);

		// read input and process them accordingly
		word1= sc.next().toCharArray();
		word2= sc.next().toCharArray();
		length= word1.length;
	
		//System.out.println(word1);
		// simulate the problem
		for(int i=0; i<length; i++){
			if (word1[i]!=word2[length-i-1]){
				state=false;
				System.out.println("NO");
				break;
			}
		}
		if (state)
			System.out.println("YES");

		// output the result

	}
}
