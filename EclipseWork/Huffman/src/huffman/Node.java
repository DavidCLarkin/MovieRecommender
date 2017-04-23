package huffman;

import java.io.Serializable;

public class Node implements Comparable, Serializable
{
	    /**
	    * 
	    */
		private static final long serialVersionUID = -1415859363893588060L;
		
		Node left, right;
	    char letter;
	    int frequency;

	    public Node(char letter, int freq, Node left, Node right)
	    {
	    	this.letter = letter;
	    	this.frequency = freq;
	    	this.left = left;
	    	this.right = right;
	    }
	    
	    @Override
	    public String toString()
	    {
	    	return letter + ": " + frequency; //to string of Letter
	    }
	    
	    public boolean isLeaf()
	    {
	    	return left == null && right == null;
	    }

		@Override
		public int compareTo(Object node) 
		{
			return frequency - ((Node) node).frequency;
		}
}