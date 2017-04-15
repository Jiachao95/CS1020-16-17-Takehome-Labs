/**
 * Name          :  Jiachao
 * Matric number :  A0155568R
 */

import java.util.*;

public class Chemistry {
    
    private Stack<Integer> chemical= new Stack<Integer>();
    
    //Hashmap to store the masses of the elements
    private HashMap<Character, Integer> elements= new HashMap<Character, Integer>();
    
    //Use -1 to represent '(' so that it can be pushed into the Integer Stack
    private static final int L_BRACKET = -1;
    
    public void run(){
        //implement your main method here
        Scanner sc = new Scanner(System.in);
        
        //takes in the elements and their masses
        int numElements = sc.nextInt();
        while (numElements-- >0){
            //add the chemical
            elements.put(sc.next().charAt(0) ,sc.nextInt());//char at to convet to character
        }
        sc.nextLine();//consumes line feed

        //takes in the chemical's name as a string
        String exp = sc.nextLine().trim();

        for(char ch: exp.toCharArray()){

            if (Character.isLetter(ch)){
                //if is a character push it's value into the stack
                chemical.push( elements.get(ch) );
            }else if (Character.isDigit(ch)){
                //if number then return mass of the item before * the number
                chemical.push( chemical.pop()* Character.getNumericValue(ch));
            }else if (ch == '('){
                // use -1 to represent left Bracket
                chemical.push(L_BRACKET);
            }else if (ch == ')'){
                //use method to pop all until '(', evaluate and return
                chemical.push( evaluate(chemical));

            }//no more cases

        }

        //evaluate one more time because no outermost brackets
        int result = evaluate(chemical);
        System.out.println(result);
    }

    public static void main(String[] args) {
        Chemistry myChemistry= new Chemistry();
        myChemistry.run();
    }


    //evaluates from ( to ) in a stack and returns the mass as integer
    public int evaluate(Stack<Integer> stack){
        int result=0;
        
        while (!stack.isEmpty()){
            
            //if the character at the top is not ), then add to result, if it is then remove and break
            if(stack.peek() == L_BRACKET){// use -1 to represent left Bracket
                stack.pop();
                break;
            }

            result+= stack.pop();
        }

        return result;

    }


}

class Element{
    //attributes
    String elementName;
    int elementMass;

    //constructor
    public Element(String name, int mass){
        elementName=name;
        elementMass=mass;
    }

    public String getName(){
        return elementName;
    }

    public int getMass(){
        return elementMass;
    }

}
