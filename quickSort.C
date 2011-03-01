#include <iostream>
using namespace std;

#include <stdlib.h>

int partition(int a[], int left, int right)
{
  int pivotIndex = left + (right - left) / 2;
  cout << "Pivot Index = " << pivotIndex << endl;
  int pivotValue = a[pivotIndex];
  int writePos = left;

  int temp;

  temp = a[pivotIndex];
  a[pivotIndex] = a[right];
  a[right] = temp;

  for(int i = left; i < right; i++)
  {
    if(a[i] <= pivotValue)
    {
      temp = a[i];
      a[i] = a[writePos];
      a[writePos] = temp;
      writePos++;
    }
  }

  temp = a[writePos];
  a[writePos] = a[right];
  a[right] = temp;

  return writePos;
}

void quickSort(int a[], int start, int end)
{
  if(start < end)
  {
    int pivotPos = partition(a, start, end);
    quickSort(a, start, pivotPos - 1);
    quickSort(a, pivotPos + 1, end);
  }
}

int main(int argc, char * argv[])
{
  int len = argc -1;
  cout << "Number of elements = " << len << endl;
  int a[len];

  for(int i = 1; i <= len; i++)
  {
    a[i - 1] = atoi(argv[i]);
  }

  cout << "Original Array" << endl;
  for(int i = 0; i < len; i++)
    cout << a[i] << " ";
  cout << endl;

  cout << "Sorted Array" << endl;

  quickSort(a, 0, len -1);

  for(int i = 0; i < len; i++)
    cout << a[i] << " ";
  cout << endl;

  return 0;
}
