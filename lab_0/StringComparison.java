/**
 *
 * author	: []
 * matric no: []
 * 
 */

import java.util.*;
import java.lang.*;

public class StringComparison {
	
	public static void main(String[] args) {

		// declare the necessary variables
		String st1, st2;

		// declare a Scanner object to read input
		Scanner sc = new Scanner(System.in);

		// read input and process them accordingly
		st1= sc.next();
		st2= sc.next();
		
		int res= st1.compareToIgnoreCase(st2);
		//System.out.println(res);	
		if (res==0)
			System.out.println(0);
		else if(res<0)
			System.out.println(1);
		else if(res>0)
			System.out.println(2);
		
		sc.close();
	}
}
