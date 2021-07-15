#define _CRT_SECURE_NO_WARNINGS

#include "stdio.h"
#include <iostream>
#include <string>
#include <vector>
#include <math.h>

using namespace std;



int main(void)
{
    unsigned int N;
    unsigned int F;

    unsigned int a_temp;
    unsigned int a_temp2;

    unsigned int b_temp;
    unsigned int b_temp2 = 0;

    unsigned int rlt = 0;


    scanf("%d", &N);
    scanf("%d", &F);

    for (int i = 0; i < 100; i++)
    {
        a_temp = N / 100;
        a_temp2 = (a_temp * 100) % F;

        //b_temp = N % 100;
        b_temp2 = i % F;

        if (((a_temp2 + b_temp2) % F) == 0)
        {
            rlt = i;
            break;
        }
    }

    if (rlt < 10)
    {
        printf("0");
        printf("%d", rlt);
    }
    else
    {
        printf("%d", rlt);
    }

    return 0;
}