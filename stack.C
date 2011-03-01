#include <iostream>
#include <list>
using namespace std;

#include <stdlib.h>

class stack
{
  public :
   stack()
   {
//     head = NULL;
   }
  
   void push(int data);
   int pop();

   void show()
   {
     list<int>::iterator it;

     for(it = head.begin(); it != head.end(); it++)
     {
       cout << *it << endl;
     }
   }

  private :
   list<int> head;
};

void stack::push(int data)
{
  head.push_front(data);
}

int stack::pop()
{
  if(head.size() == 0)
  {
    cerr << "Empty stack" << endl;
    return -1;
  }
  int data = head.front();
  head.pop_front();
  return data;
}

int main(int argc, char *argv[])
{
  stack istack;

  for(int i = 0; i < 10; i++)
   istack.push(i);

  cout << "Stack values " << endl;
    istack.show();

  cout << "Popping values " << endl;
  for(int i = 0; i < 10; i++)
   cout << istack.pop() << " ";
  cout << endl;
  return 0;
}
