import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is where you will put your main method. For
 * instructions on how to create it, refer to the PDF for
 * this homework assignment.
 * 
 * @author Jesse Chen
 *
 */
public class AmusementPark {
	
	//Leave this variable alone. It is necessary for compilation.
	public static int ticketNumber = 1000;
	private static Scanner keyboard = new Scanner(System.in);
	private static ArrayList<Patron> patrons = new ArrayList<Patron>();
	private static Queue<Patron> line = new Queue<Patron>();
	private static Stack<Ticket> tickets = new Stack<Ticket>();
	
	/**
	 * Main method. Executes program.
	 * 
	 * @param args Not used
	 */
	public static void main(String[] args) {
		boolean quit = false;
        while (!quit) {
            int choice = 0;

            System.out.println(
                "\nWelcome to the amusement park! What would you like to do?");
            System.out.println("1: Add patron to the back of the line");
            System.out.println("2: Put a ticket in the pile");
            System.out.println("3: Get the number of patrons in line");
            System.out.println("4: Get the number of available tickets");
            System.out.println("5: Administer a ticket");
            System.out.println("6: Show patrons with tickets");
            System.out.println("7: Quit");
            System.out.println("Enter your choice (1-7) here: ");
            choice = keyboard.nextInt();

            switch (choice) {
                case 1: addPatron();
                        break;
                case 2: addTicket();
                        break;
                case 3: getLineSize();
                        break;
                case 4: getAvailableTickets();
                        break;
                case 5: administerTicket();
                        break;
                case 6: getPatrons();
                        break;
                case 7: quit = true;
                        break;
                default: break;
            }
        }
	}
	
	/**
	 * Prompts user for a name to add a new Patron to the queue.
	 */
	private static void addPatron() {
		String name = "";
		
		System.out.println("Enter the patron's name:");
        keyboard.nextLine();
        name = keyboard.nextLine();
        
        Patron newPatron = new Patron(name);
        //patrons.add(newPatron);
        line.enqueue(newPatron);
        
        System.out.println(name + " added to the back of the line.");
	}
	
	/**
	 * Creates a new Ticket and pushes it onto the stack.
	 */
	private static void addTicket() {
		Ticket newTicket = new Ticket();
		tickets.push(newTicket);
		System.out.println("Ticket " + newTicket + " added.");
	}
	
	/**
	 * Gets the size of the queue of Patrons.
	 * 
	 * @return int size of the queue.
	 */
	private static int getLineSize() {
		System.out.println("There are " + line.size() + " patrons in line.");
		
		return line.size();
	}
	
	/**
	 * Gets the size of the stack of Tickets.
	 * 
	 * @return int size of the stack.
	 */
	private static int getAvailableTickets() {
		System.out.println("There are " + tickets.size()
				+ " tickets available.");
		
		return tickets.size();
	}
	
	/**
	 * Pops a ticket off the stack and administers it to the next Patron in
	 * queue.
	 */
	private static void administerTicket() {
		if (line.isEmpty()) {
			System.out.println("There is no one in line.");
		} else if (tickets.isEmpty()) {
			System.out.println("There are no available tickets.");
		} else {
			Patron currentPatron = line.dequeue();
			currentPatron.giveTicket(tickets.pop());
			patrons.add(currentPatron);
			System.out.println(currentPatron);
		}
	}
	
	/**
	 * Prints out an ArrayList of all ticketed Patrons.
	 * 
	 * @return ArrayList of all ticketed Patrons.
	 */
	private static ArrayList<Patron> getPatrons() {
		if (patrons.size() > 0) {
			for (int i = 0; i < patrons.size(); i++) {
			System.out.println(patrons.get(i) + "\n");
			}
		} else {
			System.out.println("There are no ticketed patrons.");
		}
		
		return patrons;
	}
}
