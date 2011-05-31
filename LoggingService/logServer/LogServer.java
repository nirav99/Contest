/**
 * Class to implement the logging server
 */
package logServer;

import java.util.LinkedList;
import logService.LogMessage;

/**
 * @author Nirav Shah niravs@bcm.edu
 * Class to implement the logging server
 */
public class LogServer
{
  LinkedList<LogMessage> messageList = null;
  RequestListener listener           = null;
  RequestProcessor processor         = null;
  
  public LogServer()
  {
    try
    {
      messageList = new LinkedList<LogMessage>();

      System.out.println("Starting request listener");
      listener    = new RequestListener(this, messageList);
      System.out.println("Starting the listener thread");
      new Thread(listener).start();
      System.out.println("Started the listener thread");
      
      System.out.println("Starting request processor");
      processor   = new RequestProcessor(this, messageList);
    }
    catch(Exception e)
    {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
  }
  
  public static void main(String args[])
  {
    System.out.println("Staring the server");
    LogServer server = new LogServer();
  }
}

