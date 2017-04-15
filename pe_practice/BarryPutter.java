/**
 * Name 		:   Lin Jiachao
 * Matric No. 	:   A0155568R
 * PLab Acct. 	:
 */

import java.util.*;
public class BarryPutter {

    private LinkedList<Chamber> bogwarts = new LinkedList<Chamber>();

    private void run() {
        // treat this as your "main" method
        Scanner sc = new Scanner(System.in);

        int numChamber = sc.nextInt(); 
        int numApps = sc.nextInt();

        for(int i=0; i<numChamber; i++){//populate bogwarts with chambers

            sc.nextInt(); //chamberNum is useless
            int amountGold = sc.nextInt();
            int numPassages = sc.nextInt();

            Chamber chamber = new Chamber(amountGold, numPassages);

            for(int j=0; j<numPassages; j++){
                int distance = sc.nextInt();
                chamber.addPath( distance);//adds index of next place
                //System.out.println("gold here = " + chamber.getGold()); //DEBUGGING checks path taken
                //System.out.println("Next stop = " +chamber.getPath(j)); //DEBUGGING checks path taken
            }

            bogwarts.add(chamber);

        }


        System.out.println( evalBestPath(numApps, 0, 0) );//can only enter from 0, with no gold
    }

    public static void main(String[] args) {
        BarryPutter barry = new BarryPutter();
        barry.run();
    }

    //returns amount of gold if he follows the path
    private int evalBestPath(int numApp, int index, int currGold){
        if ( index<0 || index>= bogwarts.size())    //if chamber doesnt exist, return the previous amoutn
            return currGold;

        Chamber chamber = bogwarts.get(index);//gets the chamber

        currGold += chamber.getGold();  //increment the gold in the chamber

        if ( chamber.getNumPassages() == 0 || numApp == 0)  //if run out of apparitions || reach a deadend
            return currGold;
        
        else{   //if passages wxist
            int highGold=0; //used to track hoghest amount 
            for(int i=0; i< chamber.getNumPassages(); i++){ //loop through all passages
                highGold = Math.max( evalBestPath(numApp-1, chamber.getPath(i)+index, currGold), highGold);

            }
            return highGold;

        }

    }






}

class Chamber{

    private int amountGold;
    private int numPassages;
    private ArrayList<Integer> paths = new ArrayList<Integer>(); 

    public Chamber(int amountGold, int numPassages){
        this.amountGold = amountGold;
        this.numPassages = numPassages;
    }

    public int getGold(){
        return amountGold;
    }

    public int getNumPassages(){
        return numPassages;
    }

    public void addPath(int index){
        paths.add(index);
    }

    public int getPath(int index){
        return paths.get(index);
    }
}

