#include <iostream>
using namespace std;

#include <stdlib.h>

void merge(int a[], int start, int mid, int end)
{
  int start1 = start;
  int end1 = mid;
  int start2 = mid + 1;
  int end2 = end;
  int dst = 0;
  
  int temp[end - start + 1];

  while(start1 <= end1 && start2 <= end2)
  {
    if(a[start1] < a[start2])
    {
      temp[dst++] = a[start1++];
    }
    else
      temp[dst++] = a[start2++];
  }

  while(start1 <= end1)
    temp[dst++] = a[start1++];

  while(start2 <= end2)
    temp[dst++] = a[start2++];

  for(int i = 0; i < end - start + 1; i++)
    a[start + i ] = temp[i];
}

void mergeSort(int a[], int start, int end)
{
  if(start < end)
  {
    int mid = (start + end) / 2;
    mergeSort(a, start, mid);
    mergeSort(a, mid + 1, end);
    merge(a, start, mid, end);
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

  mergeSort(a, 0, len -1);

  for(int i = 0; i < len; i++)
    cout << a[i] << " ";
  cout << endl;

  return 0;
}
