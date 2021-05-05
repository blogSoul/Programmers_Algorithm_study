#include <iostream>
#include <string>
#include <vector>

using namespace std;

// 0 : SK
// 1 : CY

int main(void)
{
    int number = 0;
    int loser = 0;

    cin >> number;

    while (1)
    {
        if (3 < number)
        {
            number -= 3;
        }
        else
        {
            number -= 1;
        }

        if (number == 0)
        {
            if (loser == 0)
            {
                cout << "CY" << endl;
            }
            else
            {
                cout << "SK" << endl;
            }
            break;
        }
        else
        {
            if (loser == 0)
            {
                loser = 1;
            }
            else
            {
                loser = 0;
            }
        }


    }

   

}