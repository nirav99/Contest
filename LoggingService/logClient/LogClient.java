package logClient;

import java.net.*;
import logService.LogMessage;
import java.io.*;

public class LogClient
{

  /**
   * @param args
   */
  public static void main(String[] args)
  {
	  try
	  {
  
      LogMessage msg = null;
      InetSocketAddress remoteAddress = new InetSocketAddress("127.0.0.1", 12345);
    
      for(int i = 0; i < 10; i++)
      {
        String fcBarcode = "FCBarcode-" + i;
        String message   = "This is message number " + i;
      
        msg = new LogMessage(fcBarcode, message);
        Socket clientSocket = new Socket();
        clientSocket.connect(remoteAddress);
        DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
        
        System.out.println("Writing : "+ msg.toString() + " to socket");
        dos.writeUTF(msg.toString());
        dos.close();
        clientSocket.close();
      }
    }
    catch(Exception e)
    {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
  }
}
