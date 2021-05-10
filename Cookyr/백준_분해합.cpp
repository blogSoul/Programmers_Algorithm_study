#include <iostream>
#include <string>
#include <vector>
#include <math.h>

using namespace std;


int main(void)
{
    int value = 0;
    int rlt = 0;
    int temp = 0;
    int temp2 = 0;
    int temp3 = 0;

    int flag_no_result = 0;
    int flag_result = 0;

    cin >> value;


    for (int i = 1; i < value; i++)
    {
        temp = i;

        for (int j = 0; j <= 6; j++)
        {
            

            if (0 < (int)(i / pow(10, j)))
            {
                temp2 = i % (int)pow(10, (int)(j+1));
                temp3 = (int)(temp2 / pow(10, j));

                temp += temp3;
            }
            else
            {
                break;
            }
        }

        if (temp == value)
        {
            rlt = i;
            break;
        }
    }





    cout << rlt;

    return 0;
}