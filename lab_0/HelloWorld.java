/**
 *
 * author	: []
 * matric no: []
 * 
 */

import java.util.*;

public class HelloWorld {
	
	public static void main(String[] args) {

		// declare the necessary variables
		int choice, nLines, num1, num2;
		String operator;

		// declare a Scanner object to read input
		Scanner sc=new Scanner(System.in);

		// read input and process them accordingly
		choice= sc.nextInt();
		
		if (choice==1){		//take in nLines and loop that many times
			nLines= sc.nextInt();
 
			for(int i=0; i<nLines; i++){
				operator= sc.next();
				num1=sc.nextInt();
				num2=sc.nextInt();
				
				int res=0;
				if (operator.equals("AND")){
					if(num1==1 &&num2==1)
						res=1;
					else 
						res=0;
				}else if (operator.equals("OR")){
					if(num1==1 ||num2==1)
						res=1;
					else res=0;
				}

				System.out.println(res);
			}
			
		}else if (choice==2){		// loop til user inputs 0 /-1
			
			do{
				operator= sc.next();
				//checks for exit condition
				if(operator.equals("0") ||operator.equals("-1"))
					break;

				num1=sc.nextInt();
				num2=sc.nextInt();
				
				int res=0;
				
				//computes truth value
				if (operator.equals("AND")){
					if(num1==1 &&num2==1)
						res=1;
					else 
						res=0;
				}else if (operator.equals("OR")){
					if(num1==1 ||num2==1)
						res=1;
					else res=0;
				}

				System.out.println(res);
			}while(true);//just to keep loop going

		}else if (choice==3){		//read all data in file
			
			do{
				String op=sc.next();

				//checks for exit condition
				num1=sc.nextInt();
				num2=sc.nextInt();
				
				int res=0;
				
				//computes truth value
				if (op.equals("AND")){
					if(num1==1 &&num2==1)
						res=1;
					else 
						res=0;
				}else if (op.equals("OR")){
					if(num1==1 ||num2==1)
						res=1;
					else res=0;
				}

				System.out.println(res);
			}while(sc.hasNext());//just to keep loop going
		}
	sc.close();

	}
}
