/**
 * Name         : Jiachao
 * Matric No.   : A0155568R
 */
import java.util.*;
public class Storage {
	public void run() {
		Scanner sc = new Scanner(System.in);
		//scan input
		int num_box = sc.nextInt();
		int num_carry = sc.nextInt();
		int box_size = sc.nextInt();
		int queries_num = sc.nextInt();
		
		String[] queries = new String[queries_num];
		sc.nextLine(); //consume line input
		
		
		//scan in queries_num number of input
		for(int i = 0; i < queries_num; i++) queries[i] = sc.nextLine();
		Box[] boxArray = new Box[num_box + 1];//declare array of both hand and box together
		boxArray[0] = new Box(num_carry);
		for(int i = 1; i < num_box + 1; i++) boxArray[i] = new Box(box_size);
		for(int i = 0; i < queries_num; i++) {
			String[] queryParameters = queries[i].trim().split(" ");//returns an array of strings
			String queryName = queryParameters[0];

			switch(queryName) {
				case "purchase":
					purchase(queryParameters[1], boxArray, Integer.parseInt(queryParameters[2]));
				break;
				case "deposit":
					deposit(queryParameters[1], boxArray);
				break;
				case "withdraw":
					withdraw(queryParameters[1], boxArray);
				break;
				case "location":
					location(queryParameters[1], boxArray);
				break;
				case "valuable": valuable(boxArray);
			}
		}
	}
	public static void main(String[] args) {
		Storage myStorageSystem = new Storage();
		myStorageSystem.run();
	}
	
	private static Item findItem(String itemName, Box[] boxArray) {
		Item targetItem = null;
		for(int i = 0; i < boxArray.length; i++) {
			for(Item item : boxArray[i].getListOfItems()) {
				if(itemName.equals(item.getName())) {
					targetItem = item;
					break;
				}
			}
		}
		return targetItem;
	}
	public static void purchase(String itemName, Box[] boxArray, int value) {
		Item item = new Item(value, itemName, 0);
		if(boxArray[0].getListOfItems().size() >= boxArray[0].getSize()) {//if evan's hand is full
			for(int j = 1; j < boxArray.length; j++) {
				if(boxArray[j].getListOfItems().size() < boxArray[j].getSize()) {// if the box is not full
					boxArray[j].depositItem(item);
					item.setLocation(j);
					System.out.println("item " + itemName + " has been deposited to box " + j);
					break;
				}
			}
		} else {//evan is not full
			boxArray[0].depositItem(item);
			System.out.println("item " + itemName + " is now being held");
		}
//		System.out.println("Hand now holds "+ boxArray[0].getListOfItems().size());
	}
	private static void deposit(String itemName, Box[] boxArray) {
		Item item = findItem(itemName, boxArray);
		if(item == null) System.out.println("item " + itemName + " does not exist");
		else if(item.getLocation() != 0) System.out.println("item " + itemName + " is already in storage"); //item is not with evan means is in storage
		else {
			for(int j = 1; j < boxArray.length; j++) {
				if(boxArray[j].getListOfItems().size() < boxArray[j].getSize()) { //makes sure that box is not full before depositing
					boxArray[j].depositItem(item);
					boxArray[0].withdrawItem(item);
					item.setLocation(j);
					System.out.println("item " + itemName + " has been deposited to box " + j);
					break;
				}
			}
		}
	}
	private static void withdraw(String itemName, Box[] boxArray) {
		Item item = findItem(itemName, boxArray);
		if(item == null) System.out.println("item " + itemName + " does not exist");
		else if(item.getLocation() == 0) System.out.println("item " + itemName + " is already being held"); //item is with evan
		else { //means that item is in storage
			if(boxArray[0].getListOfItems().size() < boxArray[0].getSize()) {
				boxArray[0].depositItem(item);
				boxArray[item.getLocation()].withdrawItem(item);
				item.setLocation(0);
				System.out.println("item " + itemName + " has been withdrawn");
			} else System.out.println("cannot hold any more items");
		}
	}
	private static void location(String itemName, Box[] boxArray) {
		Item item = findItem(itemName, boxArray);
		if(item == null) System.out.println("item " + itemName + " does not exist");
		else {
			if(item.getLocation() == 0) System.out.println("item " + itemName + " is being held");
			else System.out.println("item " + itemName + " is in box " + item.getLocation());
		}
	}
	public static void valuable(Box[] boxArray) {
		int most_value = 0;
		String valuable_item = "";
		for(int j = 0; j < boxArray.length; j++) {
			for(Item i : boxArray[j].getListOfItems()) {
				if(i.getValue() >= most_value) {
					if(i.getValue() == most_value && i.getName().compareTo(valuable_item) > 0) continue;
					most_value = i.getValue();
					valuable_item = i.getName();
				}
			}
		}
		System.out.println(valuable_item);
	}
}
class Box {
	private int _size;
	private List<Item> listOfItems = new ArrayList<Item>();

	public Box(int size) { this._size = size; }
	public void depositItem(Item item) { listOfItems.add(item); }
	public void withdrawItem(Item item) { listOfItems.remove(item); }
	public List<Item> getListOfItems() { return listOfItems; }
	public int getSize() { return _size; }
}
class Item {
	private int _value;
	private String _name;
	private int _location;

	public Item(int value, String name, int location) {
		this._value = value;
		this._name = name;
		this._location = location;
	}
	public void setLocation(int location) { this._location = location; }
	public int getValue() { return _value; }
	public String getName() { return _name; }
	public int getLocation() { return _location; }
}
