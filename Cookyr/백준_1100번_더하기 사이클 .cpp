#define _CRT_SECURE_NO_WARNINGS

#include "stdio.h"
#include <iostream>
#include <string>
#include <vector>
#include <math.h>

using namespace std;



int main(void)
{
    int init_value;
    int A, B, a, b;
    int value;
    int value2;
    int value3;
    int cnt = 0;
    
    scanf("%d", &init_value);

    value = init_value;
    while (1)
    {
        A = value / 10;
        B = value % 10;

        value2 = A + B;
        a = value2 / 10;
        b = value2 % 10;

        value3 = B * 10 + b;

        cnt++;

        if (value3 == init_value)
        {
            break;
        }
        else
        {
            value = value3;
        }

    }
    
    printf("%d\n", cnt);

    return 0;
}