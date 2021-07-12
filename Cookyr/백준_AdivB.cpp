#define _CRT_SECURE_NO_WARNINGS

#include "stdio.h"
#include <iostream>
#include <string>
#include <vector>

using namespace std;

int main(void)
{
    int a, b;

    scanf("%d %d", &a, &b);

    printf("%.10Lf", (long double)((long double)a / (long double)b));


    return 0;
}