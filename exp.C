#include <iostream>
using namespace std;

double exp(double x, int y)
{
  if(y == 1)
    return x;
  else
  if(y % 2 == 0)
    return exp(x * x, y / 2);
  else
    return exp(x * x, y / 2) * x;
}

double exponent(int x, int y)
{
  if(y == 0)
    return 1;
  else
  if(y == 1) 
    return x;
  else 
  if(y > 1)
    return exp(x, y);
  else
    return 1.0 / exp(x, y * -1);
}

double exponent2(int x, int y)
{
  double intR = x;
  double result = 1;

  while(y)
  {
    if(y & 1)
      result = result * intR;
    
    y >>= 1;

    intR = intR * intR;
  } 

  return result;
}

int main(int argc, char * argv[])
{
  int x = atoi(argv[1]);
  int y = atoi(argv[2]);

  double power = exponent(x, y);
  double power2 = exponent2(x, y);
  cout << "Power of " << x << " and " << y << " : " << power << endl;
  cout << "Power of " << x << " and " << y << " : " << power2 << endl;
  return 0;
}
