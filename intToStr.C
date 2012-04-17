#include <iostream>
using namespace std;

#include <stdlib.h>

void intToStr(int input, char str[])
{
  bool isNeg = false;
  char ch;
  unsigned int idx = 0;
  long input2 = input;

  if(input2 < 0)
  {
    isNeg = true;
    input2 = input2 * -1;
  }

  do
  {
    ch = input2 % 10 + '0';
    input2 = input2 / 10;
    str[idx++] = ch;
  }while(input2);

  if(isNeg)
    str[idx++] = '-';

  str[idx] = '\0';

  // reverse the string
  for(unsigned int start = 0, end = strlen(str) -1; start < end; start++, end--)
  {
    ch = str[start];
    str[start] = str[end];
    str[end] = ch;
  }
}

int main(int argc, char * argv[])
{
  int input = atoi(argv[1]);

  char result[20];

  intToStr(input, result);

  cout << "Int : " << input << endl;
  cout << "Str : " << result << endl;
}
