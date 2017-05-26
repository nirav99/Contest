/**
 * Phone number digits are mapped to letters.
 * Write a function that would print all the combinations of letters for the given phone number
 * @author nirav99
 *
 */
public class PhoneNumberToLetters
{
	/**
	 * Returns the mapping between the digits and the letters
	 * @param digit
	 * @return
	 */
  public static char[] getMapping(char digit)
  {
  	if(digit == '1' || digit == '0')
  		return null;
  	else
  	if(digit == '2')
  		return new char[]{'A', 'B', 'C'};
  	else
  	if(digit == '3')
  		return new char[]{'D', 'E', 'F'};
  	else
  	if(digit == '4')
  		return new char[]{'G', 'H', 'I'};
  	else
  	if(digit == '5')
  		return new char[]{'J' , 'K', 'M'};
  	else
  	if(digit == '6')
  		return new char[]{'N', 'O', 'P'};
  	else
  	if(digit == '7')
  		return new char[]{'Q', 'R', 'S'};
  	else
  	if(digit == '8')
  		return new char[]{'T', 'U', 'V'};
  	else
  	if(digit == '9')
  		return new char[]{'W', 'X', 'Y', 'Z'};
  	return null;	
  }
  
  private static void print(char[] array)
  {
  	for(int i = 0; i < array.length; i++)
  		System.out.print(array[i]);
  	System.out.println();
  }
  
  public static void findPhoneNumberLetters(char[] number, int index, char[] output)
  {
  	if(index >= number.length)
  	{
  		print(output);
  		return;
  	}
  	else
  	{
  		char[] replacements = getMapping(number[index]);
  		
  		if(replacements != null)
  		{
  			for(int j = 0; j < replacements.length; j++)
  			{
  				output[index] = replacements[j];
  				findPhoneNumberLetters(number, index + 1, output);
  			}
  		}
  		else
  		{
  			output[index] = number[index];
  			findPhoneNumberLetters(number, index + 1, output);
  		}
  	}
  }
  
  public static void main(String[] args)
  {
  	try
  	{
  		String[] numbers = {"234"}; //, "12", "23041"};
  		
  		for(String number : numbers)
  		{
  			char[] output = new char[number.length()];
  			findPhoneNumberLetters(number.toCharArray(), 0, output);
  		}
  	}
  	catch(Exception e)
  	{
  		e.printStackTrace();
  	}
  }
}
