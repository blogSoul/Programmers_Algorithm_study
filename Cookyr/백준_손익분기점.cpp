#include <iostream>


using namespace std;


int main(void)
{
	int A = 0;
	int B = 0;
	int C = 0;

	int i = 0;

	cin >> A >> B >> C;


	if (B >= C)
	{
		cout << -1 << endl;
	}
	else 
	{
		i = A / (C - B);

		cout << i+1 << endl;
	}


	


	

	/*
	while (1)
	{
		i++;

		if (A + B * i < C * i)
		{
			cout << i << endl;
			break;
		}

		if (B >= C)
		{
			cout << -1 << endl;
			break;
		}

	}
	*/

	return 0;
}
