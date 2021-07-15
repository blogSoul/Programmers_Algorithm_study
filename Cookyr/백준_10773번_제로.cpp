#define _CRT_SECURE_NO_WARNINGS

#include "stdio.h"
#include <iostream>
#include <string>
#include <vector>
#include <math.h>

using namespace std;



int main(void)
{
    int K;
    int* p_arr;
    int temp;
    int idx = 0;
    int sum = 0;
   
 
    scanf("%d", &K);

    p_arr = new int[K];

    for (int i = 0; i < K; i++)
    {
        scanf("%d", &temp);

        if (temp == 0)
        {
            idx--;
        }
        else
        {
            p_arr[idx] = temp;
            idx++;
        }
    }

    for (int i = 0; i < idx; i++)
    {
        sum = sum + p_arr[i];
    }

    printf("%d", sum);

    return 0;
}