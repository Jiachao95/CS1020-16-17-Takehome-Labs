/**
 *	Name	  : Lin Jiachao
 *	Matric no.: A0155568R
 */

import java.util.*;

public class DNA {

    private HashMap< String, Integer > subSequences = new HashMap<String, Integer>();

    public void run() {
        Scanner sc = new Scanner(System.in);

        // read input and process them accordingly
        int totalLength = sc.nextInt();
        int subLength = sc.nextInt();

        String dna = sc.next();


        int numSubs = sc.nextInt();
        String[] arr = new String[numSubs];//used to maintain sequence to print in

        //takes in the sequences to check against
        for(int i=0; i<numSubs; i++){
            String subs = sc.next();

            subSequences.put( subs, 0 );//adds into hashmap
            arr[i]= subs;//stores sequence of sub strings
        }

        //loops through all sublength possibilities
        for(int i=0; i<= totalLength - subLength; i++){
            String subs = dna.substring(i, i+subLength );

            //if match, increment count by 1
            if ( subSequences.containsKey(subs) )
                subSequences.put(subs, subSequences.get(subs) + 1 );

        }

        //prints out occurences in sequence
        for(int i=0; i<numSubs; i++){
            System.out.println( subSequences.get(arr[i]) );
        }

    }


    public static void main(String[] args) {
        DNA dna = new DNA();
        dna.run();
    }
}
