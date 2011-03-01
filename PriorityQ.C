#include <iostream>
using namespace std;

#include <stdlib.h>

class PriorityQ
{
  public :
    PriorityQ()
    {
      size = 0;
      MAXSIZE = 10;
      a = new int[MAXSIZE + 1];
    }
    void addPQ(int data);
    int deleteMin();
    void printPQ();
  private :
    int MAXSIZE;
    int *a;
    int size;
};

void PriorityQ::addPQ(int data)
{
  int curr, parent;
  size++;

  if(size > MAXSIZE)
  {
    cerr << "ERROR FULL PQ" << endl;
    exit(-1);
  }
  
  curr = size;

  while(curr > 1)
  {
    parent = curr / 2;
    if(a[parent] > data)
    {
      a[curr] = a[parent];
      curr = parent;
    }
    else
      break;
  }
  a[curr] = data;
}

void PriorityQ::printPQ()
{
  cout << "PQ Size : " << size << endl;

  for(int i = 0; i < size; i++)
  {
    cout << a[i + 1] << " ";
  }
  cout << endl;
}

int PriorityQ::deleteMin()
{
  if(size < 1)
  {
    cerr << "ERROR EMPTY PQ" << endl;
    exit(-1);
  }

  int result = a[1];
  int last   = a[size--];
  int curr   = 1;
  int minChild;

  while(curr * 2 <= size)
  {
    minChild = 2 * curr;
    if(minChild + 1 <= size && a[minChild] > a[minChild + 1])
    {
      minChild++;
    }
    if(last < minChild)
    {
      break;
    }
    else
    {
      a[curr] = a[minChild];
      curr = minChild;
    }
  }
  a[curr] = last;
  return result;
}

int main(int argc, char *argv[])
{
  PriorityQ q; 
  int result;

  for(int i = 1; i < argc; i++)
  {
    cout << "Adding " << argv[i] << " to PQ" << endl;
    q.addPQ(atoi(argv[i]));
  }
  q.printPQ();

  for(int i = 1; i < argc; i++)
  {
    result = q.deleteMin();
    cout << "Deleted " << result << " from PQ" << endl;
    q.printPQ();
  }
  return 0;
}
