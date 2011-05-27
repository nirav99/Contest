/**
 * Class to process the requests from the client
 */
package logServer;

import java.util.LinkedList;

import logService.LogMessage;

/**
 * @author Nirav Shah niravs@bcm.edu
 *
 */
public class RequestProcessor
{
  private LogServer logServer                = null; // Instance of LogServer
  private LinkedList<LogMessage> messageList = null; // List of received messages
  
  public RequestProcessor(LogServer ls, LinkedList<LogMessage> msgList)
         throws Exception
  {
    this.logServer   = ls;
    this.messageList = msgList;
    processRequests();
  }
  
  private void processRequests() throws Exception
  {
    LogMessage msg[] = null;
    System.out.println("Starting to process requests");
    while(true)
    {
      msg = getNextMessage();
      
      if(msg != null)
      {
        for(int i = 0; i < msg.length; i++)
          System.out.println("Obtained the message from list : " + msg[i].toString());
        
        msg = null;
      }
      Thread.sleep(5000);
    }
  }
  
  private LogMessage[] getNextMessage()
  {
    LogMessage nextMsg[] = null;
    synchronized(logServer)
    {
      if(messageList.size() > 0)
      {
        nextMsg = new LogMessage[messageList.size()];
        int i = 0;        
        while(messageList.size() > 0)
        {
          nextMsg[i++] = messageList.removeFirst();
        }
      }
    }
    return nextMsg;
  }
}
