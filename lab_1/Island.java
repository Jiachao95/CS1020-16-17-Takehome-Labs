/*
Name:Jiachao
Matric No.:A0155568R
 */

import java.util.*;

public class Island {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);

		int row= sc.nextInt();
		int col= sc.nextInt();

		int[][] map= new int[row+1][col+1];//+1 for sentinels

		//scan in the map of hyrule
		for(int i=1; i<row+1; i++){
			for(int j=1; j<col+1; j++){
				map[i][j]=sc.nextInt();
			}
		}

		int islandCount= countIslands(map, row, col);

		System.out.println(islandCount);
	}
	
	private static int countIslands(int[][] map, int row, int col){
		int finalCount=0;
		
		// scan theough each cell
		for(int i=1; i<row+1; i++){
			for(int j=1; j<col+1; j++){
			
			//look for the pattern above and left
			//    0
			// 0  1
			
			if (map[i][j]==1 && map[i-1][j]==0 && map[i][j-1]==0 )
				finalCount++;

			}
		}

		return finalCount;
	}



}
