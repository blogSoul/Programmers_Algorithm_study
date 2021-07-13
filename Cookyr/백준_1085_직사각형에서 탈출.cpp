#define _CRT_SECURE_NO_WARNINGS

#include "stdio.h"
#include <iostream>
#include <string>
#include <vector>
#include <math.h>

using namespace std;



int main(void)
{
    
    unsigned int x, y, w, h;

    int x_p_length, y_p_length;
    int x_m_length, y_m_length;

    int rlt;


    scanf("%d %d %d %d", &x, &y, &w, &h);

    x_p_length = w - x;
    y_p_length = h - y;
    x_m_length = x;
    y_m_length = y;

    rlt = x_p_length;
    if (y_p_length < rlt)
    {
        rlt = y_p_length;
    }
    if (x_m_length < rlt)
    {
        rlt = x_m_length;
    }
    if (y_m_length < rlt)
    {
        rlt = y_m_length;
    }

    printf("%d", rlt);

    return 0;
}