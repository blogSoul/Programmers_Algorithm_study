#define _CRT_SECURE_NO_WARNINGS

#include "stdio.h"
#include <iostream>
#include <string>
#include <vector>
#include <math.h>

using namespace std;



int main(void)
{
    int T = 0;
    unsigned int* p_a;
    unsigned int* p_b;
    unsigned long long* total;

    scanf("%d", &T);

    p_a = new unsigned int[T];
    p_b = new unsigned int[T];
    total = new unsigned long long[T];

    for (int i = 0; i < T; i++)
    {
        scanf("%d %d", &p_a[i], &p_b[i]);

        if (p_b[i] == 0)
        {
            total[i] = 1;
        }
        else if (p_a[i] == 0)
        {
            total[i] = 0;
        }
        else
        {
            total[i] = p_a[i] % 10;
            for (int j = 1; j < p_b[i]; j++)
            {
                total[i] = total[i] * (p_a[i] % 10);
                total[i] = (total[i] % 10);
            }
            total[i] = (total[i] % 10);
        }
        if (total[i] == 0)
        {
            printf("10\n");
        }
        else
        {
            printf("%lld \n", total[i]);
        }
        
    }

    return 0;
}