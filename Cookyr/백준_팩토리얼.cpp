#include <iostream>
#include <string>
#include <vector>

using namespace std;

int factorial(int val)
{
    if (val == 1)
    {
        return 1;
    }
    else if (val == 0)
    {
        return 1;
    }
    else
    {
        return val * factorial(val - 1);
    }

    
}

int main(void)
{
    int value = 0;

    cin >> value;

    cout << factorial(value);

    return 0;
}