package huffman;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

public class Driver 
{
	private static HuffmanTree huffTree = new HuffmanTree();
	private Scanner input = new Scanner(System.in);
	//Node root;

	public Driver()
	{
		runMenu();
	}
    public static void main(String[] args) 
    {
    	Driver app = new Driver();
    	//BTreePrinter.printNode(root); //display the tree
    	
    }
    
    /**
     * Method to encode the whole file
     * Get Characters and Frequencies, Build the tree,
     * get binary encodings for each character, serialize the root node,
     * and write the binary to the file
     */
    public void encodeWholeFile()
    {
    	boolean goodName = false;
    	File fileName = null;
    	do
    	{
    		try
    		{
    	    	fileName = huffTree.chooseFile(); //choose a file
    	    	if(fileName != null)
    	    		goodName = true;
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    	} while(!goodName);

    	//System.out.println(fileName.length());
		
    	long startTimeOne = System.currentTimeMillis(); //start timer one
    	
    	String fileStr = "";
		try 
		{
			fileStr = readFile(fileName, StandardCharsets.UTF_8); //convert file to string
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
    	
    	HashMap<Character, Integer> letters = huffTree.letterCount(fileStr); //count frequency and letters
    	
    	Node root = huffTree.buildHuffmanTree(letters); //set root note to the tree of nodes, root is highest frequency
    	
    	HashMap<Character, String> encoding = huffTree.getEncoding(root); //get binary encodings for each letter from tree traversal
    	
    	//print letters and their binary code
    	System.out.println("\nCharacter\tCode\tFrequency ");
    	System.out.println("-----------------------------------");
    	
    	for(Character c : encoding.keySet())
    	{
    		Character key = c; //character
    		String value = encoding.get(c).toString(); //binary huffman
    		String freq = letters.get(c).toString();
    		System.out.println("   '" + key + "'" + "\t\t" +value + "\t    " + freq);
    	}
    	
    	String code = encodeFileStr(fileStr, encoding);
    	
    	System.out.println("-----------------------------------");
    	System.out.println("Encoding...");
    	long stopTimeOne = System.currentTimeMillis(); //end timer one
    	System.out.println("What would you like to save the file name as?");	
    	input.nextLine();
    	String fileNameSave = input.nextLine();
    	long startTimeTwo = System.currentTimeMillis(); //second start timer
    	
    	huffTree.serializeNode(root, fileNameSave); //serialize node to test file
    	File writtenFile = huffTree.writeFile(code, fileNameSave); //write the whole binary code to file
    	
    	long stopTimeTwo = System.currentTimeMillis(); //second end timer
    	//Add up timer one and timer two, and get the time it takes minus the file name input
    	System.out.println("Time to encode : " + ((stopTimeOne - startTimeOne)/1000.0) + ((stopTimeTwo - startTimeTwo)/1000.0));
    	System.out.println("File size after: " + writtenFile.length());
    }
    
    /**
     * Method to read the file, deserialize the root node
     * at the top of the file, and decode the whole file
     */
    public void decodeWholeFile()
    {
    	boolean goodName = false;
    	File file = null;
    	do
    	{
    		try
    		{
    	    	file = huffTree.chooseFile(); //choose a file
    	    	if(file != null)
    	    		goodName = true;
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    	} while(!goodName);
    	
    	long startTimeOne = System.currentTimeMillis(); //start timer one
    	
    	String fileStr = ""; //empty string to populate
		try 
		{
			fileStr = readFile(file, StandardCharsets.UTF_8); //convert file to string
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		
		Node root = huffTree.deserialzeAddress(file.getAbsolutePath());
		String decodedFile = "";
		try
		{
			decodedFile = decode(fileStr, root);
		}
		catch(Exception e)
		{
			System.out.println("Cannot decode file");
		}
		
    	System.out.println("Decoding...");
    	long stopTimeOne = System.currentTimeMillis(); //end timer one
    	System.out.println("What would you like to save the file name as?");
    	input.nextLine();
    	String fileNameSave = input.nextLine();
    	long startTimeTwo = System.currentTimeMillis(); //end timer two
    	File writtenFile = huffTree.writeFile(decodedFile, fileNameSave);
    	long stopTimeTwo = System.currentTimeMillis(); //second end timer
    	
    	System.out.println("File Size after: "+writtenFile.length());
    	System.out.println("Time to decode : " + ((stopTimeOne - startTimeOne)/1000.0) + ((stopTimeTwo - startTimeTwo)/1000.0));
    }
    
    private int Menu()
	{
		boolean goodInput = false;
		int choice=0;
		do
		{
			try
			{
				System.out.println("---------------------------------------------------------------------|");
				System.out.println("|  Welcome to the Encoding/Decoding Program				     |");
				System.out.println("|  Here, you can choose what option you want to do, seen below.      |");
				System.out.println("|  Simply input a number to choose your option.			     |");
				System.out.println("|--------------------------------------------------------------------|");
				System.out.println("|   1) Encode a file						     |");
				System.out.println("|   2) Decode a file				     			|");
				System.out.println("|   0) Exit						             |");
				System.out.println("|--------------------------------------------------------------------|");
				System.out.println("Choose your option ==>");
				choice = input.nextInt();
				goodInput = true;
			}
			catch(Exception e)
			{
				System.out.println("Enter a valid number from the choices above");
				String throwOut = input.nextLine();
			}
		} while(!goodInput);
		return choice;
	}
    
	public void runMenu()
	{
		int choice = Menu();
		while(choice != 0)
		{
			switch (choice)
			{			
				case 1:		encodeWholeFile();
						break;
				case 2:		decodeWholeFile();
						break;
				default: 	System.out.println("You've entered an invalid number : " + choice +". Try again");
						break;
			}
			
	        System.out.println("\nPress any key to continue...");
	        input.nextLine();
	        input.nextLine(); //bug in scanner
	        choice = Menu();
		}
	       System.out.println("Exiting... bye");
	       System.exit(0);
	}
    
    /**
     * Method to read a file to a String object
     * @param fileName
     * @param encoding
     * @return
     * @throws IOException
     */
    public static String readFile(File fileName, Charset encoding) throws IOException 
    {
    	byte[] encoded = Files.readAllBytes(Paths.get(fileName.getAbsolutePath()));
    	return new String(encoded, encoding);
    }
    
    /**
     * Method to encode a string (made from file contents)
     * @param fileStr
     * @param encoding
     * @return
     */
    public String encodeFileStr(String fileStr, HashMap<Character, String> encoding)
    {
    	StringBuilder code = new StringBuilder();
		while(fileStr.length() > 0)
		{
			char toEncode = fileStr.charAt(0); //set first char to encoding char
			code.append(encoding.get(toEncode)); //add the code to huffman binary
			fileStr = fileStr.substring(1); //get rid of the first letter each time
		}
		return code.toString();
    }
    
    /**
     * Method to decode a binary huffman encoded file
     * @param encodedString - The binary huffman coded string
     * @param rootNode - the root node of the huffman tree
     * @return
     */
    public String decode(String encodedString, Node rootNode)
    {

    	StringBuilder output = new StringBuilder();
    	Node base = rootNode; //base is set to root node of huffman tree
    	while (!encodedString.isEmpty())
    	{
    		//Keep taking away the char at index 0 until a leaf node is met, then add that node's letter
    	    if (encodedString.charAt(0) == '1') //if number in the encoding is a '1', to right on huffman tree
    	    {
    	    	base = base.right;
    	    	encodedString = encodedString.substring(1);
    	    }
    	    else 
    	    {
    	    	base = base.left;
    	    	encodedString = encodedString.substring(1);
    	    }
    	    
    	    if (base.isLeaf()) //if null on either side
    	    {
    	  		output.append(base.letter); //add the letter
    	  		base = rootNode; //set the base to the top of the tree
    	  	}

    	}
    	return output.toString();
    }
}
