/**
 * Name			:   Jiachao
 * Matric No.	:   A0155568R
 * PLab Acct.	:
 */

import java.util.*;

public class Radiation {

    private Stack<Element> stack = new Stack<Element>();

    public void run() {
        // implement your "main" method here...
        Scanner sc = new Scanner(System.in);

        int numElements = sc.nextInt();

        int poppedMax=0;
        int overallMax=0;

        for (int i=0; i< numElements; i++){
            Element element= new Element( sc.nextInt(), 0);
            poppedMax = 0; //resets popped max to 0 every iteration

            int years = 0;

            if(stack.isEmpty()){
                years = 0; 

            }else if(element.getStrength() > stack.peek().getStrength() ){//next element is bigger
                years = 1; //because if next element is bigger, it will decay in the 1st year, thus set to 1

            }else{//next element is equal or smaller

                while (!stack.isEmpty() && stack.peek().getStrength() >= element.getStrength() ){
                    //updates popped max and the overall max then pop the element
                    poppedMax= Math.max(poppedMax, stack.peek().getYearsBeforeDecay() );
                    overallMax = Math.max(poppedMax, overallMax);
                    stack.pop();

                }//exits when it reaches element smaller than itself or when stack is empty

                if (stack.isEmpty()){
                    poppedMax=0; 
                }else{//stack is not empty
                    years = poppedMax+1;
                }

            }
            element.setYearsBeforeDecay(years);
            overallMax = Math.max(years,overallMax);// checks the current years before decay and updates overall Max
            stack.push(element);
        }

        System.out.println(overallMax);
    }
    
    public static void main(String[] args) {
        Radiation myChemicalElements = new Radiation();
        myChemicalElements.run();
    }
}

// hint for O(N) solution...
class Element {
    private int strength;
    private int yearsBeforeDecay;

    public Element(int strength, int yearsBeforeDecay) {
        this.strength = strength;
        this.yearsBeforeDecay = yearsBeforeDecay;
    }

    public int getStrength() {
        return this.strength;
    }

    public int getYearsBeforeDecay() {
        return this.yearsBeforeDecay;
    }

    public void setYearsBeforeDecay(int number) {
        yearsBeforeDecay= number;
    }
}
