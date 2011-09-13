#include <stdio.h>
#include <string.h>

/*
 * Function to remove duplicate characters from a character string.
 */
void removeDuplicates(char * s)
{
  int len, writePos;

  if(s == NULL)
    return;
  len = strlen(s);

  if(len < 2)
    return;

  for(int i = 0; i < len; i++)
  {
    writePos = i + 1;
    for(int j = i + 1; j < len; j++)
    {
      if(s[i] != s[j])
      {
        s[writePos++] = s[j]; 
      }
    }
    len = writePos;
  }
  s[writePos] = '\0';
}

int main(int argc, char * argv[])
{
  char *input_str = argv[1];
  printf("Input string : %s\n", input_str);
  removeDuplicates(input_str);
  printf("Duplicates removed : %s\n", input_str);
  return 0;
}
