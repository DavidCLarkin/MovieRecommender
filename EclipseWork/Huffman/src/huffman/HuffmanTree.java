package huffman;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.PriorityQueue;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class HuffmanTree
{

	public static JFileChooser chooser = new JFileChooser();
    
    /**
     * Method to write huffman binary to a file
     * @param code
     * @return File to get data from the file
     */
    public File writeFile(String code, String fileName)
    {
    	File file = new File(fileName + ".txt");
    	
		BufferedWriter bw = null;
		FileWriter fw = null;
		
		
    	try
    	{
    		fw = new FileWriter(file.getAbsolutePath(), true);
    		bw = new BufferedWriter(fw);
    		bw.write(code);
    		bw.flush();
    		fw.flush();
    		//System.out.println("Finished writing to file");
    	}
    	catch(IOException e)
    	{
    		System.out.println("Unable to encode" + e);
    	} 
    	finally 
    	{
    		try{
    			if(bw != null)
    			{
    				bw.close();
    			}
    			if(fw!=null)
    			{
    				fw.close();
    			}
    		}
    		catch(IOException ex)
    		{
    			ex.printStackTrace();
    		}
    	}
    	return file;
    	
    }
    
    /**
     * Method to serialize a node object to a file, used when writing to a file
     * @param node
     * @param fileName
     */
    public void serializeNode(Node node, String fileName)
    {
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;
		
		try 
		{
			fout = new FileOutputStream(fileName + ".txt");
			oos = new ObjectOutputStream(fout);
			oos.writeObject(node);

			//System.out.println("Done");

		} 
		catch (Exception ex) 
		{

			ex.printStackTrace();

		} finally 
		{

			if (fout != null) 
			{
				try 
				{
					fout.close();
				} catch (IOException e) 
				{
					e.printStackTrace();
				}
			}

			if (oos != null) 
			{
				try {
					oos.close();
				} catch (IOException e) 
				{
					e.printStackTrace();
				}
			}

		}
    }
    
    /**
     * Method to de-serialize a serialized node from a specified file
     * @param fileName
     * @return a node, root node generally
     */
	public Node deserialzeAddress(String fileName) 
	{

		Node root = null;

		FileInputStream fin = null;
		ObjectInputStream ois = null;

		try {

			fin = new FileInputStream(fileName); //input is file
			ois = new ObjectInputStream(fin); //getting object from the file input stream
			root = (Node) ois.readObject();

		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		} 
		finally 
		{

			if (fin != null) 
			{
				try 
				{
					fin.close();
				} catch (IOException e) 
				{
					e.printStackTrace();
				}
			}

			if (ois != null) 
			{
				try 
				{
					ois.close();
				} catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
		return root;

	}

    
    /**
     * Counts the amount of time a letter appears in a given sentence/phrase
     * @param sentence
     * @return
     */
    public HashMap<Character, Integer> letterCount(String sentence)
    {
    	HashMap<Character, Integer> charCount = new HashMap<Character, Integer>();
    	char[] charArray = sentence.toCharArray();
    	
    	for(char c : charArray)
    	{
    		if(charCount.containsKey(c))
    		{
    			charCount.put(c, charCount.get(c) + 1); //increment value
    		}
    		else
    		{
    			charCount.put(c, 1); //add it to the map
    		}
    	}
    	
    	return charCount;
    }
    
    /**
     * Method to gradually build the tree of nodes. 
     * @param charCount - A HashMap made from calculating all chars and frequencies that they occur, using letterCount()
     * @return Root node(tree)
     */
    public Node buildHuffmanTree(HashMap<Character, Integer> charCount)
    {
    	PriorityQueue<Node> queue = new PriorityQueue<Node>();
    	for(char c: charCount.keySet())
    		queue.add(new Node(c, charCount.get(c), null, null)); //add everything to a priority queue
    	
    	//Use the queue to add frequencies together
    	while(queue.size() > 1) //while there's more than 1 node, keep removing and adding them together
    	{
    		Node left = queue.poll();
    		Node right = queue.poll();
    		Node newNode = new Node('-', left.frequency + right.frequency, left, right); //add node from additions of 2 nodes' frequencies, and it has
    																				  //left and right nodes from polling
    		queue.add(newNode); //highest node
    	}
    	
    	return queue.poll(); //return the tree
    }
    
    /**
     * Recursive method to get the binary huffman tree from traversal
     * @param root
     * @return
     */
    public HashMap<Character, String> getEncoding(Node root)
    {
    	HashMap<Character, String> encoding = new HashMap<Character, String>();
    	encode(root, "", encoding);
    	
    	return encoding;
    }
    
    /**
     * Method to encode each letter to a binary number
     * @param node The Root node of the tree at the end
     * @param code An Empty string usually, so you can get the binary from this
     * @param encoding An Empty HashMap so I can have a map of the letter and code
     */
    private void encode(Node node, String code, HashMap<Character, String> encoding) 
    {
        if (node.isLeaf())  //if its the end of the branch
        {
                encoding.put(node.letter, code); //add the letter and the binary code
        }
        else 
        {
        	if(node.left != null)  //if left is not null, keep searching and add 0 , string gets bigger the more searches
        		encode(node.left, code + "0", encoding);
            if (node.right != null) //if right not null, keep going and add 1
                encode(node.right, code + "1", encoding);
        }
    }
    
    /**
     * File chooser that returns the name of the file for use
     * @return
     */
    public File chooseFile()
    {
    	chooser.setCurrentDirectory(new java.io.File("."));
    	chooser.setDialogTitle("Choose a File");
    	chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    	FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "text"); //only use txt files
    	chooser.setFileFilter(filter);
    	//chooser.setAcceptAllFileFilterUsed(false);
    	File file = null;

    	if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
    	{
    		 file = chooser.getSelectedFile();
    		 System.out.println("File Size : "+file.length());
    	}
    	else
    		 System.out.println("No Selection ");
    	
    	return file;
    }
    
    
}
