import java.util.*;

/**
 * Class to represent convert an integer into its string representation in
 * binary format.
 */
public class ConvertIntToBinaryStr
{
  public static void convertInt(int input)
  {
    char temp;
    StringBuffer result = new StringBuffer();

    if(input < 0)
    {
      System.err.println("Does not handle negative numbers right now");
      return;
    }
    if(input == 0)
    {
      result.append("0");
    }
    else
    {
      while(input > 0)
      {
        if((input & 1) > 0)
          result.append("1");
        else
          result.append("0");
        input = input >> 1;
      }
      result.reverse();
    }

    System.out.println("The equivalent binary string is : " + result.toString());
  }

  public static void main(String args[])
  {
    System.out.println("Input number : " + args[0]);

    convertInt(Integer.parseInt(args[0]));
  }

}
