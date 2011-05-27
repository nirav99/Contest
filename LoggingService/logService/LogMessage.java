/**
 * Class to encapsulate a log message
 */
package logService;

import java.util.zip.DataFormatException;

/**
 * @author Nirav Shah niravs@bcm.edu
 *
 */
public class LogMessage
{
  private String flowcellBarcode;  // Sequencing event identifier
  private String message;          // Actual message
  private String delimiter = ";";  // String for marking field limits
  
  /**
   * Class constructor
   * @param fcBarcode
   * @param msg
   */
  public LogMessage(String fcBarcode, String msg)
  {
    this.flowcellBarcode = fcBarcode;
    this.message = msg;
  }
  
  /**
   * Class constructor to de-serialize the LogMessage object
   * @param msg
   * @throws DataFormatException
   */
  public LogMessage(String msg) throws DataFormatException
  {
    String tokens[] = msg.split(delimiter);
    
    if(tokens == null || tokens.length != 2)
    {
      throw new DataFormatException("The specified message " + msg + " cannot be converted to an instance of LogMessage");
    }
    flowcellBarcode = tokens[0];
    message         = tokens[1];
  }
  
/**
   * Returns flowcell barcode
   * @return
   */
  public String getFlowcellBarcode()
  {
    return flowcellBarcode;
  }
  
  /**
   * Returns actual message string
   * @return
   */
  public String getMessage()
  {
    return message;	  
  }
  
  /**
   * Serialize the object as a string
   */
  public String toString()
  {
    return flowcellBarcode + delimiter + message; 
  }
}
