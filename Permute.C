#include <iostream>
using namespace std;

void permute(char input[], char output[], int len, bool used[], int level)
{
  if(level >= len)
  {
    cout << output << endl;
    return;
  }
  for(int i = 0; i < len; i++)
  {
    if(used[i] == false)
    { 
      output[level] = input[i];
      used[i] = true;

      permute(input, output, len, used, level + 1);
      used[i] = false;
    }
  }
}

void combine(char input[], char output[], int len, int level, int nextChar)
{
  for(int i = nextChar; i < len; i++)
  {
    output[level] = input[i];
    output[level + 1] = '\0';
    cout << output << endl;
 
    if(i < len -1)
    {
      combine(input, output, len, level + 1, i + 1);
    }
  }  
}

int main(int argc, char * argv[])
{
  int len = strlen(argv[1]);
  char output[len + 1];
  output[len] = '\0';
  bool used[len];

  for(int i = 0; i < len; i++)
    used[i] = false;

  cout << "Permutations for : " << argv[1] << endl;
  permute(argv[1], output, len, used, 0);

  cout << endl << "Combination for " << argv[1] << endl;
  combine(argv[1], output, len, 0, 0);
  
  return 0;
}
