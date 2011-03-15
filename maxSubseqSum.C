#include <iostream>
using namespace std;

int findMaximumSubsequenceSum(int input[], int len)
{
  int maxSum    = 0;
  int currStart = 0;
  int currSum   = 0;

  for(int i = 0; i < len; i++)
  {
    currSum = currSum + input[i];
    if(currSum > maxSum)
    {
      maxSum = currSum;
    }
    else
    if(currSum < 0)
    {
      currSum = 0;
    }
  }
  return maxSum;
}

int main(int argc, char * argv[])
{
  int arrayLen = atoi(argv[1]);
  int input[arrayLen];

  cout << "The sequence is : " << endl;

  for(int i = 0; i < arrayLen; i++)
  {
    input[i] = atoi(argv[i + 2]);
    cout << input[i] << " ";
  }
  cout << endl;

  int sum = findMaximumSubsequenceSum(input, arrayLen);

  cout << "Max subsequence sum : " << sum << endl;
  return 0;
}
