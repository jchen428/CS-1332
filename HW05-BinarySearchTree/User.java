/**
 * This class represents a User with a username and an associated name.
 * 
 * @author Jesse Chen
 */
public class User implements Comparable<User> {

	private String userName, name;
	
	/**
	 * Constructor for a new User
	 * 
	 * @param userName The User's username.
	 * @param name The User's name.
	 */
	public User(String userName, String name) {
		this.userName = userName;
		this.name = name;
	}
	
	/**
     * The toString method is used for debugging purposes.
     *
     * @return A String representation of the User.
     */
	public String toString() {
		return userName + "-" + name;
	}
	
	/**
	 * Overrides the default compareTo method. Compares users by their username
	 * (not case-sensitive).
	 * 
	 * @param other The other User being compared to.
	 * @return An integer value that lexicographically describes the order of
	 * 		   the two Users.
	 */
	public int compareTo(User other) {
		String userLC = userName.toLowerCase();
		String otherUserLC = other.userName.toLowerCase();
		
		return userLC.compareTo(otherUserLC);
	}
	
	/**
	 * Determines if another object is equal to the current User.
	 * 
	 * @param other The other object being compared for equality.
	 * @return true if compareTo returns 0, otherwise false.
	 */
	public boolean equals(Object other) {
		boolean result = false;
		
		if (other != null) {
			if (other.getClass() == getClass()) {
				User u = (User) other;
				
				if (u.compareTo(this) == 0) {
					result = true;
				}
			}
		}
		
		return result;
	}
}
