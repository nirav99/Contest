#include <iostream>
using namespace std;

int strToI(char str[])
{
  int result = 0, prevRes = 0;
  bool isNeg = false;
  unsigned int start = 0;

  if(str[0] == '-')
  {
    isNeg = true;
    start = 1;
  }

  for(unsigned int i = start; i <strlen(str); i++)
  {
    prevRes = result;
    result = result * 10 + (str[i] - '0');

    if(result < 0 || result < prevRes)
    {
      throw "Overflow error"; 
    }
  }

  if(isNeg)
   result *= -1;
  return result;
}

int main(int argc, char * argv[])
{
  try
  {
    cout << "The int equivalent of " << argv[1] << " : " << strToI(argv[1]) << endl;
    return 0;
  }
  catch(char const *msg)
  {
    cerr << "Exception occurred " << msg << endl;
  }
}

