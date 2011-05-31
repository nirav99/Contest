/**
 * Class to handle incoming requests
 */
package logServer;

import java.net.*;
import java.util.*;
import java.io.*;
import logService.LogMessage;

/**
 * @author Nirav Shah niravs@bcm.edu
 *
 */
public class RequestListener implements Runnable
{
  private LinkedList<LogMessage> messageList = null; // List of received messages
  private ServerSocket serverSocket          = null;
  private int bindingPort                    = 12345;
  private LogServer logServer                = null;
  
  /**
   * Class constructor
   * @throws Exception
   */
  public RequestListener(LogServer logServer, LinkedList<LogMessage> msgList)
         throws Exception
  {
    this.messageList = msgList;
    this.logServer   = logServer;
    
    serverSocket = new ServerSocket(bindingPort);
    System.out.println("Server socket bound to port : " + bindingPort);
  }
  
  /**
   * Method to process incoming requests
   * @throws Exception
   */
  private void listenToRequests() throws Exception
  {
    Socket clientSocket;
    DataInputStream inputStream;
    String data;
    
    while(true)
    {
      System.out.println("Waiting for client requests");
      
      clientSocket = serverSocket.accept();
      inputStream  = new DataInputStream(clientSocket.getInputStream());
      
      data = inputStream.readUTF();
      System.out.println("Read from socket : " + data);
      
      addToList(data);
      inputStream.close();
      clientSocket.close();
      clientSocket = null;
      inputStream  = null;
    }
  }
  
  /**
   * Convert the string to LogMessage and add to the list
   * @param data
   * @throws Exception
   */
  private void addToList(String data) throws Exception
  {
    synchronized(logServer)
    {
      LogMessage msg = new LogMessage(data);
      messageList.addLast(msg);
    }
  }

  @Override
  public void run()
  {
    try
    {
      listenToRequests();
    }
    catch(Exception e)
    {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
  }
}
