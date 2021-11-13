#include <iostream>
#include <string>
#include <vector>
#include <stdio.h>

using namespace std;

int kind_len; 
int target;
int cnt = 0;


int main(void)
{
	cin >> kind_len;
	cin >> target;

	int *kind_arr = new int[kind_len];

	for (int i = 0; i < kind_len; i++)
	{
		cin >> kind_arr[i];
	}

	for (int i = kind_len - 1; i >= 0; i--)
	{
		while (1)
		{
			if (target >= kind_arr[i])
			{
				cnt++;

				target = target - kind_arr[i];
			}
			else
			{
				break;
			}
		}

		if (target == 0)
		{
			break;
		}
	}
	
	printf("%d", cnt);

	return 0;
}