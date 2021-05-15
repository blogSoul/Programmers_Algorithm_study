#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

//int arr[3] = { 3,1,2 };
//sort(arr, arr + 3);

int* weight_arr;
int* height_arr;
int* cnt_arr;

int rankVar = 1;

int len;


int main(void)
{
    cin >> len;
    weight_arr = new int[len];
    height_arr = new int[len];
    cnt_arr = new int[len];

 


    for (int i = 0; i < len; i++)
    {
        cin >> weight_arr[i];
        cin >> height_arr[i];

        cnt_arr[i] = 0;
    }

    for (int i = 0; i < len; i++)
    {
        for (int j = 0; j < len; j++)
        {
            if (i == j) continue;

            if (weight_arr[i] > weight_arr[j] &&
                height_arr[i] > height_arr[j])
            {
                cnt_arr[j]++;
            }  
         
        }
    }


    

    for (int i = 0; i < len; i++)
    {
        printf("%d ", cnt_arr[i]+1);
    }
    
    return 0;

}