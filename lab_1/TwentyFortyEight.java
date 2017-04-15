/*
 * Name		:Jiachao
 * Matric No.		:A0155568R
 */

import java.util.*;

public class TwentyFortyEight {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int[][] arr=new int[4][4];

		//scan in the 16 numbers
		for (int i=0; i<4; i++ ){
			for(int j=0; j<4; j++){
				arr[i][j]=sc.nextInt();
			}
		}


		//direction of mergeLeft
		int dir = sc.nextInt();

		//execution, rotation first then slide
		switch (dir){
			case 0: mergeLeft(arr);
					break;
			case 1: rotateClock(arr);
					rotateClock(arr);
					rotateClock(arr);
					mergeLeft(arr);
					rotateClock(arr);
					break;
			case 2:rotateClock(arr);
				   rotateClock(arr);
				   mergeLeft(arr);
				   rotateClock(arr);
				   rotateClock(arr);
				   break;
			case 3:rotateClock(arr);
				   mergeLeft(arr);
				   rotateClock(arr);
				   rotateClock(arr);
				   rotateClock(arr);
				   break;
		}
		//print out the final array
		for (int i=0; i<4; i++ ){
			for(int j=0; j<4; j++){
				if (j==3)
					System.out.print(arr[i][j]);
				else
					System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}

	}

	public  static void mergeLeft(int[][] arr){
		//stack left first
		for (int cyc=0; cyc<4; cyc++){
			for(int i=0; i<4; i++){
				for(int j=1; j<4; j++)
					if (arr[i][j-1]==0){
						arr[i][j-1]=arr[i][j];
						arr[i][j]=0;
					}
			}
		}
		//evaluate and merge
		for(int i=0; i<4; i++){
			for (int j=0; j<3; j++){
				if (arr[i][j]==arr[i][j+1]){
					arr[i][j]*=2;
					arr[i][j+1]=0;
					j++;
				}
			}
		}

		//stack again

		for (int cyc=0; cyc<4; cyc++){
			for(int i=0; i<4; i++){
				for(int j=1; j<4; j++)
					if (arr[i][j-1]==0){//if cell is empty, move left
						arr[i][j-1]=arr[i][j];
						arr[i][j]=0;
					}
			}
		}
	}

	public  static void rotateClock(int[][] arr){
		int temp=0;
		//rotate diagonally
		for (int i=0; i<4; i++ ){
			for(int j=i+1; j<4; j++){
				temp= arr[i][j];
				arr[i][j]= arr[j][i];
				arr[j][i]= temp;
			}
		}
		//rotate horizontally
		for (int i=0; i<4; i++ ){
			for(int j=0; j<2; j++){
				temp= arr[i][j];
				arr[i][j]= arr[i][3-j];
				arr[i][3-j]= temp;
			}
		}
	}

}
