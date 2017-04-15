/**
 * Name			:   Lin Jiachao
 * Matric No.	:   A0155567R
 */
import java.util.*;

public class Love {

    private HashMap<String, Integer> currWords = new HashMap< String, Integer>();
    String[] wordList;

    public void run() {
        // treat this as your "main" method
        Scanner sc = new Scanner(System.in);

        int numStrings = sc.nextInt();
        int distinctWords = 0;
        int minLength = 0;

        wordList = new String[numStrings];

        //finds the number of distinct words and length of all strings
        for(int i=0; i<numStrings; i++ ){
            wordList[i] = sc.next();

            if ( !currWords.containsKey(wordList[i]) ){
                currWords.put(wordList[i], 1);
                distinctWords++;
            }

            minLength += wordList[i].length() ;

        }

        currWords.clear();

        int currLength = 0;
        int startIndex = 0;
        int endIndex = 0;
        int currDistinct = 0;


        //adds strings till contains all words
        for( ; endIndex < numStrings; endIndex++ ){
            String currWord = wordList[endIndex];

            currLength += currWord.length();
            //if is distinct, add to hash
            if( !currWords.containsKey(currWord)){
                currDistinct++;
                currWords.put ( currWord, 1 );
            }else{
                currWords.replace( currWord, currWords.get( currWord ) + 1 );
            }

            //checks if all words are between the current indexes
            if (currDistinct == distinctWords){
                currWord =  wordList[startIndex];
                //checks if the front index is repeated
                while( currWords.get( currWord ) >1 ){
                    currWords.replace( currWord, currWords.get( currWord ) - 1 );
                    currLength -= currWord.length();
                    startIndex++;
                    currWord = wordList[startIndex];//prepares currWord for next while loop
                }

                minLength = Math.min( minLength, currLength);
            }

        }

        System.out.println( minLength);



    }

    public static void main(String[] args) {
        Love myLove = new Love();
        myLove.run();
    }

}
