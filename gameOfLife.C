#include <iostream>
using namespace std;

#include <stdlib.h>

#define ROWS 3
#define COLS 3

int board[ROWS][COLS];

void initialize()
{
  int x;
  srand(ROWS * COLS);
  for(int i = 0; i < ROWS; i++)
  {
    for(int j = 0; j < COLS; j++)
    {
       x = rand() % 10;
       if(x < 5)
         board[i][j] = 0;
       else
         board[i][j] = 1;        
    }
  }
}

void printBoard()
{
  for(int i = 0; i < ROWS; i++)
  {
    for(int j = 0; j < COLS; j++)
    {
      cout << board[i][j] << " ";
    }
    cout << endl;
  }
}

int getNeighborState(int r, int c)
{
  int neighState = 0;

  int rows[3];
  int cols[3];

  rows[0] = r -1;
  rows[1] = r;
  rows[2] = r + 1;

  cols[0] = c -1;
  cols[1] = c;
  cols[2] = c +1;

  for(int i = 0; i < 3; i++)
  {
    for(int j = 0; j < 3; j++)
    {
      if(rows[i] == r && cols[j] == c)
        continue;
      if((rows[i] >= 0 && rows[i] < ROWS) &&
         (cols[j] >= 0 && cols[j] < COLS))
      {
        neighState += board[rows[i]][cols[j]];
      }
    }
  }
//  cout << "I = " << r << " J = " << c << " Neighbor Count = " << neighState << endl;
  return neighState;
}

void nextState()
{
  int count = 0;

  int newBoard[ROWS][COLS];
  for(int i = 0; i < ROWS; i++)
  {
    for(int j = 0; j < COLS; j++)
    {
       newBoard[i][j] = board[i][j];
       count = getNeighborState(i, j); 
       
       if(count == 3)
         newBoard[i][j] = 1;
       else
       if(count != 2)
         newBoard[i][j] = 0;
    }
  }

  for(int i = 0; i < ROWS; i++)
    for(int j = 0; j < COLS; j++)
      board[i][j] = newBoard[i][j];
}

int main(int argc, char * argv[])
{
  initialize();
  cout << "State of the board" << endl;
  printBoard();
  nextState();
  cout << "New state of the board" << endl;
  printBoard();
  return 0;
}
