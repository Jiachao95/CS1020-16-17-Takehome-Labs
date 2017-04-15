/**
 * Name         :   Lin Jiachao
 * Matric No    :   A0155568R
 * Plab Acct.   :   
 *
 */

import java.util.*;
import java.lang.*;
public class Cards {

    private LinkedList<Cards> cards = new LinkedList<Cards>();

    public void run() {
        // implement your "main" method here

        Scanner sc = new Scanner(System.in);



        //taking in the cards and adding them to a linked list
        int numCards = sc.nextInt();
        sc.nextLine();//consumes trailing line

        for (int i=0; i< numCards; i++){
            cards.addLast( new Cards(sc.next(), sc.nextInt()));
            sc.nextLine();//consume trailing line
        }

        //taking in the queries adn running them
        int numQueries = sc.nextInt();
        sc.nextLine();//consumes trailing line

        for (int i=0; i<numQueries; i++){
            switch( sc.next() ){
                case "swap":
                    swap(sc);
                    break;
                case "details":
                    details(sc);
                    break;
                case "position":
                    position(sc);
                    break;
                case "shuffle":
                    shuffle();
                    break;
                case "print":
                    print();
                    break;
                default:
                    break;
            }
        }
        sc.nextLine();//consume trailing line


    }

    public static void main(String[] args) {
        Cards myCards = new Cards();
        myCards.run();
    }
    ///////////////////////////////////////// attributes and constructor
    private String name;
    private int age;

    public Cards(){

    }

    public Cards(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }



    /////////////////////////////////////////queries

    public void swap(Scanner sc){
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();

        int deckSize= cards.size();
        //create 2 new linked lists and take in the 2 ranges
        LinkedList<Cards> ab= new LinkedList<Cards>();
        LinkedList<Cards> cd= new LinkedList<Cards>();

        for(int i= 0; i< b-a+1; i++){//adds a to b to the linked list
            ab.addLast(cards.get(a+i-1));

        }
        for(int i= 0; i< d-c+1; i++){//adds c to d to the linked list
            cd.addLast(cards.get(c+i-1));

        }

        //swop behind half
        for(int i= 0; i< d-c+1; i++){//remove cd
            cards.remove(c-1);
        }
        //print();
        for(int i= 0; i< b-a+1; i++){//adds ab
            cards.add( (c-1), ab.removeLast() );
        }
        //print();

        //swop front half
        if (a==1){
            for(int i= 0; i< b-a+1; i++){//remove ab
                cards.removeFirst();
            }
            //  print();
            for(int i= 0; i< d-c+1; i++){//add cd
                cards.addFirst( cd.removeLast() );
            }
            //  print();
        }else{
            for(int i= 0; i< b-a+1; i++){//remove ab
                cards.remove(a-1);
            }
            //  print();
            for(int i= 0; i< d-c+1; i++){//add cd
                cards.add( (a-1), cd.removeLast() );
            }
            //  print();
        }   

        //print();
        System.out.println("swap has been performed");
    }

    public void details(Scanner sc){
        int index = sc.nextInt();

        Cards card= cards.get(index-1);

        System.out.println(card.getName()+ " " + card.getAge());


    }

    //pre condition: card name must exist else index out of bounds exception
    public void position(Scanner sc){
        String name = sc.next();

        int i=0;
        while (cards.get(i).getName().equals(name)  == false){
            i++;
        }
        //exits while loop at the position of the correct element or at the end null
        System.out.println(i+1);

    }

    public void shuffle(){
        int deckSize= cards.size();

        LinkedList<Cards> deck1 = new LinkedList<Cards>();
        LinkedList<Cards> deck2 = new LinkedList<Cards>();

        for (int i=0; i< (Math.ceil(deckSize/2.0)); i++){
            deck1.addLast( cards.removeFirst() );
            //    print();
        }
        for (int i=0; i< deckSize/2; i++){
            deck2.addLast( cards.removeFirst());
        }

        for(int i=0; i<deckSize; i++){
            if (i%2 == 0)
                cards.addLast( deck1.removeFirst() );
            else
                cards.addLast( deck2.removeFirst() );

            //    print();
        }

        System.out.println("shuffle has been performed");
    }

    public void print(){
        Cards curr = cards.getFirst();
        System.out.print(curr.getName() );

        for(int i=1; i<cards.size(); i++ ){
            curr= cards.get(i);
            System.out.print( " "+ cards.get(i).getName() );
        }
        System.out.println();   

    }
}
