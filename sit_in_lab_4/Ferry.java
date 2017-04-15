/**
 * Name         : Lin Jiachao
 * Matric No.   : A0155568R
 * PLab Acct.   : plab8628
 */

import java.util.*;

public class Ferry {
	//
	//queue version
	private	Queue<Integer> leftSide  =new Queue<Integer>();
	private Queue<Integer> rightSide  =new Queue<Integer>();

	private int tripsMade;

	private void run() {
		// implement your "main" method here...
		Scanner sc = new Scanner(System.in);
		int weightCap = sc.nextInt(); 
		int numCars = sc.nextInt();

		//puts the cars into 2 queues
		while (numCars-- >0){
			int weight = sc.nextInt();
			String side = sc.next();
			if (side.equals("left"))
				leftSide.enqueue(weight);
			else	//right side
				rightSide.enqueue(weight);
		}
		evaluateNumTrips(weightCap);

		System.out.println( tripsMade);

	}

	// assigns the integers (car weights into 2 queues
	// precond: car's wieight must be withing the ferry's capacity and weight cannot be negative
	// postcond: returns number of trips needed to ferry all cars to the other side
	private void evaluateNumTrips(int weightCap){
		while (!leftSide.isEmpty() || !rightSide.isEmpty()){

			//loads cars on left side
			int spaceRemaining  = weightCap;
			while ( !leftSide.isEmpty() &&  leftSide.peek() <=  spaceRemaining){//while there's enough space, add the car and remove it from  queue
				spaceRemaining -= leftSide.peek();
				leftSide.dequeue();
			}
			tripsMade++;

			//checks if both sides are empty
			if (leftSide.isEmpty() && rightSide.isEmpty() )//prevents extra crossing
				break;

			//loads cars on right side
			spaceRemaining  = weightCap;//resets weight remaining
			while ( !rightSide.isEmpty() &&  rightSide.peek() <=  spaceRemaining){//while there's enough space, add the car and remove it from  queue
				spaceRemaining -= rightSide.peek();
				rightSide.dequeue();
			}
			tripsMade++;
		}

	}

	public static void main(String[] args) {
		Ferry trip = new Ferry();
		trip.run();
	}


	///////////////////////////////////////////////////////////////////////////////////////
	/*
	//stack version
	private	Stack leftSide  =new Stack();
	private Stack rightSide  =new Stack();

	private int tripsMade;

	private void run() {
	// implement your "main" method here...
	Scanner sc = new Scanner(System.in);
	int weightCap = sc.nextInt(); 
	int numCars = sc.nextInt();

	//puts the cars into 2 queues
	while (numCars-- >0){
	int weight = sc.nextInt();
	String side = sc.next();

	if (side.equals("left")){
	int leftRemaining = weightCap;
	if  (leftSide.isEmpty())
	leftSide.push(weight);
	else{
	while (!leftSide.isEmpty() && (leftSide.peek() + weight) <= leftRemaining ){//had some problem adding peek
	leftSide.push( leftSide.pop() + weight );
	leftRemaining -=weight;
	}//exits when all condensed into 1 integer represinting 1 ferry full of cars
	}

	}else{	//right side
	int rightRemaining = weightCap;
	if  (rightSide.isEmpty())
	rightSide.push(weight);
	else{
	while (!rightSide.isEmpty() && (rightSide.peek() + weight) <= rightRemaining ){//had some problem adding peek
	rightSide.push( rightSide.pop() + weight );
	rightRemaining -=weight;
	}//exits when all condensed into 1 integer represinting 1 ferry full of cars
	}
	}

	//now we have 2 stacks, each element represents a ferryfull of cars

	//counts num of trips
	while (!leftSide.isEmpty() || !rightSide.isEmpty()){

	//simulates ferry going to the right
	leftSide.pop();
	tripsMade++;

	//checks if both sides are empty
	if (leftSide.isEmpty() && rightSide.isEmpty() )//prevents extra crossing
	break;

	//simulates ferry going to the left
	rightSide.pop();
	tripsMade++;
	}
	System.out.println( tripsMade);

	}

	}

	public static void main(String[] args) {
	Ferry trip = new Ferry();
	trip.run();
	}

	 */



}

class Queue<E> {

	private LinkedList<E> elements;

	public Queue() {
		elements = new LinkedList<E>();
	}

	public void enqueue(E element) {
		elements.add(element);
	}

	/**
	 * Pre-Condition: The elements in the queue IS NOT EMPTY
	 * Post-Condition: Returns the head of the queue AND removes it from the queue
	 *
	 * This method throws an exception if the queue is empty when called
	 */
	public E dequeue() {
		return elements.removeFirst();
	}

	/**
	 * Pre-Condition: The elements in the queue IS NOT EMPTY
	 * Post-Condition: Returns the head of the queue but WITHOUT removing it
	 *
	 * This method throws an exception if the queue is empty when called
	 */
	public E peek() {
		return elements.getFirst();
	}

	public int size() {
		return elements.size();
	}

	public boolean isEmpty() {
		return elements.isEmpty();
	}
}

class Stack<E> {

	private LinkedList<E> elements;

	public Stack() {
		elements = new LinkedList<E>();
	}

	public void push(E element) {
		elements.addFirst(element);
	}

	/**
	 * Pre-Condition: The elements in the stack IS NOT EMPTY
	 * Post-Condition: Returns the top of the stack AND removes it from the stack
	 *
	 * This method throws an exception if the stack is empty when called
	 */
	public E pop() {
		return elements.removeFirst();
	}

	/**
	 * Pre-Condition: The elements in the stack IS NOT EMPTY
	 * Post-Condition: Returns the top of the stack but WITHOUT removing it
	 *
	 * This method throws an exception if the stack is empty when called
	 */
	public E peek() {
		return elements.getFirst();
	}

	public int size() {
		return elements.size();
	}

	public boolean isEmpty() {
		return elements.isEmpty();
	}
}
