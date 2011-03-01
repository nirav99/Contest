#include <iostream>
using namespace std;

#include <stdlib.h>

void removeChars(char input[], char toRemove[])
{
  bool map[256];
  int src = 0;
  int dest = 0;

  for(short i = 0; i < 256; i++)
    map[i] = false;

  for(short i = 0; i < strlen(toRemove); i++)
  {
    map[toRemove[i]] = true;
  }

  for(int idx = 0; idx < strlen(input); idx++)
  {
    if(map[input[idx]] == false)
    {
      input[dest++] = input[src];
    }
    src++;
  }
  input[dest] = '\0';

  cout << "See : " << input << endl;
}

int main(int argc, char * argv[])
{
  cout << "Input string : " << argv[1] << endl;
  cout << "Remove String : " << argv[2] << endl;

  char input[strlen(argv[1]) + 1];
  strcpy(input, argv[1]);
  removeChars(argv[1], argv[2]);
  cout << "Output       : " << argv[1] << endl;
  return 0;
}
