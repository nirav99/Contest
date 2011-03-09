#include <iostream>
using namespace std;

void reverse(char str[], int start, int end)
{
  char temp;
  while(start < end)
  {
    temp = str[start];
    str[start] = str[end];
    str[end] = temp;
    start++;
    end--;
  }
}

void rotate(char str[], int num)
{
  int len = strlen(str);

  if(num >= len -1)
  {
    num = num % len;
  }

  reverse(str, 0, num -1);
  reverse(str, num, len -1);
  reverse(str, 0, len -1);
}

int main(int argc, char * argv[])
{
  int numTimes = atoi(argv[2]);
  cout << "Original String : " << argv[1] << endl;
  rotate(argv[1], numTimes);
  cout << "New String : " << argv[1] << endl;
  return 0;
}
