#define _CRT_SECURE_NO_WARNINGS

#include "stdio.h"
#include <iostream>
#include <string>
#include <vector>
#include <math.h>

using namespace std;



int main(void)
{
    int cnt = 0;
    char temp[9];
    
    for (int i = 1; i <= 8; i++)
    {
        scanf("%s", temp);

        for (int j = 1; j <= 8; j++)
        {
            if ((i % 2 == 1))
            {
                if (j % 2 == 1)
                {
                    if ((temp[j - 1] == 'F'))
                    {
                        cnt++;
                    }
                }
            }
            else
            {
                if (j % 2 == 0)
                {
                    if ((temp[j - 1] == 'F'))
                    {
                        cnt++;
                    }
                }
            }


            
        }
    }
    printf("%d", cnt);

    return 0;
}