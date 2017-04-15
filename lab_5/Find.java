/**
 *	name	  : Lin Jiachao
 *	matric no.: A0155568R
 */

import java.util.*;


class Find {

    private static HashMap< String, Integer > subSequences1 = new HashMap<String, Integer>();
    private static HashMap< String, Integer > subSequences2 = new HashMap<String, Integer>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int totalLength = sc.nextInt();
        int subLength = sc.nextInt();

        String dna1 = sc.next();
        String dna2 = sc.next();


        int numSubs = sc.nextInt();
        String[] arr = new String[numSubs];//used to maintain sequence to print in

        //takes in the sequences to check against
        for(int i=0; i<numSubs; i++){
            String subs = sc.next();

            subSequences1.put( subs, 0 );//adds into hashmap1
            subSequences2.put( subs, 0 );//adds into hashmap2
            arr[i]= subs;//stores sequence of sub strings
        }

        //loops through all sublength possibilities
        for(int i=0; i<= totalLength - subLength; i++){
            String subs1 = dna1.substring(i, i+subLength );
            String subs2 = dna2.substring(i, i+subLength );

            //if sub1 match, change to 1
            if ( subSequences1.containsKey(subs1) )
                subSequences1.put(subs1, 1 );

            //if sub2 match, change to 1
            if ( subSequences2.containsKey(subs2) )
                subSequences2.put(subs2, 1 );
        }

        //prints out in sequence
        for(int i=0; i<numSubs; i++){
            String sub = arr[i];

            int match1 = subSequences1.get(sub); 
            int match2 = subSequences2.get(sub); 

            if( match1 == 1 && match2 == 1 )
                System.out.println( 3 );
            
            else if( match1 == 0 && match2 == 1 )
                System.out.println( 2 );
            
            else if( match1 == 1 && match2 == 0  )
                System.out.println( 1 );
            
            else 
                System.out.println( 0 );
        }

    }
}
