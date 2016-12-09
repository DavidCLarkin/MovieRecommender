/**
 * XMLSerializer class
 * NOT IMPLEMENTEd
 * 
 * @author David Larkin
 * @version 09/12/2016
 */
package utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;

public class XMLSerializer implements Serializer
{

  private Stack stack = new Stack();
  private File file;

  /**
   * Constructor for XMLSerializer
   * @param file
   */
  public XMLSerializer(File file)
  {
    this.file = file;
  }

  /**
   * Push method to push an object to a stack
   */
  public void push(Object o)
  {
    stack.push(o);
  }

  /**
   * Pop Method to take an object off a stack
   */
  public Object pop()
  {
    return stack.pop(); 
  }

  /**
   * Read Method to read a file
   */
  @SuppressWarnings("unchecked")
  public void read() throws Exception
  {
    ObjectInputStream is = null;

    try
    {
      XStream xstream = new XStream(new DomDriver());
      is = xstream.createObjectInputStream(new FileReader(file));
      Object obj = is.readObject();
      while(obj != null)
      {
    	  stack.push(obj);
    	  obj = is.readObject();
      }
    }
    finally
    {
      if (is != null)
      {
        is.close();
      }
    }
  }

  /**
   * Write method to write to file
   */
  public void write() throws Exception
  {
    ObjectOutputStream os = null;

    try
    {
      XStream xstream = new XStream(new DomDriver());
      os = xstream.createObjectOutputStream(new FileWriter(file));
      while(!stack.empty())
      {
    	  os.writeObject(stack.pop());
      }
    }
    finally
    {
      if (os != null)
      {
        os.close();
      }
    }
  }
}
