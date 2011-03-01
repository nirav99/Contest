#include <iostream>
using namespace std;

#include <stdlib.h>

int partition(int a[], int left, int right)
{
  int pivotIndex = left + (right - left) / 2;
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

void kthLargestElement(int a[], int start, int end, int k)
{
  if(start < end)
  {
    int pivotPos = partition(a, start, end);
  
    cout << "Pivot Index = " << pivotPos <<  " Pivot Value = " << a[pivotPos] << endl;
 
    if(pivotPos == k -1) 
    {
  //    cout << k << "th largest element is " << a[pivotPos] << endl;
      return;
    }
    else
    if(pivotPos > k)
    {
      kthLargestElement(a, start, pivotPos -1 , k);
    }
    else
      kthLargestElement(a, pivotPos + 1, end, k);
  }
}

int main(int argc, char * argv[])
{
  int len = argc -2;
  int k = atoi(argv[argc -1]);

  cout << "Number of elements = " << len << endl;
  cout << "Kth element to find = " << k << endl;

  int a[len];

  for(int i = 1; i <= len; i++)
  {
    a[i - 1] = atoi(argv[i]);
  }

  cout << "Original Array" << endl;
  for(int i = 0; i < len; i++)
    cout << a[i] << " ";
  cout << endl;

  kthLargestElement(a, 0, len -1, k);

  cout << "final array" << endl;

  for(int i = 0; i < len; i++)
    cout << a[i] << " ";
  cout << endl;

  cout << k << "th element : " << a[k-1] << endl;

  return 0;
}
