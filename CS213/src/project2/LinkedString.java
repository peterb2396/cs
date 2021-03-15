package project2;

/**
 * Implementations of the operations for a Bag using singly linked list
 * @author Peter
 * @version 1.0
 */
public class LinkedString implements StringInterface {
	/**
	 * A ref to the first node in the array
	 */
	private Node head; //Head reference (contains first char in string)
	
	/**
	 * The size of this Bag (# of Objects within it)
	 */
	public int count; //Length of the string
	
	/**
	 * Generate an empty string (doubly linked list)
	 */
	public LinkedString() { //Calls master constructor to instantiate a null head and 0 count
		this(new char[0]);
	}
	
	/**
	 * Generate a string (doubly linked) with the char array given
	 * @param head the first char of the string
	 */
	public LinkedString(char[] chars) { //Constructs a new DLL and allocates necessary nodes
		if (chars.length > 0)
		{
			this.head = new Node(chars[0]); //Set head to first of char array
			this.count = 1;
			for (int i = 1; i<chars.length; i++)
			{//Add a node for each element of the chars array
				Node last_node = find(length() - 1);
				
				Node new_node = new Node(chars[i]);
				
				new_node.setPrev(last_node);
				
				last_node.setSuccessor(new_node);
				this.count++;
			}
		}
		else
		{
			this.head = null;
			this.count = 0;
		}
		
	}
	
	/**
	 * Generate a string (doubly linked) with the given string
	 * @param head the first char of the string
	 */
	public LinkedString(String str) { //Converts a string to chars to construct a DLL with these chars to become nodes
		
		this(str.toCharArray());
	}
	
	/**
	 * Return a ref to a node at a given index
	 * @return Node at the given index
	 */
	private Node find(int index) {      //Traverse the nodes by repeatedly getting the successor index times.
		int pos = 0;
		Node found = this.head;
		while (pos != index) {
			pos++;
			found = found.getSuccessor();
		}
		return found;
	}
	/**
	 * Retrieve the amount of elements in the bag
	 * @return the size of this string (num chars)
	 */
	public int length() { //Return the fild containing the integer representation of the length of this DLL
		return this.count;
	}
	
	
	/**
	 * Check to see if the list is empty
	 * @return boolean If list is empty then true
	 */
	public boolean isEmpty() { //Are there no characters in the string?
		return length() == 0;
	}

	/**
	 * Returns LinkedString that is a substring of this string at the specified boudns
	 * @throws StringBoundsException if unreachable bound
	 * @param from index to start substring
	 * @param to one more than index to end substring at
	 * @return specified substring of this string if valid
	 */
	public LinkedString substring(int from, int to) throws StringBoundsException {
		//Create a new LinkedString object, and add nodes by reading the characters of this string
		//Check if out of bound
		if (to > length())
			throw new StringBoundsException("The specified substring is not within the bounds of the target!");
		else
		{   //Create the substring and set the head to the element within the node at the start index
			LinkedString sub = new LinkedString((String)find(from).getElement());
			
			//Now we need to loop the rest of the chars and create + add a node to the list
			for (int c = from+1; c<to; c++) {
				//Add a node with element that is the Char of the substring at the respective index to the end of this LinkedList.
				
				Node last_node = find(length() - 1);
				Node new_node = new Node(find(c).getElement(), null, last_node);
				last_node.setSuccessor(new_node);
				
				this.count++;
			}
		return sub;
		}
	}

	/**
	 * Join the specified string to the end of this one
	 * @param other string to join to this one
	 * @return LinkedString object of the new combinatorial string
	 */
	public LinkedString concat(LinkedString other) {
		//Create and return a new LinkedString object that copies the parameter's nodes to a new copy of this one
		//Maintain immutable property
		LinkedString new_str = new LinkedString(getChars());
		
		for (int i = 0; i<other.length(); i++)
		{
			Node last_node = new_str.find(length()-1);                             //Store a reference to the last node in the DLL
			Node new_node = new Node(other.find(i).getElement(), null, last_node); //Create the new node to be the next character and set the previous reference
			last_node.setSuccessor(new_node);                                      //Set the successor reference of the node that was last to the last node
			
			new_str.count++;
		}
		return new_str;
	}
	
	/**
	 * Return the LinkedList as an array of chars.
	 * @return a char array containing the elements of the DLL 
	 */
	private char[] getChars()
	{
		char[] chars = new char[length()];
		for (int i = 0; i<length(); i++)
		{
			chars[i] = (char) find(i).getElement();
		}
		
		return chars;
	}

	/**
	 * get the char at the specified index
	 * @throws StringBoundsException if character index does not exist
	 * @return char at the index
	 */
	public char charAt(int index) throws StringBoundsException{
		//Check if out of bounds
		if (index >= length())
			throw new StringBoundsException("Can not access element "+ index + " because it does not exist!");
		else //Traverse to the node and return it's element casted as a char
			return (char)find(index).getElement();
	}
	
	/**
	 * Overrides the printing of a LinkedString, to return the sequence of characters rather than the memory address.
	 * @return String representation of our LinkedString
	 */
	@Override
    public String toString() {
        char[] chars = new char[length()];
        for (int i = 0; i<length(); i++)
        {
        	chars[i] = (char) this.find(i).getElement();
        }
        //Accumulate each element of node as characters, put them together in a string and return it.
        return new String(chars);
        
    } 
	

}
