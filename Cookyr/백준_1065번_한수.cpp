#define _CRT_SECURE_NO_WARNINGS

#include "stdio.h"
#include <iostream>
#include <string>
#include <vector>
#include <math.h>

using namespace std;



int main(void)
{
    int chk = 1;
    int N;
    int cnt = 0;

    int arr[4];
    int diff[3];

    scanf("%d", &N);

    chk = 1;
    for (int i = 1; i <= N; i++)
    {
        //chk = 1;

        arr[3] = i / 1000;
        arr[2] = (i % 1000) / 100;
        arr[1] = (i % 100) / 10;
        arr[0] = i % 10;

        if (i < 10)
        {
            arr[1] = -1;
            arr[2] = -1;
            arr[3] = -1;
        }
        if (10 <= i && i < 100)
        {
            arr[2] = -1;
            arr[3] = -1;
        }
        if (100 <= i && i < 1000)
        {
            arr[3] = -1;
        }

        diff[0] = 0;
        diff[1] = 0;
        diff[2] = 0;

        // 0001
        if (arr[1] == -1)
        {
            cnt++;
            continue;
        }
        else
        {
            diff[0] = arr[1] - arr[0];
        }

        // 0011
        if (arr[2] == -1)
        {
            cnt++;
            continue;
        }
        else
        {
            // 0111
            diff[1] = arr[2] - arr[1];
            
            if (arr[3] == -1)
            {
                if (diff[0] == diff[1])
                {
                    cnt++;
                    continue;
                }
                else
                {
                    continue;
                }
                
            }
            else
            {
                // 1111
                diff[2] = arr[3] - arr[2];

                if (diff[0] == diff[1] && diff[1] == diff[2])
                {
                    cnt++;
                    continue;
                }
                else
                {
                    continue;
                }
            }
            
        }

        
    }

    printf("%d", cnt);

    return 0;
}