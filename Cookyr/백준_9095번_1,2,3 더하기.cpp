#define _CRT_SECURE_NO_WARNINGS

#include "stdio.h"
#include <iostream>
#include <string>
#include <vector>
#include <math.h>

using namespace std;


int cnt = 0;

int func(int val)
{
    if (val <= 0)
    {
        cnt++;
        return 0;
    }

    if (3 <= val)
    {
        func(val - 1);
        func(val - 2);
        func(val - 3);
    }
    else if (val == 2)
    {
        func(val - 1);
        func(val - 2);
    }
    else
    {
        func(val - 1);
    }

    return 0;
}

int main(void)
{
    int T;
    int N;
 
    scanf("%d", &T);
    for(int i =0; i<T; i++)
    { 
        cnt = 0;
        scanf("%d", &N);
        func(N);
        printf("%d\n", cnt);
    }

    return 0;
}