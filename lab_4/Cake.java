/**
 * Name          :  Jiachao
 * Matric number :  A0155568R
 */

import java.util.*;

public class Cake {

    private Queue<Slice> subCake= new LinkedList<Slice>();
    
    private int maxLength=0;//greatest amount of cake that can be eaten
    private int currChoc=0; //choc in current subcake
    private int currRasin=0;//rasins in current subcake

    public void run(){
	    //implement your main method here
        Scanner sc = new Scanner(System.in); 
        
        int cakeLength = sc.nextInt();

        int maxRasin = sc.nextInt();

        for( int i=0; i<cakeLength; i++){

           //add cake
            addCake(sc);

           //check cake overflow
            while (currRasin > maxRasin)
                removeCake(sc);
            
            //update values
            if (currChoc>maxLength)
                maxLength = currChoc;
        }

        System.out.println(maxLength);
    }

	public static void main(String[] args) {
	    Cake myCake = new Cake();
        myCake.run();
    }

    public void addCake(Scanner sc){
        int rasin;//use integers for easy counting of rasins 

        if (sc.next().charAt(0)== 'R')
            rasin = 1;//represents has rasin
        else 
            rasin = 0;//represents no rasin

        int choc = sc.nextInt();
        
        subCake.offer(new Slice( rasin, choc ) );
        
        //updating values
        currChoc+=choc;
        currRasin+=rasin;
    }

    public void removeCake(Scanner sc){
        int rasin= subCake.peek().getRasin();
        int choc= subCake.peek().getChoc();
        
        subCake.poll();
        
        //updating values
        currRasin-=rasin;
        currChoc-= choc;
    }
}

class Slice{
    
    private int rasin;
    private int numChoc;

    public Slice(int  rasin, int choc){
        this.rasin = rasin;
        numChoc= choc;
    }

    public int getRasin(){
        return rasin;
    }

    public int getChoc(){
        return numChoc;
    }

}
