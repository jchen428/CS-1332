import java.util.List;
import java.util.Scanner;

/**
 * Driver for the program.
 * 
 * @author Jesse Chen
 */
public class Driver {
	
	private static Scanner keyboard = new Scanner(System.in);
	private static BSTInterface<User> bst = new BST<User>();
	
	/**
	 * Main method. Executes program.
	 * 
	 * @param args Not used
	 */
	public static void main(String[] args) {	
		boolean exit = false;
        while (!exit) {
        	System.out.print("Please enter a command: ");
            String command = keyboard.next();

            switch (command) {
            case "add":		add();
                    		break;
            case "remove":	remove();
                    		break;
            case "find":	find();
                    		break;
            case "list":	list();
                    		break;
            case "debug":	debug();
    						break;
            case "exit":	exit = true;
                    		break;
            default:		System.out.println("Invalid command");
            				break;
            }
        }
	}
	
	/**
	 * Shows the interface to add a new User.
	 */
	private static void add() {
		System.out.print("Please enter username: ");
		keyboard.nextLine();
		String username = keyboard.nextLine();
		
		System.out.print("Please enter name: ");
		String name = keyboard.nextLine();
		
		if (!username.trim().isEmpty() && !name.trim().isEmpty()) {
			User newUser = new User(username, name);
		
			if (!bst.contains(newUser)) {
				bst.add(newUser);
			}
		}
	}
	
	/**
	 * Shows the interface to remove an existing User.
	 */
	private static void remove() {
		System.out.print("Please enter username you would like to remove: ");
		keyboard.nextLine();
		String toRemove = keyboard.nextLine();
		User removeUser = new User(toRemove, null);
		
		if (bst.contains(removeUser)) {
			bst.remove(removeUser);
			System.out.println("Remove was successful!");
		} else {
			System.out.println("User does not exist!");
		}
	}
	
	/**
	 * Shows the interface to find an existing User.
	 */
	private static void find() {
		System.out.print("Please enter username you would like to find: ");
		keyboard.nextLine();
		String toFind = keyboard.nextLine();
		User findUser = new User(toFind, null);
		
		if (bst.contains(findUser)) {
			System.out.println(bst.get(findUser));
		} else {
			System.out.println("User does not exist!");
		}
	}
	
	/**
	 * Prints a list of existing Users in order.
	 */
	private static void list() {
		System.out.println("List of current users: ");
		List<User> users = bst.inOrder();
		System.out.println(users);
	}
	
	/**
	 * Prints a String representation of the binary search tree.
	 */
	private static void debug() {
		System.out.println("String representation of tree: \n"
				+ bst.toString());
	}
}
