#include <iostream>


using namespace std;


int main(void)
{
	int input = 0;

	cin >> input;

	for (int i = 0; i<input; i++)
	{
		for (int j = 0; j < (input - i -1); j++)
		{
			cout << " ";
		}

		for (int k = 0; k < i; k++)
		{
			cout << "*";
		}

		cout << "*";

		for (int k = 0; k < i; k++)
		{
			cout << "*";
		}

		cout << endl;
	}

	for (int i = input-1; i > 0; i--)
	{
		for (int j = 0; j < (input - i); j++)
		{
			cout << " ";
		}

		for (int k = 1; k < i; k++)
		{
			cout << "*";
		}

		cout << "*";

		for (int k = 1; k < i; k++)
		{
			cout << "*";
		}

		cout << endl;
	}


	return 0;
}
