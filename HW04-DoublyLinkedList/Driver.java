import java.util.Scanner;
import java.util.Iterator;

/**
 * Driver for the program.
 * 
 * @author Jesse Chen
 */
public class Driver {
	
	private static Scanner keyboard = new Scanner(System.in);
	private static LinkedListInterface<Integer> list = new DoublyLinkedList<Integer>();
	
	/**
	 * Main method. Executes program.
	 * 
	 * @param args Not used
	 */
	public static void main(String[] args) {
		boolean exit = false;
        while (!exit) {
            String command = "";

            System.out.println("The list currently has " + list.size() + " elements.");
            System.out.print("Enter a command: ");
            command = keyboard.next();

            switch (command) {
            case "add":		add();
                    		break;
            case "remove":	remove();
                    		break;
            case "reverse":	reverse();
                    		break;
            case "list":	System.out.println(list());
                    		break;
            case "exit":	exit = true;
                    		break;
            default:		System.out.println("Invalid command");
            				break;
            }
        }
	}
	
	private static void add() {
		System.out.println("What number would you like to add? ");
		int newElem = keyboard.nextInt();
		
		System.out.println("Where would you like to add?");
		System.out.println("front");
		System.out.println("back");
		System.out.println("index");
		String command = keyboard.next();
		
		switch (command) {
		case "front":	list.add(0, newElem);
						break;
		case "back":	list.add(list.size(), newElem);
						break;
		case "index":	System.out.println("What index are you adding to?");
						int index = keyboard.nextInt();
						list.add(index, newElem);
						break;
		default:		System.out.println("Invalid command");
						break;
		}
	}
	
	private static void remove() {
		System.out.println("Where would you like to remove?");
		System.out.println("front");
		System.out.println("back");
		System.out.println("index");
		System.out.println("specificItem");
		String command = keyboard.next();
		
		switch (command) {
		case "front":			list.remove(0);
								break;
		case "back":			list.remove(list.size() - 1);
								break;
		case "index":			System.out.println("What index are you removing from?");
								int index = keyboard.nextInt();
								list.remove(index);
								break;
		case "specificItem":	System.out.println("Which item would you like to remove?");
								int elem = keyboard.nextInt();
								list.remove(Integer.valueOf(elem));
								break;
		default:				System.out.println("Invalid command");
								break;
		}
	}
	
	private static void reverse() {
		list.reverseList();
	}
	
	private static String list() {
		Iterator<Integer> it = list.iterator();
		String result = "";
		
		while (it.hasNext()) {
			result += it.next().toString() + " ";
		}
		
		return result;
	}
}