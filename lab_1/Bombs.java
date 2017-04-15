/*
 * Name		:Jiachao
 * Matric No.	:A0155568R
 */

import java.util.*;
import java.lang.*;

public class Bombs{

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int[][] map= new int[60][60];
		int row= sc.nextInt();
		int col= sc.nextInt();

		//scan in map
		for(int i=0; i<row; i++){
			for(int j=0; j<col; j++){
				map[i][j]=sc.nextInt();
			}
		}

		//scan in query to integer array
		int query= sc.nextInt();
		int[] bombArr= new int[5];
		for (int i=0; i<query; i++){
			bombArr[i]=sc.nextInt();
		}

		//start processing


		//no of queries for bombs					Part 1 
		for(int i=0; i<query; i++){
			countHit(map, bombArr[i], row, col);
		}

		//query2 
		countScore(map,row, col);

	}

	public static void countHit(int[][] map, int size,  int row, int col ){

		int best=0, x=0, y=0;
		//scroll though the whole map one by one
		for (int i= 0; i<row; i++){
			for(int j=0; j<col; j++){
				int hitX=0, hitY=0;

				//scan throught hit area
				int hit=0, posAdj= size/2; 
				for(int a=0; a<size; a++){
					for(int b=0; b<size; b++){
						if ( i-posAdj+a <0 || i-posAdj+a>row-1 || j-posAdj+b<0 || j-posAdj+b>col-1)
							continue;
						if ( map[i-posAdj+a][j-posAdj+b]==1)
							hit++;
					}
				}
				if (hit> best){
					best=hit;
					x=i;
					y=j;
				}
				//System.out.println( "hit x= "+ i +" hit y= "+ j + " hits= " + hit);
				//System.out.println("Best score= "+ best);
			}
		}
		System.out.println(x+ " " + y);
	}





	public static void countScore(int[][] map, int row, int col ){

		int bestScore=0, bestX=0, bestY=0, bestSize=0;
		for(int size=1; size<=51 ; size+=2){

			int best=0, x=0, y=0;//best is score
			//scroll though the whole map one by one
			for (int i= 0; i<row; i++){
				for(int j=0; j<col; j++){

					//scan throught hit area
					int score=0, posAdj= size/2; 
					for(int a=0; a<size; a++){
						for(int b=0; b<size; b++){
							if ( i-posAdj+a <0 || i-posAdj+a>row-1 || j-posAdj+b<0 || j-posAdj+b>col-1)
								continue;
							if ( map[i-posAdj+a][j-posAdj+b]==1)
								score+=3;
							else
								score--;
						}
					}
					if (score> best){
						best=score;
						x=i;
						y=j;
					}
					//System.out.println( "hit x= "+ i +" hit y= "+ j + " hits= " + hit);
					//System.out.println("Best score= "+ best);
				}
			}
			//System.out.println(x+ " " + y);

			if (best> bestScore){
				bestScore=best;
				bestX=x;
				bestY=y;
				bestSize=size;
			}

			//System.out.println("Best score= "+ bestScore + " x= " + bestX + " y= "+ bestY);
		}
		System.out.println(bestX+ " " + bestY + " " + bestSize);




	}
}
