#define _CRT_SECURE_NO_WARNINGS

#include "stdio.h"
#include <iostream>
#include <string>
#include <vector>
#include <math.h>

using namespace std;



int main(void)
{
    int N =0;
    int* p_M;
    int high_score= 0;
    double* p_f_M;
    double rlt = 0.0f;
    unsigned int temp1;
    unsigned int temp2;
    double temp3;


    double t1, t2, t3;

    scanf("%d", &N);

    p_M =  new int[N];
    p_f_M =  new double[N];

    for (int i = 0; i < N; i++)
    {
        scanf("%d", &p_M[i]);

        if (high_score < p_M[i])
        {
            high_score = p_M[i];
        }
        
    }

    for (int i = 0; i < N; i++)
    {
        //temp1 = p_M[i] * 1000000;
        //temp2 = high_score * 1000000;
        //temp3 = (double)(temp1 / temp2);
        
        t1 = (double)((double)p_M[i] / high_score) * 100.0f;

        rlt = rlt + t1;
    }

    printf("%f", rlt / (double)N);

}