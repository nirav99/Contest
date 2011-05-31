/**
 * Class to process the requests from the client
 */
package logServer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.io.*;
import logService.LogMessage;

/**
 * @author Nirav Shah niravs@bcm.edu
 *
 */
public class RequestProcessor
{
  private LogServer logServer                = null; // Instance of LogServer
  private LinkedList<LogMessage> messageList = null; // List of received
                                                     // messages
  private String dateFormat = "yyyy_MM_dd";
  private BufferedWriter writer              = null;
  
  /**
   * Class constructor
   * @param ls
   * @param msgList
   * @throws Exception
   */
  public RequestProcessor(LogServer ls, LinkedList<LogMessage> msgList) throws
         Exception
  {
    this.logServer   = ls;
    this.messageList = msgList;
    processRequests();
  }
  
  /**
   * Method to process the incoming requests. It dequeues the messages from
   * the list and logs them to a suitable file
   * @throws Exception
   */
  private void processRequests() throws Exception
  {
    LogMessage msg[] = null;
    System.out.println("Starting to process requests");
    while(true)
    {
      msg = getNextMessage();
      
      if(msg != null)
      {
        openLogFileHandle();
        for(int i = 0; i < msg.length; i++)
        {
//          System.out.println("Obtained the message from list : " +
//          msg[i].toString());
          writer.write(msg[i].toString());
          writer.newLine();
        }
        writer.flush();
        msg = null;
      }
      Thread.sleep(3000);
    }
  }
  
  /**
   * Get all available messages from the queue
   * @return
   */
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
  
  /**
   * Create a log file name based on current date
   * @return
   */
  private String getLogFileName()
  {
    String fileName = "ArchiveList_" + getCurrentDate() + ".txt";
    return fileName;
  }
  
  /**
   * Create a new file handle per day.
   * @throws IOException
   */
  private void openLogFileHandle() throws IOException
  {
    String fileName = getLogFileName();
    String currDate = getCurrentDate();
    
    if(fileName.contains(currDate))
    {
      if(writer == null)
      {
        writer = new BufferedWriter(new FileWriter(fileName));
      }
    }
    else
    {
      if(writer != null)
      {
        writer.close();
        writer = new BufferedWriter(new FileWriter(fileName));
      }
    }
  }
  
  /**
   * Helper method to get the current date in yyyy_mm_dd format
   * @return
   */
  private String getCurrentDate()
  {
    Calendar cal = Calendar.getInstance();
	  SimpleDateFormat df = new SimpleDateFormat(dateFormat);
	  return df.format(cal.getTime());
  }
}
