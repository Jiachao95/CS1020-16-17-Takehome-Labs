
/**
 *	Name		:   Lin Jiachao
 *	Matric No.	:   A0155568R
 */

import java.util.*;

public class Passing {

    private static LinkedList<Player> listOfPlayers= new LinkedList<Player>();
    private static int holderIndex=0;


    public void run(){
        Scanner sc= new Scanner(System.in);

        int names= sc.nextInt();
        int lines= sc.nextInt();
        int limit= sc.nextInt();
        sc.nextLine();// consume trailing line

        for(int i=0; i<names; i++){
            listOfPlayers.add( new Player(sc.nextLine().trim()));
        }

        for(int i=0; i<lines; i++){
            followLine(sc, limit);
        }
        sc.nextLine();// consume trailing line

    }

    public static void main(String[] args) {
        Passing myPassing= new Passing();
        myPassing.run();

    }


    public void followLine(Scanner sc, int limit){
        int inputInt = sc.nextInt();

        //static holderindex is the index of person currently holding it
        int finalPos = (holderIndex + inputInt )% listOfPlayers.size() ;

        //prints the final person name and increment count
        System.out.println( listOfPlayers.get(finalPos).getName() );
        listOfPlayers.get(finalPos).incCount();//increment count

        Player tempInitial= listOfPlayers.get(holderIndex);
        Player tempFinal= listOfPlayers.get(finalPos);
        //different cases

        //if person hit limit, need to remove
        if(listOfPlayers.get(finalPos).getCount()==limit){
            listOfPlayers.remove(finalPos);

            if (finalPos==listOfPlayers.size())//if last guy gets eliminated
                holderIndex=0;
            else 
                holderIndex=finalPos;

        }else{ //else swap with the final posotion
            listOfPlayers.remove(holderIndex);
            listOfPlayers.add(holderIndex, tempFinal );

            listOfPlayers.remove(finalPos);
            listOfPlayers.add(finalPos, tempInitial );
        }
        /*
           for(Player player:listOfPlayers)
           System.out.print("   "+ player.getName()+ "   " );

           System.out.println();
         */
    }

}

class Player{
    ///////////////////////
    //attributes
    private String name;
    private int holdCount=0;

    public Player(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getCount(){
        return holdCount;
    }

    public void incCount(){
        holdCount++;
    }

}
