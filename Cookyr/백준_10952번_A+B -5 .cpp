#define _CRT_SECURE_NO_WARNINGS

#include "stdio.h"
#include <iostream>
#include <string>
#include <vector>
#include <math.h>

using namespace std;



int main(void)
{
    int A, B;

    

    while (1)
    {
        scanf("%d %d", &A, &B);

        if (A == 0 && B == 0)
        {
            break;
        }
        else
        {
            printf("%d", A + B);
        }
    }
    

    return 0;
}