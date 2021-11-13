#include <iostream>
#include <string>
#include <vector>
#include <stdio.h>

using namespace std;

int len;
int* var = 0; 

int max_val = 0;
int min_val = 0;
int temp_val = 0;


void main()
{
	//var = new int[];

	cin >> len;
	for (int i = 0; i < len; i++)
	{
		cin >> temp_val;

		if (i == 0)
		{
			max_val = temp_val;
			min_val = temp_val;
		}

		if (temp_val > max_val)
		{
			max_val = temp_val;
		}
		if (temp_val < min_val)
		{
			min_val = temp_val;
		}

	}

	printf("%d %d", min_val, max_val);

}