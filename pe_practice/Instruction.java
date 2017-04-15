import java.util.*;

class Instruction {

    private static ArrayList<Query> queryList  = new ArrayList<Query>();
    private static int target;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numQueries = sc.nextInt();
        target = sc.nextInt();

        for (int i=0; i< numQueries; i++){
            queryList.add( new Query(sc.next(), sc.nextInt()));
        }

        System.out.println( findBestNum(0, 0) );        //start with 0 and 1st instruction

    }
    //Returns the closest and lowest number to the target
    private static int findBestNum(int currNum, int queryIndex){
        /*
           PSUEDOCODE:
           if reach the end
            return currNum
           if reach the  target
            return target

           calculate  new num for both

           return best num for the closer one

           if both are the same diff, return the lower number 
         
         */
        if( queryIndex >= queryList.size() )   //reach end of the instructions, out of bounds alr
            return currNum;

        if( diffBetween(currNum, target) == 0 )   //base case target number is achieved
            return target;


        int operateOnCurr=0;

        switch(queryList.get(queryIndex).getOp()){
            case "+":
                operateOnCurr = currNum + queryList.get(queryIndex).getNum();
                break;
            case "-":
                operateOnCurr = currNum - queryList.get(queryIndex).getNum();
                break;
            case "*":
                operateOnCurr = currNum * queryList.get(queryIndex).getNum();
                break;
            case "/":
                operateOnCurr = currNum / queryList.get(queryIndex).getNum();
                break;
            default:
                break;
        }

        int bestNum = 0;
        int numDo = findBestNum( operateOnCurr, queryIndex+1);  //best number if done
        int numDont = findBestNum( currNum, queryIndex+1);          //best number if not done
//        System.out.println(numDo + " " + numDont);

        if( diffBetween(numDo,target) < diffBetween(numDont,target) )   //if numDo is closer        
            bestNum = numDo;
        else if ( diffBetween( numDo, target ) == diffBetween( numDont, target) )//if same difference, return the lower one
            bestNum = Math.min(numDo, numDont);
        else        //numDont is closer
            bestNum = numDont;

        return bestNum;
    }

    private static int diffBetween(int num1, int num2){
        return Math.abs(num1-num2);
    }

}
class Query{
    private String op;
    private int num;

    public Query(String op, int num){
        this.op = op;
        this.num = num;
    }
    public String getOp(){
        return op;
    }
    public int getNum(){
        return num;
    }

}
